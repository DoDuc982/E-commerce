package com.example.ecommerce.controller.user;

import com.example.ecommerce.DTO.response.CartItemResponseDTO;
import com.example.ecommerce.security.JwtGenerator;
import com.example.ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/api/cart")
public class UCartController {
    @Autowired
    private final CartItemService cartItemService;

    public UCartController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItemResponseDTO>> getAllCartItem(@PathVariable Long userId){
        return new ResponseEntity<>(cartItemService.getAllCartItem(userId), HttpStatus.OK);
    }
    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<Void> removeFromCart(
            @PathVariable Long userId,
            @PathVariable Long productId
    ) {
        cartItemService.removeFromCart(userId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{userId}/update_product/{productId}")
    public ResponseEntity<Void> updateCartItem(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestBody Map<String, Integer> requestBody
    ) {
        Integer quantity = requestBody.get("quantity");
        cartItemService.updateCartItem(userId, productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/{userId}/add_product/{productId}")
    public ResponseEntity<Void> addCartItem(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestBody Map<String, Integer> requestBody
    ) {
        Integer quantity = requestBody.get("quantity");
        cartItemService.addToCart(userId, productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
