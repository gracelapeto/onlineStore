package com.example.onlineStore.controllers;

import com.example.onlineStore.entities.Author;
import com.example.onlineStore.services.AuthorService;
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
            return authorService.findAllAuthors();
        }
        @GetMapping("/{id}")
        public Author getAuthorById(@PathVariable Long id) {
            return authorService.findAuthorById(id);
        }
        @PostMapping
        public Author createAuthor(@RequestBody Author author) {
            return authorService.createAuthor(author);
        }
        @PutMapping("/{id}")
        public Author updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
            return authorService.updateAuthor(id, authorDetails);
        }
    }
