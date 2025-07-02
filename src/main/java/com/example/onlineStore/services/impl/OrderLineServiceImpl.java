package com.example.onlineStore.services.impl;

import com.example.onlineStore.entities.OrderLine;
import com.example.onlineStore.entities.Product;
import com.example.onlineStore.repositories.OrderLineRepository;
import com.example.onlineStore.repositories.ProductRepository;
import com.example.onlineStore.services.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineServiceImpl implements OrderLineService {

    @Autowired
    private OrderLineRepository orderLineRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<OrderLine> findAll() {
        return orderLineRepository.findAll();
    }

    @Override
    public OrderLine findById(Long id) {
        return orderLineRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Order Line Not Found"));
    }

    @Override
    public OrderLine save(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        OrderLine orderLine = new OrderLine();
        orderLine.setProduct(product);
        orderLine.setQuantity(quantity);
        return orderLineRepository.save(orderLine);


    }

    @Override
    public void deleteById(Long id) {
        orderLineRepository.deleteById(id);
    }

    @Override
    public List<OrderLine> findByOrderId(Long orderId) {
        return orderLineRepository.findByOrderId(orderId);
    }
    public String getProductNameById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return product.getTitle();
    }

}
