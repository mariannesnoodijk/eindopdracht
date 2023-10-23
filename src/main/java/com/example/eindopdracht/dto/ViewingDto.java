package com.example.eindopdracht.dto;

import lombok.Data;

@Data
public class ViewingDto { // This class serves as a data structure for transferring data related to viewings between different parts of the application
    private Long id;

    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String emailAddress;
}