package com.example.eindopdracht.models;

import com.example.eindopdracht.controllers.UserController;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data // Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id //  Primary Key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This specifies that the ID is automatically generated
    private Long accountId;

    private String firstname;
    private String lastname;
    private String phonenumber;
    private String emailaddress;


    // contactgegevens toevoegen???
    // // welke informatie heb ik nodig?
    //    // voornaam
    //    // achternaam
    //    // telefoonnummer
    //    // emailadres


    // Relations between entities:

    // RELATION BETWEEN ACCOUNT & USER
    @OneToOne // This is the owner side of the relation. There is a Foreign Key in the database.
            User user;

    // RELATION BETWEEN ACCOUNT & PROPERTY
    @ManyToMany(fetch = FetchType.EAGER) // The FetchType. EAGER option indicates that the associated entity should be fetched eagerly, which means that it will be fetched at the same time as the parent entity.
    private List<Property> properties = new ArrayList<>();

    // RELATION BETWEEN ACCOUNT & VIEWING
    @OneToMany(mappedBy = "account") // This is the target side of the relation with Viewing. There is nothing in the database.
    @JsonIgnore
    List<Viewing> viewings;

}
