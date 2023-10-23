package com.example.eindopdracht.dto;


import lombok.Data;

@Data
public class UserDto { // This class serves as a data structure for transferring data related to user accounts between different parts of the application
    private String username;
    private String password;

    private String[] roles;
}
