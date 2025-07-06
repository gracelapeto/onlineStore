package com.example.onlineStore.services.impl;

import com.example.onlineStore.entities.Author;
import com.example.onlineStore.entities.Product;
import com.example.onlineStore.exception.OnlineStoreException;
import com.example.onlineStore.repositories.AuthorRepository;
import com.example.onlineStore.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }
    @Override
    public Author updateAuthor(Long id, Author authorDetails) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> OnlineStoreException.notFound(Author.class, id.toString()));
        author.setFirstname (authorDetails.getFirstname());
        author.setLastname (authorDetails.getLastname());
        return authorRepository.save(author);
    }

    @Override
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> findAuthorByName(String name) {
        String search = "%".concat(name).concat("%");
        return authorRepository.findByName(search);
    }

    @Override
    public List<Product> getAllByAuthor(Long id) {
        return List.of();
    }
}

