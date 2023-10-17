package com.example.eindopdracht.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

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
    private Integer numberOfRooms;
    private String surface;
    private Double price;
    private Boolean available;
    private Boolean match;


}
