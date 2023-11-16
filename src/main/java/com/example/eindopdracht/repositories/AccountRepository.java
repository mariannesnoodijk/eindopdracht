package com.example.eindopdracht.repositories;

import com.example.eindopdracht.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository for managing Account entities with Long as the primary key type
public interface AccountRepository extends JpaRepository<Account, Long> {
}
