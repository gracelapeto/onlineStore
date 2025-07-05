package com.example.onlineStore.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "author")

public class Author {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;

    public static void setFirstName(String firstname) {
    }

    public static void setLastName(String lastname) {
    }
}
