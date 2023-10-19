package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.AccountDto;
import com.example.eindopdracht.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Handles HTTP requests and returns the response directly to the client
@RequestMapping("/accounts") // Using @RequestMapping sets the endpoint as a standard, unless specified otherwise
public class AccountController {

    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // This method handles HTTP GET requests to the /accounts endpoint
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> aDto = accountService.getAllAccounts();
        return new ResponseEntity<>(aDto, HttpStatus.OK);
    }

    // This method handles HTTP GET requests to the /accounts/{id} endpoint
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getOneAccount(@PathVariable Long id) {
        AccountDto aDto = accountService.getAccount(id);
        return new ResponseEntity<>(aDto, HttpStatus.OK);
    }

    // This method handles HTTP POST requests to the /accounts endpoint
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        AccountDto newAccount = accountService.createAccount(accountDto);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    // This method handles HTTP DELETE requests to the /accounts/{id} endpoint
    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
