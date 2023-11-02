package com.example.eindopdracht.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto { // This class serves as a data structure for transferring data related to user accounts between different parts of the application

    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotEmpty
    private String[] roles;
}
