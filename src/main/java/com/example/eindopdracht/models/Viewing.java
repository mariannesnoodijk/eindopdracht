package com.example.eindopdracht.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

// Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Data
@Entity
@Table(name = "viewings")
public class Viewing {

    // Primary key for the Viewing entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long viewingId;

    // Details of the person attending the viewing
    private String fullname;
    private String phonenumber;
    private String email;

    // Date and time of the viewing
    private LocalDate date;
    private LocalTime time;

    // Many-to-one relationship with the Account entity
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;
}
