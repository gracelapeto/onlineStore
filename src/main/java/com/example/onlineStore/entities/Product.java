package com.example.onlineStore.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")

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
