package com.example.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.model.Category;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.CategoryRepository;
import com.example.restaurant.repository.RestaurantRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
    
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
    
    public List<Category> createCategories(List<Category> categories, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        
        categories.forEach(category -> category.setRestaurant(restaurant));
        
        return categoryRepository.saveAll(categories);
    }
    
    public Category updateCategory(Long id, Category category) {
        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    existingCategory.setStrCategory(category.getStrCategory());
                    existingCategory.setStrCategoryThumb(category.getStrCategoryThumb());
                    existingCategory.setStrCategoryDescription(category.getStrCategoryDescription());
                    return categoryRepository.save(existingCategory);
                })
                .orElse(null);
    }
    
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
    
    public List<Category> getCategoriesByRestaurant(Long restaurantId) {
        return categoryRepository.findByRestaurantId(restaurantId);
    }
}
