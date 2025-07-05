package com.example.onlineStore.services;

import com.example.onlineStore.entities.Category;
import com.example.onlineStore.repositories.CategoryRepository;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);

    Category updateCategory(Long id, Category categoryDetails);

    Category findById(Long id);

    List<Category> findAll();

    Category findByName(String name);
}
