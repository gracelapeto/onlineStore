package com.example.onlineStore.controllers;

import com.example.onlineStore.dtos.OrderCreateDto;
import com.example.onlineStore.entities.Order;
import com.example.onlineStore.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor

public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody OrderCreateDto orderDto) {
        Order createdOrder = orderService.create(orderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Order>> findAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }
    @GetMapping("/status")
    public ResponseEntity<List<Order>> findOrdersByStatus(@RequestParam String status) {
        List<Order> orders = orderService.getOrdersByStatusForCurrentUser(status);
        return ResponseEntity.ok(orders);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

}






