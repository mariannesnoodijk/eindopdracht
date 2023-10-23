package com.example.eindopdracht.models;

import com.example.eindopdracht.controllers.UserController;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data // Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id //  Primary Key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This specifies that the ID is automatically generated
    private Long id;

    private String username;
    private String password;

    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String emailAddress;

    // contactgegevens toevoegen???
    // // welke informatie heb ik nodig?
    //    // voornaam
    //    // achternaam
    //    // telefoonnummer
    //    // emailadres


    // RELATION BETWEEN ACCOUNT & USER
    @OneToOne // This is the owner side of the relation. There is a Foreign Key in the database.
            User user;

    // RELATION BETWEEN ACCOUNT & PROPERTY
    @OneToMany(mappedBy = "accounts") // This is the target side of the relation with Viewing. There is nothing in the database.
    @JsonIgnore
    private List<Property> properties;

}
