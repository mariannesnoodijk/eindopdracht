package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.UserDto;
import com.example.eindopdracht.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account") // Using @RequestMapping sets the endpoint as a standard, unless specified otherwise
public class AccountController {

    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> uDto = userService.getAllUsers();
        return new ResponseEntity<>(uDto, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getOneUser(@PathVariable Long id) {
        UserDto uDto = userService.getUser(id);
        return new ResponseEntity<>(uDto, HttpStatus.OK);
    }
}
