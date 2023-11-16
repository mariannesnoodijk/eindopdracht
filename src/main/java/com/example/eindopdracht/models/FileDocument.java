package com.example.eindopdracht.models;

import jakarta.persistence.*;
import lombok.Data;

// Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Entity
@Data
public class FileDocument {

    // Primary key for the FileDocument entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This specifies that the ID is automatically generated
    private Long id;
    private String fileName;

    // The content of the file stored as a byte array
    @Lob
    private byte[] docFile;
}
