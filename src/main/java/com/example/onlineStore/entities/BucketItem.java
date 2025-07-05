package com.example.onlineStore.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BucketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Bucket bucket;

    @ManyToOne
    private Product product;

    private int quantity;
}

