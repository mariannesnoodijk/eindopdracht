package com.example.eindopdracht.repositories;

import com.example.eindopdracht.models.Property;
import com.example.eindopdracht.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {
}
