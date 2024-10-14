package com.wo.bookservice.service;

import com.wo.bookservice.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();

    Category findById(Long id);

    Category save(Category category);

    void deleteById(Long id);

    List<Category> findActiveCategories();

    Category updateActiveStatus(Long id, boolean isActive);
}
