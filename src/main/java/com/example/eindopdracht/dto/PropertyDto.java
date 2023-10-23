package com.example.eindopdracht.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PropertyDto { // This class serves as a data structure for transferring data related to properties between different parts of the application

    @NotEmpty(message = "Streetname cannot be empty")
    private String streetName;
    @Min(value = 1, message = "A housenumber cannot be 0")
    private Integer houseNumber;
    @NotEmpty(message = "Price cannot be empty")
    private Double price;
    @NotEmpty(message = "Description cannot be empty")
    private String description;
    // private Boolean available;
    // private Boolean match;
}
