package com.example.onlineStore.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tables")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Double price;
    private Integer quantity;
    @ManyToOne
    @JoinColumn (name = "author")
    private Author author;
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;
}
