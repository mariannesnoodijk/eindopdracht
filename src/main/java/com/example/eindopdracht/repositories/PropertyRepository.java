package com.example.eindopdracht.repositories;

import com.example.eindopdracht.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
