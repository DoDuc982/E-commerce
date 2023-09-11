package com.example.ecommerce.service;

import com.example.ecommerce.DTO.mapper.Mapper;
import com.example.ecommerce.DTO.response.CartItemResponseDTO;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }
    public List<CartItemResponseDTO> getAllCartItem(Long userId){
        return cartItemRepository.findAllCartItemByUserId(userId).stream()
                .map(Mapper::cartItemToCartItemResponseDTO)
                .collect(Collectors.toList());
    }
    public void addToCart(Long userId, Long productId, Integer quantity){
        for (CartItem cartItem : cartItemRepository.findAll()) {
            if (Objects.equals(cartItem.getUser().getId(), userId)) {
                if (cartItem.getProduct().getId().equals(productId)) {
                    cartItem.setQuantity(cartItem.getQuantity() + quantity);
                    cartItemRepository.save(cartItem);
                    return;
                }
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(quantity);
        cartItem.setUser(userRepository.findById(userId).orElse(null));
        cartItem.setProduct(productRepository.findById(productId).orElse(null));
        if (cartItem.getUser() == null || cartItem.getProduct() == null) return;
        cartItemRepository.save(cartItem);
    }
    public void updateCartItem(Long userId, Long productId, Integer quantity) {
        List<CartItem> cartItems = cartItemRepository.findAllCartItemByUserId(userId);
        for (CartItem cartItem : cartItems) {
            if (Objects.equals(cartItem.getProduct().getId(), productId)) {
                cartItem.setQuantity(quantity);
                cartItemRepository.save(cartItem);
            }
        }
    }
    public void removeFromCart(Long userId, Long productId) {
        List<CartItem> cartItems = cartItemRepository.findAllCartItemByUserId(userId);
        for (CartItem cartItem : cartItems) {
            if (Objects.equals(cartItem.getProduct().getId(), productId)) {
                cartItemRepository.delete(cartItem);
            }
        }
    }
}
