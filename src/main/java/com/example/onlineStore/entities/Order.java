package com.example.onlineStore.entities;

import com.example.onlineStore.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private OrderStatus orderStatus;
    private Double totalPrice;
    private LocalDateTime orderDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;







}
