package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.AccountDto;
import com.example.eindopdracht.dto.UserDto;
import com.example.eindopdracht.models.Role;
import com.example.eindopdracht.models.User;
import com.example.eindopdracht.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    // Endpoint to create a new user
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        // Call the service to create a new user
        User newUser = userService.createUser(userDto);

        // Convert User to UserDto
        UserDto newUserDto = new UserDto();
        userToUserDto(newUser, newUserDto);

        // Return the new user and HTTP status code 201 (Created)
        return new ResponseEntity<>(newUserDto, HttpStatus.CREATED);
    }

    // Helper method to convert User to UserDto
    private static void userToUserDto(User u, UserDto uDto) {
        uDto.setUsername(u.getUsername());
        uDto.setPassword(u.getPassword());
        ArrayList<String> roles = new ArrayList<>();
        for (Role role : u.getRoles()) {
            roles.add(role.getRolename());
        }
        uDto.setRoles(roles.toArray(new String[0]));
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
