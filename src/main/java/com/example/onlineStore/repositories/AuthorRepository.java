package com.example.onlineStore.repositories;

import com.example.onlineStore.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long>{
    Optional<Object> findByFirstName(String firstname);

    Optional<Object> findByName(String name);
}
