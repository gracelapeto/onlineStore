package com.example.onlineStore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table (name = "authors")

public class Author {
    @Id
    private Long id;
    private String name;
    private String lastname;

}
