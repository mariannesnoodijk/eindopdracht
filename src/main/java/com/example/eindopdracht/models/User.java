package com.example.eindopdracht.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Data
@Entity
@Table(name = "users")
public class User {

    // Primary key for the User entity
    @Id
    private String username;
    private String password;

    // One-to-one relationship with the Account entity, mapped by the "user" field in Account
    @OneToOne(mappedBy = "user")
            Account account;

    // Many-to-many relationship with the Role entity
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
}
