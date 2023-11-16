package com.example.eindopdracht.repositories;

import com.example.eindopdracht.models.FileDocument;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository for managing FileDocument entities with Long as the primary key type
@Transactional
public interface DocFileRepository extends JpaRepository<FileDocument, Long> {
    // Custom query method to find a FileDocument by its file name
    FileDocument findByFileName(String fileName);
}
