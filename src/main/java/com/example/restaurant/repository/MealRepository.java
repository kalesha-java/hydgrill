package com.example.restaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.restaurant.model.Meal;

public interface MealRepository extends JpaRepository<Meal, Long> {
	List<Meal> findByCategoryId(Long categoryId, Sort sort);
    Optional<Meal> findByCategory_IdAndId(Long categoryId, Long itemId);
    List<Meal> findByItemNameContaining(String itemName);
    
    @Query(nativeQuery = true, value="select * from meal where flag=:flag")
	List<Meal> getMealByFlag(String flag);
}
