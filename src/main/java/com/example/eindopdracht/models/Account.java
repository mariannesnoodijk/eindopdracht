package com.example.eindopdracht.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

// Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Data
@Entity
@Table(name = "accounts")
public class Account {

    // Primary key for the Account entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    // Fields representing account details
    private String firstname;
    private String lastname;
    private String email;

    // One-to-one relationship with the User entity
    @OneToOne
            User user;

    // One-to-many relationship with the Viewing entity, mapped by the "account" field in Viewing
    @OneToMany(mappedBy = "account")
    List<Viewing> viewings;
}
