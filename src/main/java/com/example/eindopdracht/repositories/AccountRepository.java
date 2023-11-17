package com.example.eindopdracht.repositories;

import com.example.eindopdracht.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository for managing Account entities with Long as the primary key type
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUser_Username(String username);
}
