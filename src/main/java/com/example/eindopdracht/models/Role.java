package com.example.eindopdracht.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data // Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Entity
@Table(name="roles")
public class Role {

    @Id //  Primary Key of the entity
    private String rolename;

    // RELATION BETWEEN ROLE & USER
    @ManyToMany(mappedBy = "roles") // This is the target side of the relation with Account. There is nothing in the database.
    private List<User> users;
}
