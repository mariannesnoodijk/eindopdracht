package com.example.eindopdracht.services;

import com.example.eindopdracht.dto.AccountDto;
import com.example.eindopdracht.exceptions.IdNotFoundException;
import com.example.eindopdracht.exceptions.IncorrectEmailException;
import com.example.eindopdracht.models.Account;
import com.example.eindopdracht.repositories.AccountRepository;
import jakarta.validation.constraints.Email;
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

    public AccountDto createAccount(AccountDto accountDto) {
        Account account = new Account();
        accountDtoToAccount(accountDto, account);

        Account savedAccount = accountRepository.save(account);

        AccountDto savedAccountDto = new AccountDto();
        accountToAccountDto(savedAccount, savedAccountDto);

        return savedAccountDto;
    }

    //TODO: Need to add a throw IncorrectEmailException exception here. Right now Postman sees when email is invalid, but i don't get an exception message. If succeeded, do the same for phone number.
//    public AccountDto createAccount(AccountDto accountDto) {
//        //   Validate email address
//        if (accountDto.getEmailaddress() != null && matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
////        if (accountDto.getEmailaddress() != null && validateEmailPattern(accountDto.getEmailaddress())) {
////        if (validateEmailPattern(accountDto.getEmailaddress())) {
//
//        // Validate phone number
////            if (!validatePhoneNumber(accountDto.getPhonenumber())) {
////                throw new IllegalArgumentException("Invalid phone number: " + accountDto.getPhonenumber());
////            }
//
//        Account account = new Account();
//        accountDtoToAccount(accountDto, account);
//
//        Account savedAccount = accountRepository.save(account);
//
//        AccountDto savedAccountDto = new AccountDto();
//        // Mapping savedAccount to savedAccountDto
//        accountToAccountDto(savedAccount, savedAccountDto);
//
//        return savedAccountDto;
//    } else {
//            throw new IncorrectEmailException("Invalid email address: " + accountDto.getEmailaddress());
//        }
//    }

//    public boolean validateEmailPattern(@Email String emailaddress) {
////    public boolean validateEmailPattern(String emailaddress) {
//        // TODO: huidige emailRegex throwt foutmelding maar geen exception message wanneer email adres GEEN @ bevat
////        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
//        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
//        return emailaddress.matches(emailRegex);
//    }



    public String deleteAccount(@RequestBody Long accountId) {
        accountRepository.deleteById(accountId);
        return "Account successfully deleted";
    }


    private static void accountToAccountDto(Account a, AccountDto aDto) {
        aDto.setFirstname(a.getFirstname());
        aDto.setLastname(a.getLastname());
        aDto.setPhonenumber(a.getPhonenumber());
        aDto.setEmailaddress(a.getEmailaddress());
    }

    private static void accountDtoToAccount(AccountDto accountDto, Account account) {
        account.setFirstname(accountDto.getFirstname());
        account.setLastname(accountDto.getLastname());
        account.setPhonenumber(accountDto.getPhonenumber());
        account.setEmailaddress(accountDto.getEmailaddress());
    }

}