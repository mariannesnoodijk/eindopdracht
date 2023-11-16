package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.AccountDto;
import com.example.eindopdracht.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Handling of HTTP requests which returns the response directly to the client
@RestController
// Setting the endpoint as a standard, unless specified otherwise
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    // Constructor to inject the AccountService dependency
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Endpoint to create a new account
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountDto accountDto) {
        // Call the service to create a new account
        AccountDto newAccount = accountService.createAccount(accountDto);
        // Return the new account and HTTP status code 201 (Created)
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    // Endpoint to retrieve a single account by ID
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getOneAccount(@PathVariable Long accountId) {
        // Call the service to retrieve an account by ID
        AccountDto aDto = accountService.getAccount(accountId);
        // Return the account and HTTP status code 200 (OK)
        return new ResponseEntity<>(aDto, HttpStatus.OK);
    }

    // Endpoint to retrieve all accounts
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        // Call the service to retrieve all accounts
        List<AccountDto> aDto = accountService.getAllAccounts();
        // Return the list of accounts and HTTP status code 200 (OK)
        return new ResponseEntity<>(aDto, HttpStatus.OK);
    }

    // Endpoint to delete an account by ID
    @DeleteMapping("/{accountId}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable Long accountId) {
        // Call the service to delete an account by ID
        accountService.deleteAccount(accountId);
        // Return HTTP status code 204 (No Content) indicating successful deletion
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
