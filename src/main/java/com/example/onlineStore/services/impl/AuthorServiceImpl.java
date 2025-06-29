package com.example.onlineStore.services.impl;

import com.example.onlineStore.entities.Author;
import com.example.onlineStore.entities.Category;
import com.example.onlineStore.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl (AuthorRepository authorRepository){
        this.authorRepository=authorRepository;
    }
    public Author createAuthor(Author author) {
        if (authorRepository.findByFirstName(author.getFirstname()).isPresent()) {
            throw new IllegalArgumentException("Author already exists with name: " + author.getFirstname());
        }
        return authorRepository.save(author);
    }

    public Author updateauthor (Long id, Author authorDetails) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + id));
        Author.setFirstName(authorDetails.getFirstname());
        Author.setLastName(authorDetails.getLastname());
        return authorRepository.save(author);
    }


    public Optional<Author> findAuthorById(Long id) {

        return authorRepository.findById(id);
    }

    public List<Author> findAllAuthors() {

        return authorRepository.findAll();
    }

    public Optional<Object> findAuthorByName(String name) {
        return authorRepository.findByName(name);
    }
}

