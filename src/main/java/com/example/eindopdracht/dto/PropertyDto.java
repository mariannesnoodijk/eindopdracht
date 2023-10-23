package com.example.eindopdracht.dto;

import lombok.Data;

@Data
public class PropertyDto { // This class serves as a data structure for transferring data related to properties between different parts of the application

    private Long id;

    private String streetName;
    private Integer houseNumber;
    private Integer numberOfRooms;
    private String surface;
    private Double price;
//    private Boolean available;
    // private Boolean match;
}
