package com.wo.bookservice.controller;

import com.wo.bookservice.model.Book;
import com.wo.bookservice.model.Category;
import com.wo.bookservice.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping("/all") //Lista de todas las categorias
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}") //Busqueda de una categoria
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping("/save") //Guardado de categorias
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.save(category));
    }

    @PutMapping("/update/{id}") //Actualizacion de categoria
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        Category existingCategory = categoryService.findById(id);
        existingCategory.setName(category.getName());
        existingCategory.setActive(category.isActive());
        return ResponseEntity.ok(categoryService.save(existingCategory));
    }

    @DeleteMapping("/delete/{id}") //Eliminacion de categoria
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/activeCategories") //Lista de categorias activas
    public ResponseEntity<List<Category>> findActiveCategories() {
        return ResponseEntity.ok(categoryService.findActiveCategories());
    }

    @PutMapping("/updateActiveStatus/{id}") // Actualiza el estado activo/inactivo de categoria
    public ResponseEntity<Category> updateActiveStatus(@PathVariable Long id, @RequestParam boolean isActive) {
        return ResponseEntity.ok(categoryService.updateActiveStatus(id, isActive));
    }
}
