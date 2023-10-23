package com.example.eindopdracht.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data // Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Entity
@Table(name="properties")
public class Property {

    @Id //  Primary Key of the entity
    @GeneratedValue
    Long id;

    //  Variables of class Property
    // welke informatie heb ik nodig?
    // adres
    // huisnummer
    // oppervlakte
    // prijs
    // beschikbaar ja of nee
    // match met gebruiker ja of nee
    // favorite?? ja of nee


    private String streetName;
    private Integer houseNumber;
    private Double price;
    private String description;
//    private Boolean available;
//    private Boolean match; // TODO: OPTIONAL... GA IK DOEN OF NIET?


    // RELATION BETWEEN PROPERTY & USER
    @ManyToMany(mappedBy = "properties") // This is the target side of the relation with Account. There is nothing in the database.
    private List<User> users;

    // RELATION BETWEEN PROPERTY & VIEWING
    @OneToMany(mappedBy = "properties") // This is the target side of the relation with Viewing. There is nothing in the database.
    @JsonIgnore
    private List<Viewing> viewings;

    // RELATION BETWEEN PROPERTY & ACCOUNT
    @ManyToOne(fetch = FetchType.EAGER) // This is the owner of the relation with Property. There is a Foreign Key in the database
    @JoinColumn(name = "account_id")
    private Account accounts;


}
