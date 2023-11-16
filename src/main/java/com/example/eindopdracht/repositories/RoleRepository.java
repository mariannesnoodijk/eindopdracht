package com.example.eindopdracht.repositories;

import com.example.eindopdracht.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository for managing Role entities with String as the primary key type
public interface RoleRepository extends JpaRepository<Role, String> {
}
