package com.example.eindopdracht.dto;

import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class AccountDto { //This class serves as a data structure for transferring data related to user accounts between different parts of the application

    public Long accountId;

    @NotEmpty(message = "First name cannot be empty")
    public String firstname;

    @NotEmpty(message = "Last name cannot be empty")
    public String lastname;

    @NotEmpty(message = "Phonenumber cannot be empty")
//    @Min(value = 10, message = "A phone number consists of 10 numbers")
//    @Max(value = 10, message = "A phone number consists of 10 numbers")
    // REGEX gebruiken voor telefoonnummer. Zoek Google !
    // phoneRegex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
    public String phonenumber;

    @Email(message = "This needs to be a valid email address")
    public String emailaddress;

}
