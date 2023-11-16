package com.example.eindopdracht.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

// This class serves as a data structure for transferring data related to authorization between different parts of the application
@Data
public class ViewingDto {

    private Long viewingId;

    @NotEmpty(message = "Full name cannot be empty")
    private String fullname;

    @NotEmpty
    private String phonenumber;

    @Email(message = "This needs to be an email address")
    private String email;

    @Future
    private LocalDate date;

    @NotNull
    private LocalTime time;

    @NotNull
    private Long accountId;
}