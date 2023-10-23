package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.AccountDto;
import com.example.eindopdracht.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Handles HTTP requests and returns the response directly to the client
@RequestMapping("/accounts") // Using @RequestMapping sets the endpoint as a standard, unless specified otherwise
public class AccountController {

    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping// This method handles HTTP GET requests to the /accounts endpoint
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> aDto = accountService.getAllAccounts();
        return new ResponseEntity<>(aDto, HttpStatus.OK);
    }


    @GetMapping("/{id}") // This method handles HTTP GET requests to the /accounts/{id} endpoint
    public ResponseEntity<AccountDto> getOneAccount(@PathVariable Long id) {
        AccountDto aDto = accountService.getAccount(id);
        return new ResponseEntity<>(aDto, HttpStatus.OK);
    }


    @PostMapping// This method handles HTTP POST requests to the /accounts endpoint creating an account
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountDto accountDto) {
        AccountDto newAccount = accountService.createAccount(accountDto);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}") // This method handles HTTP DELETE requests to the /accounts/{id} endpoint
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
