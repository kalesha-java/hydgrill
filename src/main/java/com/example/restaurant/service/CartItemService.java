package com.example.restaurant.service;

import com.example.restaurant.model.Cart;
import com.example.restaurant.model.CartItem;
import com.example.restaurant.repository.CartItemRepository;
import com.example.restaurant.repository.CartRepository;
import com.example.restaurant.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MealRepository mealRepository;

    @Transactional
    public CartItem addItemToCart(Long cartId, Long mealId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setMeal(mealRepository.findById(mealId).orElseThrow(() -> new RuntimeException("Meal not found")));
        cartItem.setQuantity(quantity);
        cartItem.setPrice(cartItem.getMeal().getOriginalPrice());
        cartItem.setDiscount(cartItem.getMeal().getPriceAfterDiscount());

        return cartItemRepository.save(cartItem);
    }

    @Transactional
    public void removeItemFromCart(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("CartItem not found"));
        cartItemRepository.delete(cartItem);
    }

    @Transactional
    public CartItem updateItemQuantity(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("CartItem not found"));
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }
}
