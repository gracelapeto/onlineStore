package com.example.onlineStore.services.impl;

import com.example.onlineStore.entities.Category;
import com.example.onlineStore.repositories.CategoryRepository;
import com.example.onlineStore.services.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category createCategory(CategoryRepository repository) {
        Category category = new Category();
        category.setName(repository.);
    }

}
