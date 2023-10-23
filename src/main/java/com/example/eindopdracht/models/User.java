package com.example.eindopdracht.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet; // Set deed het niet, dus veranderd naar List. Waarom List gebruikt? Voor tech documentatie
import java.util.List;
import java.util.Set;

@Data // Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Entity
@Table(name = "users")
public class User {

    @Id //  Primary Key of the entity
    private String username;
    private String password;


    // Relations between entities:

    // RELATION BETWEEN USER & ACCOUNT
    @OneToOne(mappedBy = "user") // This is the target side of the relation with Account. There is nothing in the database.
            Account account;

    // RELATION BETWEEN USER & ROLE
    @ManyToMany(fetch = FetchType.EAGER)
    // The FetchType. EAGER option indicates that the associated entity should be fetched eagerly, which means that it will be fetched at the same time as the parent entity.
    private List<Role> roles = new ArrayList<>();

}
