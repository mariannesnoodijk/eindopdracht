package com.example.eindopdracht.repositories;

import com.example.eindopdracht.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository for managing Property entities with Long as the primary key type
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
