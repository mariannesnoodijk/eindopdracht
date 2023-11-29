package com.example.eindopdracht.services;

import com.example.eindopdracht.dto.AccountDto;
import com.example.eindopdracht.dto.UserDto;
import com.example.eindopdracht.exceptions.IdNotFoundException;
import com.example.eindopdracht.models.Account;
import com.example.eindopdracht.models.User;
import com.example.eindopdracht.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    // Constructor to inject dependencies
    public AccountService(AccountRepository accountRepository, UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    // Create a new account
    public AccountDto createAccount(AccountDto accountDto) {
        // Create a user associated with the account
        UserDto userDto = new UserDto();
        userDto.setUsername(accountDto.getUsername());
        userDto.setPassword(accountDto.getPassword());

        // Set roles based on the selected role from the frontend
        String selectedRole = accountDto.getRole();
        if (selectedRole != null && (selectedRole.equals("ADMIN") || selectedRole.equals("USER"))) {
            userDto.setRoles(new String[]{selectedRole});
        } else {
            userDto.setRoles(new String[]{"USER"});
        }

        User user = userService.createUser(userDto);

        // Create and save the account
        Account account = new Account();
        accountDtoToAccount(accountDto, account);
        account.setUser(user);
        Account savedAccount = accountRepository.save(account);

        // Convert the saved account to AccountDto
        AccountDto savedAccountDto = new AccountDto();
        accountToAccountDto(savedAccount, savedAccountDto);

        return savedAccountDto;
    }

    // Retrieve an account by ID
    public AccountDto getAccount(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            Account a = account.get();
            AccountDto aDto = new AccountDto();
            accountToAccountDto(a, aDto);
            return (aDto);
        } else {
            throw new IdNotFoundException("Account not found with ID: " + accountId);
        }
    }

    // Retrieve all accounts
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDto> accountDtos = new ArrayList<>();

        // Convert Account entities to AccountDto
        for (Account a : accounts) {
            AccountDto aDto = new AccountDto();
            accountToAccountDto(a, aDto);

            accountDtos.add(aDto);
        }
        return accountDtos;
    }

    // Delete an account by ID
    public String deleteAccount(@RequestBody Long accountId) {
        accountRepository.deleteById(accountId);
        return "Account successfully deleted";
    }

    // Helper method to convert Account to AccountDto
    private static void accountToAccountDto(Account a, AccountDto aDto) {
        aDto.setAccountId(a.getAccountId());
        aDto.setFirstname(a.getFirstname());
        aDto.setLastname(a.getLastname());
        aDto.setEmail(a.getEmail());
        aDto.setUsername(a.getUser().getUsername());
        aDto.setRole(a.getUser().getRoles().get(0).getRolename());
    }

    // Helper method to convert AccountDto to Account
    private static void accountDtoToAccount(AccountDto accountDto, Account account) {
        account.setAccountId(accountDto.getAccountId());
        account.setFirstname(accountDto.getFirstname());
        account.setLastname(accountDto.getLastname());
        account.setEmail(accountDto.getEmail());
    }
}