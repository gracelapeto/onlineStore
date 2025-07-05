package com.example.onlineStore.repositories;

import com.example.onlineStore.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    List<OrderLine> findByOrderId(Long orderId);
}