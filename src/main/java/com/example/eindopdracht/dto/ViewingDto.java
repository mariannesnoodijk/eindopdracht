package com.example.eindopdracht.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ViewingDto { // This class serves as a data structure for transferring data related to viewings between different parts of the application

    private Long viewingId;

    @NotEmpty(message = "Full name cannot be empty")
    private String fullname;

    @NotEmpty
    private String phonenumber;
    //  private String phonenumber = "^\\+(?:[0-9] ?){6,14}[0-9]$";

    @Email(message = "This needs to be an email address")
    private String email;

    @Future
    private LocalDate date;

    @NotNull
    private LocalTime time;

    @NotNull
    private Long accountId;
}