package com.example.eindopdracht.models;

import jakarta.persistence.Table;
import lombok.Data;

@Data // Lombok imports automatically the Constructor, Getters and Setter by using @Data
@Table(name="accounts")
public class Account {


}
