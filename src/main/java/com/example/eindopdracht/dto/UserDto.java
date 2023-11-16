package com.example.eindopdracht.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

// This class serves as a data structure for transferring data related to authorization between different parts of the application
@Data
public class UserDto {

    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotEmpty
    private String[] roles;
}
