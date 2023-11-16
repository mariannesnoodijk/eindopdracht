package com.example.eindopdracht.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

// This class serves as a data structure for transferring data related to authorization between different parts of the application
@Data
public class AuthDto {


    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Username cannot be empty")
    private String password;
}
