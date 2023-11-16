package com.example.eindopdracht.repositories;

import com.example.eindopdracht.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository for managing User entities with String as the primary key type
public interface UserRepository extends JpaRepository<User, String> {
}
