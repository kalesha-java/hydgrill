package com.example.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.restaurant.model.Category;
import com.example.restaurant.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    
    @GetMapping("/getbyid/{id}")
    public Category getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }
    
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }
    
    @PostMapping("/restaurant/{restaurantId}")
    public List<Category> createCategories(@RequestBody List<Category> categories, @PathVariable Long restaurantId) {
        return categoryService.createCategories(categories, restaurantId);
    }
    
    @PutMapping("/updatebyid/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }
    
    @DeleteMapping("/deletebyid/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
    
    @GetMapping("/restaurant/{restaurantId}")
    public List<Category> getCategoriesByRestaurant(@PathVariable Long restaurantId) {
        return categoryService.getCategoriesByRestaurant(restaurantId);
    }
}