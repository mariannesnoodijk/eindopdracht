package com.example.eindopdracht.dto;

import lombok.Data;

@Data
public class PropertyDto {

    private String streetName;
    private Integer houseNumber;
    private Integer numberOfRooms;
    private Double price;
    private Boolean available;
    private Boolean match;
}
