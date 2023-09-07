package com.example.ecommerce.controller.admin;

import com.example.ecommerce.DTO.response.CartItemResponseDTO;
import com.example.ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/api/cart_item")
public class CartItemController {
    private final CartItemService cartItemService;
    @Autowired
    public CartItemController(CartItemService cartItemService){
        this.cartItemService = cartItemService;
    }
    @GetMapping("/{user_id}")
    public ResponseEntity<List<CartItemResponseDTO>> getAllCartItemOfAnUser(@PathVariable Long userId){
        if (cartItemService.getAllCartItem(userId) != null) {
            return new ResponseEntity<>(cartItemService.getAllCartItem(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
