package com.example.eindopdracht.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data // Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Entity
@Table(name="users")
public class User {

    @Id //  Primary Key of the entity
    @GeneratedValue
    private Long id;
    private String username;
    private String password;

//    @ManyToMany(fetch = FetchType.EAGER) // The FetchType. EAGER option indicates that the associated entity should be fetched eagerly, which means that it will be fetched at the same time as the parent entity.
//    private Set<Role> roles = new HashSet<>();
}
