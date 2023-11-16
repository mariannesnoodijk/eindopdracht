package com.example.eindopdracht.repositories;

import com.example.eindopdracht.models.Viewing;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository for managing Viewing entities with Long as the primary key type
public interface ViewingRepository extends JpaRepository<Viewing, Long> {
}
