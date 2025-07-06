package com.example.onlineStore.services;

import com.example.onlineStore.dtos.OrderCreateDto;
import com.example.onlineStore.entities.Order;
import com.example.onlineStore.enums.OrderStatus;
import jakarta.annotation.Nullable;

import java.util.List;

public interface OrderService {


    Order create(OrderCreateDto dto);

    List<Order> findAll();


    Order findById(Long id);

    void delete(Long id);

    List<Order> getOrdersByStatusForCurrentUser(String status);
}
