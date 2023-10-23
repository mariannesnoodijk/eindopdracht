package com.example.eindopdracht.dto;

import lombok.Data;

@Data
public class AuthDto { // This class serves as a data structure for transferring data related to authorization between different parts of the application
    private String username;
    private String password;
}
