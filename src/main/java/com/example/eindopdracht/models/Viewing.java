package com.example.eindopdracht.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data // Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Entity
public class Viewing {

    @Id //  Primary Key of the entity
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private Integer phoneNumber;

    //  Variables of class Viewing
    // welke informatie heb ik nodig?
// voornaam
    // achternaam
    // email
    // telefoonnummer


    // Constructor of class Viewing


    // Getters and Setters of class Viewing
}
