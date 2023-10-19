package com.example.eindopdracht.dto;

import lombok.Data;

@Data
public class AccountDto {
    private Long id;

    private String username;
    private String password;

    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String emailAddress;
}
