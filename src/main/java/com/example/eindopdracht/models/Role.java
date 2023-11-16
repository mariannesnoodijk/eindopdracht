package com.example.eindopdracht.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

// Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Data
@Entity
@Table(name="roles")
public class Role {

    // Primary key for the role entity
    @Id
    private String rolename;

    // Many-to-many relationship with the User entity, mapped by the "roles" field in User
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
