package com.example.eindopdracht.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data // Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Entity
public class Viewing {

    @Id //  Primary Key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This specifies that the ID is automatically generated
    private Long viewingId;

    private String fullname;
    private String phonenumber;
    private String emailaddress;

    private LocalDate date;
    private LocalTime time;


    // RELATION BETWEEN VIEWING & ACCOUNT
    @ManyToOne(fetch = FetchType.EAGER) // This is the owner of the relation with User. There is a Foreign Key in the database
    @JoinColumn(name = "account_id")
    private Account account;

    // RELATION BETWEEN VIEWING & PROPERTY
    @ManyToOne(fetch = FetchType.EAGER) // This is the owner of the relation with Property. There is a Foreign Key in the database
    @JoinColumn(name = "property_id")
    private Property properties;
}
