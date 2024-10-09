package com.wo.bookservice.service;

import com.wo.bookservice.model.Book;
import com.wo.bookservice.model.Category;
import com.wo.bookservice.respository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService{

    private final ICategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category can't be found with id: " + id));
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category can't be found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> findActiveCategories() {
        return categoryRepository.findByIsActiveTrue();
    }

    @Override
    public Category updateActiveStatus(Long id, boolean isActive) {
        Category category = findById(id);
        category.setActive(isActive);
        return categoryRepository.save(category);
    }
}
