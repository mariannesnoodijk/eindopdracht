package com.example.eindopdracht.dto;

import lombok.Data;

@Data
public class ViewingDto {
    private Long id;

    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String emailAddress;
}