package com.example.restaurant.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.restaurant.model.Category;
import com.example.restaurant.model.Meal;
import com.example.restaurant.repository.CategoryRepository;
import com.example.restaurant.repository.MealRepository;

@Service
public class MealService {
    @Autowired
    private MealRepository mealRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }
    
    public Meal getMeal(Long id) {
        return mealRepository.findById(id).orElse(null);
    }
    
    public Meal createMeal(Long categoryId, Meal meal) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found"));
        meal.setCategory(category);
        return mealRepository.save(meal);
    }
    
    public List<Meal> createMeals(Long categoryId, List<Meal> meals) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found"));
        meals.forEach(meal -> meal.setCategory(category));
        return mealRepository.saveAll(meals);
    }
    
    
    public Meal updateMeal(Long id, Meal mealDetails) {
        return mealRepository.findById(id)
                .map(existingMeal -> {
                    existingMeal.setItemName(mealDetails.getItemName());
                    existingMeal.setDiscountPercentage(mealDetails.getDiscountPercentage());
                    existingMeal.setOriginalPrice(mealDetails.getOriginalPrice());
                    existingMeal.setPriceAfterDiscount(mealDetails.getPriceAfterDiscount());
                    existingMeal.setRating(mealDetails.getRating());
                    existingMeal.setStrMealThumb(mealDetails.getStrMealThumb());
                    // Update category only if provided
                    if (mealDetails.getCategory() != null && mealDetails.getCategory().getId() != null) {
                        Optional<Category> category = categoryRepository.findById(mealDetails.getCategory().getId());
                        category.ifPresent(existingMeal::setCategory);
                    }

                    return mealRepository.save(existingMeal);
                })
                .orElse(null); // Return null if the meal with the given id does not exist
    }
    
    
     
    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }
    
    public Meal getMealByCategoryAndId(Long categoryId, Long itemId) {
        return mealRepository.findByCategory_IdAndId(categoryId, itemId)
            .orElseThrow(() -> new RuntimeException("Meal not found"));
    }
    
//    public List<Meal> getMealsByCategory(Long categoryId) {
//        return mealRepository.findByCategoryId(categoryId);
//    }
    
    public ResponseEntity<List<Meal>> searchMealsByName(String itemName) {
        List<Meal> meals = mealRepository.findByItemNameContaining(itemName);
        return new ResponseEntity<>(meals, HttpStatus.OK);
    }

	public List<Meal> getMealsByCategoryName(String categoryName, String sortBy) {
		Long categoryId = categoryRepository.findAll().stream().filter(e -> e.getStrCategory().equals(categoryName)).findFirst().get().getId();
		Sort sort;
        switch (sortBy.toLowerCase()) {
            case "rating":
                sort = Sort.by(Sort.Direction.DESC, "rating");
                break;
            case "price_low_to_high":
                sort = Sort.by(Sort.Direction.ASC, "priceAfterDiscount");
                break;
            case "price_high_to_low":
                sort = Sort.by(Sort.Direction.DESC, "priceAfterDiscount");
                break;
            case "date":
                // Assuming you have a date field in your Meal entity. 
                // Replace "createdDate" with your actual field name.
                sort = Sort.by(Sort.Direction.DESC, "createdDate");
                break;
            default:
                sort = Sort.by(Sort.Direction.ASC, "itemName"); // Default sort
        }
        System.out.println(sort);
        return mealRepository.findByCategoryId(categoryId, sort);
	}

	public List<Meal> getMealByFlag(String flag) {
		return mealRepository.getMealByFlag(flag);
	}
}
