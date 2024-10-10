package com.example.restaurant.controller;

import com.example.restaurant.model.CartItem;
import com.example.restaurant.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-item")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestParam Long cartId, @RequestParam Long mealId, @RequestParam int quantity) {
        CartItem cartItem = cartItemService.addItemToCart(cartId, mealId, quantity);
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartItemId) {
        cartItemService.removeItemFromCart(cartItemId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<CartItem> updateItemQuantity(@PathVariable Long cartItemId, @RequestParam int quantity) {
        CartItem cartItem = cartItemService.updateItemQuantity(cartItemId, quantity);
        return ResponseEntity.ok(cartItem);
    }
}
