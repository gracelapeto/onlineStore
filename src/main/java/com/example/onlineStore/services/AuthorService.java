package com.example.onlineStore.services;

import com.example.onlineStore.entities.Author;
import com.example.onlineStore.entities.Product;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author createAuthor(Author author);

    Author updateAuthor(Long id, Author authorDetails);

    Optional<Author> findAuthorById(Long id);

    List<Author> findAllAuthors();

    Optional<Object> findAuthorByName(String name);

    List<Author> getAllAuthor();

    Author getAuthorById(Long id);

    void deleteAuthor(Long id);

    List<Product> getProductsByAuthor(Long id);
}
