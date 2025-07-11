package com.example.onlineStore.services.impl;

import com.example.onlineStore.dtos.OrderCreateDto;
import com.example.onlineStore.entities.Bucket;
import com.example.onlineStore.entities.Order;
import com.example.onlineStore.entities.OrderLine;
import com.example.onlineStore.entities.User;
import com.example.onlineStore.enums.OrderStatus;
import com.example.onlineStore.exception.OnlineStoreException;
import com.example.onlineStore.repositories.OrderLineRepository;
import com.example.onlineStore.repositories.OrderRepository;
import com.example.onlineStore.services.OrderService;
import com.example.onlineStore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderLineRepository orderLineRepository;
    @Autowired
    private UserService userService;

    @Override
    public Order create(OrderCreateDto dto){
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setName(dto.getName());
        order.setAddress(dto.getAddress());
        Order savedOrder = orderRepository.save(order);
        AtomicReference<Double> totalPrice = new AtomicReference<>(0.0);

        List<OrderLine> orderLines =orderLineRepository.findAllById(dto.getOrderLinesIds());
        orderLines.forEach(orderLine->{
            orderLine.setOrder(savedOrder);
            totalPrice.set(totalPrice.get()+(orderLine.getProduct().getPrice() * orderLine.getQuantity()));


        });
        savedOrder.setTotalPrice(totalPrice.get());
        orderRepository.save(savedOrder);
        orderLineRepository.saveAll(orderLines);
        return savedOrder;


    }

    @Override
    public List<Order> findAll(){
        return orderRepository.findAll();
    }
    @Override
    public Order findById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(() ->  OnlineStoreException.notFound(Order.class,id.toString()));
    }
    @Override
    public void delete(Long id){
        orderRepository.deleteById(id);
    }

//to be seen
    private Order builOrderFromBucket(Bucket bucket){
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.NEW);
        return order;
    }
    @Override
    public List<Order> getOrdersByStatusForCurrentUser(String status) {
        User user = userService.getLoggedUser();
        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
        return orderRepository.findByUserAndOrderStatus(user, orderStatus);
    }
    }





