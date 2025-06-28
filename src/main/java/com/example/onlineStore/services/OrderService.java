package com.example.onlineStore.services;

import com.example.onlineStore.dtos.OrderCreateDto;
import com.example.onlineStore.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {


    Order create(OrderCreateDto dto);

    List<Order> findAll();


}
