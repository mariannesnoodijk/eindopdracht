package com.example.eindopdracht.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PropertyDto { // This class serves as a data structure for transferring data related to properties between different parts of the application

    @NotEmpty(message = "Streetname cannot be empty")
    private String streetname;

    @Min(value = 1, message = "A housenumber cannot be 0")
    private Integer housenumber;

    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be greater than or equal to 0")
    private Double price;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    // private Boolean available; // TODO: OPTIONAL... GA IK DOEN OF NIET? Like to have

    // private Boolean match; // TODO: OPTIONAL... GA IK DOEN OF NIET? Like to have
}
