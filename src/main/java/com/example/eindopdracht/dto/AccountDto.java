package com.example.eindopdracht.dto;

import lombok.Data;


@Data
public class AccountDto { //This class serves as a data structure for transferring data related to user accounts between different parts of the application
    private Long id;

    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String emailAddress;
}
