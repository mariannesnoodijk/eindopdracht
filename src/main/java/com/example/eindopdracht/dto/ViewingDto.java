package com.example.eindopdracht.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
public class ViewingDto { // This class serves as a data structure for transferring data related to viewings between different parts of the application

    @NotEmpty(message = "Full name cannot be empty")
    private String fullname;

//    @Min(value = 0600000000, message = "A phone number consists of 10 numbers")
//    @Max(value = 0699999999, message = "A phone number consists of 10 numbers")
    // REGEX gebruiken voor telefoonnummer. Zoek Google !
    @NotEmpty
//    @Pattern - look up how to use pattern for phone number
    private String phonenumber;
    //  private String phonenumber = "^\\+(?:[0-9] ?){6,14}[0-9]$";

    @NotEmpty
    @Email(message = "This needs to be an email address")
    private String emailaddress;

    @Future
    @DateTimeFormat(pattern="dd/mm/yyyy")
    private LocalDate viewingdate;

    @Future
    private LocalTime viewingtime;
}