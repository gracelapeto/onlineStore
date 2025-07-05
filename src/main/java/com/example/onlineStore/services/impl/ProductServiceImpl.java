package com.example.onlineStore.services.impl;

import com.example.onlineStore.dtos.ProductCreateDto;
import com.example.onlineStore.entities.Author;
import com.example.onlineStore.entities.Category;
import com.example.onlineStore.entities.Product;
import com.example.onlineStore.repositories.AuthorRepository;
import com.example.onlineStore.repositories.CategoryRepository;
import com.example.onlineStore.repositories.ProductRepository;
import com.example.onlineStore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;


    public Product createProduct(ProductCreateDto dto) {
        Author author = authorRepository.findById(dto.getAuthorId()).orElseThrow();
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow();
        Product product = new Product();
        product.setAuthor(author);
        product.setCategory(category);
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setDescription(dto.getDescription());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
        product.setTitle(productDetails.getTitle());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());
        return productRepository.save(product);
    }

    @Override
    public Product findProductByID(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findProductByTitle(String title) {
        return productRepository.findAllByTitleContainsIgnoreCase(title);
    }
    @Override
    public List<Product>findProductByCategory(){
        return productRepository.findProductByCategory();
    }
}

