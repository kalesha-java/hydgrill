package com.example.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.model.Meal;
import com.example.restaurant.service.MealService;

@RestController
@RequestMapping("/api/meals")
public class MealController {
    @Autowired
    private MealService mealService;
    
    @GetMapping
    public List<Meal> getAllMeals() {
        return mealService.getAllMeals();
    }

    @GetMapping("/getbyid/{id}")
    public Meal getMeal(@PathVariable Long id) {
        return mealService.getMeal(id);
    }
    
    @GetMapping("/getbyflag/{flag}")
    public List<Meal> getMealByFlag(@PathVariable String flag) {
        return mealService.getMealByFlag(flag);
    }
    
    @PostMapping("/category/{categoryId}")
    public Meal createMeal(@PathVariable Long categoryId, @RequestBody Meal meal) {
        return mealService.createMeal(categoryId, meal);
    }
    
    
    
    @PostMapping("/bulk/category/{categoryId}")
    public List<Meal> createMeals(@PathVariable Long categoryId, @RequestBody List<Meal> meals) {
        return mealService.createMeals(categoryId, meals);
    }
    
    
    
    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<Meal> updateMeal(@PathVariable Long id, @RequestBody Meal mealDetails) {
        Meal updatedMeal = mealService.updateMeal(id, mealDetails);
        if (updatedMeal != null) {
            return ResponseEntity.ok(updatedMeal);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    
    
    @DeleteMapping("/deletebyid/{id}")
    public void deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
    }
    
    @GetMapping("/category/{categoryId}/item/{itemId}")
    public Meal getMealByCategoryAndId(@PathVariable Long categoryId, @PathVariable Long itemId) {
        return mealService.getMealByCategoryAndId(categoryId, itemId);
    }
    
//    @GetMapping("/category/{categoryId}")
//    public List<Meal> getMealsByCategory(@PathVariable Long categoryId) {
//        return mealService.getMealsByCategory(categoryId);
//    }
    
    @GetMapping("/category/{categoryName}")
    public List<Meal> getMealsByCategory(@RequestParam(required = false, defaultValue = "itemName") String sortBy, @PathVariable String categoryName) {
        return mealService.getMealsByCategoryName(categoryName, sortBy);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Meal>> searchMealsByName(@RequestParam String itemName) {
        return mealService.searchMealsByName(itemName);
    }
}
