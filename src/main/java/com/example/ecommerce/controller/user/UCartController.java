package com.example.ecommerce.controller.user;

import com.example.ecommerce.DTO.response.CartItemResponseDTO;
import com.example.ecommerce.config.JwtGenerator;
import com.example.ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/api/cart")
public class UCartController {
    @Autowired
    private final CartItemService cartItemService;
    @Autowired
    private final JwtGenerator jwtGenerator;

    public UCartController(CartItemService cartItemService, JwtGenerator jwtGenerator) {
        this.cartItemService = cartItemService;
        this.jwtGenerator = jwtGenerator;
    }
    @GetMapping()
    public ResponseEntity<List<CartItemResponseDTO>> getAllCartItem(HttpServletRequest request) {
        String token = jwtGenerator.getJwtFromRequest(request);
        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
            Long userId = jwtGenerator.getUserIdFromJwt(token); // Lấy ID người dùng từ JWT
            List<CartItemResponseDTO> cartItems = cartItemService.getAllCartItem(userId);
            return ResponseEntity.ok(cartItems);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.emptyList());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeFromCart(
            @PathVariable Long productId,
            HttpServletRequest request
    ) {
        String token = jwtGenerator.getJwtFromRequest(request);
        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
            Long userId = jwtGenerator.getUserIdFromJwt(token); // Lấy ID người dùng từ JWT
            cartItemService.removeFromCart(userId, productId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateCartItem(
            @PathVariable Long productId,
            @RequestBody Map<String, Integer> requestBody,
            HttpServletRequest request
    ) {
        String token = jwtGenerator.getJwtFromRequest(request);
        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
            Long userId = jwtGenerator.getUserIdFromJwt(token); // Lấy ID người dùng từ JWT
            Integer quantity = requestBody.get("quantity");
            cartItemService.updateCartItem(userId, productId, quantity);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Void> addCartItem(
            @PathVariable Long productId,
            @RequestBody Map<String, Integer> requestBody,
            HttpServletRequest request
    ) {
        String token = jwtGenerator.getJwtFromRequest(request);
        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
            Long userId = jwtGenerator.getUserIdFromJwt(token); // Lấy ID người dùng từ JWT
            Integer quantity = requestBody.get("quantity");
            cartItemService.addToCart(userId, productId, quantity);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
