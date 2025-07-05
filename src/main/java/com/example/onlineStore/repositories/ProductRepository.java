package com.example.onlineStore.repositories;

import com.example.onlineStore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByTitleContainsIgnoreCase(String title);

    List<Product> findProductByCategory();
}

