package com.example.onlineStore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tables")

public class Product {
    @Id
    private String Title;
    private String Description;
    private Long price;
}
