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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This specifies that the ID is automatically generated
    private Long propertyId;

    private String streetname;
    private String housenumber;
    private Double price;
    private String description;
//    private Boolean available;// TODO: OPTIONAL... GA IK DOEN OF NIET? Like to have -- NEE, deze moet er wel nog in
//    private Boolean match; // TODO: OPTIONAL... GA IK DOEN OF NIET? Like to have


    // RELATION BETWEEN PROPERTY & ACCOUNT
    @ManyToMany(mappedBy = "properties") // This is the target side of the relation with Account. There is nothing in the database.
    private List<Account> accounts;

    // RELATION BETWEEN PROPERTY & VIEWING
    @OneToMany(mappedBy = "properties") // This is the target side of the relation with Viewing. There is nothing in the database.
    @JsonIgnore
    private List<Viewing> viewings;
}
