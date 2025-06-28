package com.example.onlineStore.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Double totalPrice;
    private LocalDateTime orderDate;
    @ManyToOne
    @JoinColumn(name = "client")
    private User user;







}
