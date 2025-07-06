package com.example.onlineStore.repositories;

import com.example.onlineStore.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long>{

    @Query("select a from Author a where concat(a.firstName, ' ', a.lastname) like :name")
    List<Author> findByName(String name);
}

