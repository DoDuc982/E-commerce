package com.example.ecommerce.controller.user;

import com.example.ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/api/cart")
public class CartController {
    @Autowired
    private final CartItemService cartItemService;

    public CartController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<Void> removeFromCart(
            @PathVariable Long userId,
            @PathVariable Long productId
    ) {
        cartItemService.removeFromCart(userId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{userId}/updateQuantity/{productId}")
    public ResponseEntity<Void> updateCartItem(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestParam Integer quantity
    ) {
        cartItemService.updateCartItem(userId, productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
