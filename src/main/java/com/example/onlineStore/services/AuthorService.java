package com.example.onlineStore.services;

import com.example.onlineStore.entities.Author;
import com.example.onlineStore.entities.Product;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author createAuthor(Author author);

    Author updateAuthor(Long id, Author authorDetails);

    Author findAuthorById(Long id);

    List<Author> findAllAuthors();

    List<Author> findAuthorByName(String name);

    List<Product> getAllByAuthor(Long id);
}
