package com.wo.bookservice.respository;

import com.wo.bookservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByIsActiveTrue();
}
