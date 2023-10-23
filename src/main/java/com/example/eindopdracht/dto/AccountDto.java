package com.example.eindopdracht.dto;

import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class AccountDto { //This class serves as a data structure for transferring data related to user accounts between different parts of the application

    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 8, max = 20, message = "Your password must contain at least 8 characters and at most 20 characters")
    private String password;

    @NotEmpty(message = "First name cannot be empty")
    @Min(value = 2)
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    @Min(value = 2)
    private String lastName;
    @NotEmpty
    @Positive
    @Min(value = 10, message = "A phone number consists of 10 numbers")
    @Max(value = 10, message = "A phone number consists of 10 numbers")
    private Long phoneNumber;
    @NotEmpty
    @Email(message = "This needs to be an email address")
    private String emailAddress;
}
