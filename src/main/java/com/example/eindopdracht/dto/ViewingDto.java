package com.example.eindopdracht.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ViewingDto { // This class serves as a data structure for transferring data related to viewings between different parts of the application

    @NotEmpty(message = "Full name cannot be empty")
    private String fullName;

    @Min(value = 10, message = "A phone number consists of 10 numbers")
    @Max(value = 10, message = "A phone number consists of 10 numbers")
    private Long phoneNumber;
    @NotEmpty
    @Email(message = "This needs to be an email address")
    private String emailAddress;

    @NotEmpty
    @Future
    @DateTimeFormat(pattern="dd/mm/yyyy")
    private Date dateViewing; // Welk Data Type valt een datum onder?
    @NotEmpty
    @Future
    private String timeViewing; // Welk Data Type val een tijd onder?
}