package com.example.onlineStore.repositories;

import com.example.onlineStore.entities.Order;
import com.example.onlineStore.entities.User;
import com.example.onlineStore.enums.OrderStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    List<Order> findByUserAndOrderStatus(User user, OrderStatus status);








}
