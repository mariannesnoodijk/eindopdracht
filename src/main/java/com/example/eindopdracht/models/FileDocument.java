package com.example.eindopdracht.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FileDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This specifies that the ID is automatically generated
    private Long id;
    private String fileName;

    @Lob
    private byte[] docFile;
}
