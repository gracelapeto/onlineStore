package com.example.onlineStore.controllers;

import com.example.onlineStore.entities.Author;
import com.example.onlineStore.entities.Product;
import com.example.onlineStore.services.AuthorService;
import com.example.onlineStore.services.impl.AuthorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
    public class AuthorController {
        private final AuthorService authorService;
        @GetMapping
        public List<Author> getAllAuthors() {
            return authorService.getAllAuthor();
        }
        @GetMapping("/{id}")
        public Author getAuthorById(@PathVariable Long id) {
            return authorService.getAuthorById(id);
        }
        @PostMapping
        public Author createAuthor(@RequestBody Author author) {
            return authorService.createAuthor(author);
        }
        @PutMapping("/{id}")
        public Author updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
            return authorService.updateAuthor(id, authorDetails);
        }
        @DeleteMapping("/{id}")
        public void deleteAuthor(@PathVariable Long id) {
            authorService.deleteAuthor(id);
        }
        // Opsionale: Merr të gjitha produktet e këtij autori
        @GetMapping("/{id}/products")
        public List<Product> getProductsByAuthor(@PathVariable Long id) {
            return authorService.getProductsByAuthor(id);
        }
    }
