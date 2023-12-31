package com.example.eindopdracht.dto;

import com.example.eindopdracht.models.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

// This class serves as a data structure for transferring data related to authorization between different parts of the application
@Data
public class AccountDto {

    public Long accountId;
    @NotEmpty(message = "First name cannot be empty")
    public String firstname;
    @NotEmpty(message = "Last name cannot be empty")
    public String lastname;
    @Email(message = "This needs to be a valid email address")
    public String email;

    @NotEmpty
    public String username;
    @NotEmpty
    public String password;

    // Add a role field to store the selected role during registration
    @NotEmpty
    public String role;
}
