package com.example.onlineStore.services;

import com.example.onlineStore.entities.OrderLine;

import java.util.List;
import java.util.Optional;

public interface OrderLineService {
    List<OrderLine> findAll();
    OrderLine findById(Long id);
    OrderLine save(Long productId, Integer quantity);

    void deleteById(Long id);
    List<OrderLine> findByOrderId(Long orderId);
}