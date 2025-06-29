package com.example.onlineStore.repositories;

import com.example.onlineStore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {




}
