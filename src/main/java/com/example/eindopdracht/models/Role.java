package com.example.eindopdracht.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data // Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Entity
@Table(name="roles")
public class Role {

    @Id //  Primary Key of the entity
    private String rolename;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
