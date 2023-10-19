package com.example.eindopdracht.services;

import com.example.eindopdracht.dto.AccountDto;
import com.example.eindopdracht.exceptions.IdNotFoundException;
import com.example.eindopdracht.models.Account;
import com.example.eindopdracht.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDto> accountDtos = new ArrayList<>();

        for (Account a : accounts) {
            AccountDto aDto = new AccountDto();
            accountToAccountDto(a, aDto);

            accountDtos.add(aDto);
        }
        return accountDtos;
    }

    public AccountDto getAccount(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            Account a = account.get();
            AccountDto aDto = new AccountDto();
            accountToAccountDto(a, aDto);
            return (aDto);
        } else {
            throw new IdNotFoundException("Property not found with ID: " + id);
        }
    }

    public AccountDto createAccount(AccountDto accountDto) {
        Account account = new Account();
        accountDtoToAccount(accountDto, account);

        Account savedAccount = accountRepository.save(account);

        AccountDto savedAccountDto = new AccountDto();
        accountToAccountDto(savedAccount, savedAccountDto);

        return savedAccountDto;
    }

    public void deleteAccount(@RequestBody Long id) {
            accountRepository.deleteById(id);
    }


    private static void accountToAccountDto(Account a, AccountDto aDto) {
        aDto.setId(a.getId());
        aDto.setUsername(a.getUsername());
        aDto.setPassword(a.getPassword());
        aDto.setFirstName(a.getFirstName());
        aDto.setLastName(a.getLastName());
        aDto.setPhoneNumber(a.getPhoneNumber());
        aDto.setEmailAddress(a.getEmailAddress());
    }

    private static void accountDtoToAccount(AccountDto accountDto, Account account) {
        account.setId(accountDto.getId());
        account.setUsername(accountDto.getUsername());
        account.setPassword(accountDto.getPassword());
        account.setFirstName(accountDto.getFirstName());
        account.setLastName(accountDto.getLastName());
        account.setPhoneNumber(accountDto.getPhoneNumber());
        account.setEmailAddress(accountDto.getEmailAddress());
    }

}
