package com.example.onlineStore.services;

import com.example.onlineStore.entities.Category;
import com.example.onlineStore.repositories.CategoryRepository;

public interface CategoryService {
    Category createCategory(Category category);

    Category updateCategory(Long id, Category categoryDetails);

}
