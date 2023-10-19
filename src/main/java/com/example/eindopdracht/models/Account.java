package com.example.eindopdracht.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data // Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Entity
@Table(name="accounts")
public class Account {

    @Id //  Primary Key of the entity
    @GeneratedValue
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

}
