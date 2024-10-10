package com.example.restaurant.controller;

import com.example.restaurant.model.Cart;
import com.example.restaurant.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<Cart> addItemToCart(@PathVariable Long userId, @RequestParam Long mealId, @RequestParam int quantity) {
        Cart cart = cartService.addItemToCart(userId, mealId, quantity);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{userId}/remove/{cartItemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long userId, @PathVariable Long cartItemId) {
        cartService.removeItemFromCart(userId, cartItemId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}/update/{cartItemId}")
    public ResponseEntity<Cart> updateItemQuantity(@PathVariable Long userId, @PathVariable Long cartItemId, @RequestParam int quantity) {
        Cart cart = cartService.updateItemQuantity(userId, cartItemId, quantity);
        return ResponseEntity.ok(cart);
    }
}
