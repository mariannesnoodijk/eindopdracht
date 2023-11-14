package com.example.eindopdracht.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String emailaddress;

    @Future
    @DateTimeFormat(pattern="dd/mm/yyyy") // Dit checken wanneer Frontend klaar is
    private LocalDate date;

    @Future
    private LocalTime time;

    @NotNull(message = "ProductId cannot be empty")
    private Long accountId;
}