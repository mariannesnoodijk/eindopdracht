package com.example.eindopdracht.models;

import jakarta.persistence.*;
import lombok.Data;


@Data // Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Entity
@Table(name="properties")
public class Property {

    @Id //  Primary Key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This specifies that the ID is automatically generated
    private Long propertyId;

    private String address;
    private Double price;
    private String description;
    private Boolean isFavorite;


    // RELATION BETWEEN PROPERTY & VIEWING
//    @OneToMany(mappedBy = "properties") // This is the target side of the relation with Viewing. There is nothing in the database.
//    @JsonIgnore
//    private List<Viewing> viewings;
}
