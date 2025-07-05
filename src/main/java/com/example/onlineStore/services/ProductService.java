package com.example.onlineStore.services;

import com.example.onlineStore.entities.Product;

import java.util.List;

public interface ProductService {
    Product updateProduct(Long id, Product productDetails);

    Product findProductByID(Long id);

    List<Product> findAllProduct();

    List<Product> findProductByTitle(String title);
}
