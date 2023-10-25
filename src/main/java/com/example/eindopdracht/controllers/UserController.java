package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.AccountDto;
import com.example.eindopdracht.dto.PropertyDto;
import com.example.eindopdracht.dto.UserDto;
import com.example.eindopdracht.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Handles HTTP requests and returns the response directly to the client
@RequestMapping("/users") // Using @RequestMapping sets the endpoint as a standard, unless specified otherwise
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

// TODO: Wil ik hier wel alle users op kunnen vragen? Of wil ik de mogelijkheid om alle ACCOUNTS op te vragen?

    @GetMapping // This method handles HTTP GET requests to the /users endpoint

    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> dDto = userService.getAllUsers();
        return new ResponseEntity<>(dDto, HttpStatus.OK);
    }

    @GetMapping("/{userId}") // This method handles HTTP GET requests to the /users/{userId} endpoint, where {id} is a path variable representing the property ID
    public ResponseEntity<UserDto> getOneUser(@PathVariable String userId) {
        UserDto uDto = userService.getUser(userId);
        return new ResponseEntity<>(uDto, HttpStatus.OK);
    }

    @PostMapping // This method handles HTTP POST requests to the /users endpoint creating a user
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto) {
        String result = userService.createUser(userDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
