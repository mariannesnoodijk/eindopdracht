package com.example.eindopdracht.dto;


import lombok.Data;

@Data
public class UserDto {
    private Long id;
    public String username;
    public String password;
}
