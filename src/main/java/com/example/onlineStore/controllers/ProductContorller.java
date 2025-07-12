package com.example.onlineStore.controllers;

import com.example.onlineStore.dtos.ProductCreateDto;
import com.example.onlineStore.entities.Category;
import com.example.onlineStore.entities.Product;
import com.example.onlineStore.services.AuthorService;
import com.example.onlineStore.services.CategoryService;
import com.example.onlineStore.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/products")
public class ProductContorller {

    private final ProductService productService;
    private final AuthorService authorService;

    public ProductContorller(ProductService productService, AuthorService authorService) {
        this.productService = productService;
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductCreateDto dto) {
        Product created = productService.createProduct(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product updated = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.findProductByID(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProduct());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(productService.findProductByTitle(title));
    }

    @GetMapping("/by-category")
    public ResponseEntity<List<Product>> findByCategory(@RequestParam Long categoryId) {
        return ResponseEntity.ok(productService.findProductByCategory(categoryId));
    }
    @GetMapping("/products/{authorId}" )
    public List<Product> getAllProductByAuthor(@PathVariable Long authorId) {
        return authorService.getAllByAuthor(authorId);
    }
}






