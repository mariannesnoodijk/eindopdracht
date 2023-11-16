package com.example.eindopdracht.models;

import jakarta.persistence.*;
import lombok.Data;

// Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Data
@Entity
@Table(name="properties")
public class Property {

    // Primary key for the Property entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

    // Fields representing property details
    private String address;
    private Double price;
    private String description;
    private Boolean isFavorite;
}
