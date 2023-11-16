package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.UserDto;
import com.example.eindopdracht.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Handling of HTTP requests which returns the response directly to the client
@RestController
// Setting the endpoint as a standard, unless specified otherwise
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    // Constructor to inject the UserService dependency
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint to retrieve a single account by ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getOneUser(@PathVariable String userId) {
        // Call the service to retrieve a user by ID
        UserDto uDto = userService.getUser(userId);
        // Return the user and HTTP status code 200 (OK)
        return new ResponseEntity<>(uDto, HttpStatus.OK);
    }

    // Endpoint to retrieve all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        // Call the service to retrieve all users
        List<UserDto> dDto = userService.getAllUsers();
        // Return the list of users and HTTP status code 200 (OK)
        return new ResponseEntity<>(dDto, HttpStatus.OK);
    }
}
