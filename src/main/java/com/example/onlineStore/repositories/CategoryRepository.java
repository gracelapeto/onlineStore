package com.example.onlineStore.repositories;

import com.example.onlineStore.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category>findByNameIgnoreCase(String name);

}
