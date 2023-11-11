package com.example.eindopdracht.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PropertyDto { // This class serves as a data structure for transferring data related to properties between different parts of the application

    private Long propertyId;

    @NotEmpty(message = "Address must not be empty")
    private String address;

    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be greater than or equal to 0")
    private Double price;

    @NotEmpty(message = "Description must not be empty")
    private String description;

    private Boolean isFavorite;

    // private Boolean available; // TODO: OPTIONAL... GA IK DOEN OF NIET? Like to have

    // private Boolean match; // TODO: OPTIONAL... GA IK DOEN OF NIET? Like to have
}
