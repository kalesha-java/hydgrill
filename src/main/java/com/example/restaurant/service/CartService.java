package com.example.restaurant.service;

import com.example.restaurant.model.*;
import com.example.restaurant.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId).orElse(null);
    }

    @Transactional
    public Cart addItemToCart(Long userId, Long mealId, int quantity) {
        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart());

        Meal meal = mealRepository.findById(mealId).orElseThrow(() -> new RuntimeException("Meal not found"));
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setMeal(meal);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(meal.getOriginalPrice());
        cartItem.setDiscount(meal.getDiscountPercentage());

        cart.getItems().add(cartItem);
        updateTotalPrice(cart);
        return cartRepository.save(cart);
    }

    @Transactional
    public void removeItemFromCart(Long userId, Long cartItemId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Cart not found"));
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("CartItem not found"));

        cart.getItems().remove(cartItem);
        updateTotalPrice(cart);
        cartRepository.save(cart);
    }

    @Transactional
    public Cart updateItemQuantity(Long userId, Long cartItemId, int quantity) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Cart not found"));
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("CartItem not found"));

        cartItem.setQuantity(quantity);
        updateTotalPrice(cart);
        return cartRepository.save(cart);
    }

    private void updateTotalPrice(Cart cart) {
        double total = cart.getItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getPrice() * (1 - item.getDiscount()))
                .sum();
        cart.setTotalPrice(total);
    }
}
