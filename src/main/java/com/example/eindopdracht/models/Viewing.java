package com.example.eindopdracht.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data // Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Entity
public class Viewing {

    @Id //  Primary Key of the entity
    @GeneratedValue
    private Long id;

    private String fullName;
    private Long phoneNumber;
    private String emailAddress;

    private String dateViewing; // Welk Data Type valt een datum onder?
    private String timeViewing; // Welk Data Type val een tijd onder?

    //  Variables of class Viewing
    // welke informatie heb ik nodig?
// voornaam
    // achternaam
    // email
    // telefoonnummer


    // RELATION BETWEEN VIEWING & ACCOUNT
    @ManyToOne(fetch = FetchType.EAGER) // This is the owner of the relation with User. There is a Foreign Key in the database
    @JoinColumn(name = "account_id")
    private Account account;

    // RELATION BETWEEN VIEWING & PROPERTY
    @ManyToOne(fetch = FetchType.EAGER) // This is the owner of the relation with Property. There is a Foreign Key in the database
    @JoinColumn(name = "property_id")
    private Property properties;

}
