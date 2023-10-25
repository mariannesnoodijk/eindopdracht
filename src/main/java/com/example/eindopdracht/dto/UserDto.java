package com.example.eindopdracht.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto { // This class serves as a data structure for transferring data related to user accounts between different parts of the application

    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
//    @Size(min = 8, max = 20, message = "Your password must contain at least 8 characters and at most 20 characters")
    private String password;

    private String[] roles;
}
