package com.example.eindopdracht.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Property {

    @Id //  Primary Key of the entity
    @GeneratedValue
    Long id;

    //  Variables of class Property
    // welke informatie heb ik nodig?
    // adres
    // huisnummer
    // prijs
    // beschikbaar ja of nee
    // match met gebruiker ja of nee


    private String streetName;
    private Integer houseNumber;
    private Integer numberOfRooms;
    private Double price;
    private Boolean available;
    private Boolean match;

    // Constructor of class Property -- niet nodig ivm @Data (want is geimporteerd door Lombok


    // Getters and Setters of class Property -- niet nodig ivm @Data (want is geimporteerd door Lombok

}
