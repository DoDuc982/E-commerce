package com.example.ecommerce.service;

import com.example.ecommerce.DTO.mapper.Mapper;
import com.example.ecommerce.DTO.response.CartItemResponseDTO;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartItemRepository;
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

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }

    public CartItem getCartItem(Long id){
        return cartItemRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("could not find cart item with id: " + id));
    }
    public List<CartItemResponseDTO> getAllCartItem(Long userId){
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<CartItem> cartItems = user.getCartItems();
            return cartItems.stream()
                    .map(Mapper::cartItemToCartItemResponseDTO)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }
    public CartItem addToCart(User user, Product product, Integer quantity){
        for (CartItem cartItem : user.getCartItems()) {
            if (cartItem.getProduct().equals(product)) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                return cartItemRepository.save(cartItem);
            }
        }
        CartItem cartItem = CartItem.builder()
                .user(user)
                .product(product)
                .quantity(quantity)
                .build();
        return cartItemRepository.save(cartItem);
    }
    public void updateCartItem(Long userId, Long productId, Integer quantity) {
        List<CartItem> cartItems = new ArrayList<>();
        for (CartItem cartItem : cartItemRepository.findAll()){
            if (Objects.equals(cartItem.getUser().getId(), userId)){
                cartItems.add(cartItem);
            }
        }
        for (CartItem cartItem : cartItems) {
            if (Objects.equals(cartItem.getProduct().getId(), productId)) {
                cartItem.setQuantity(quantity);
                cartItemRepository.save(cartItem);
            }
        }
    }
    public void removeFromCart(Long userId, Long productId) {
        List<CartItem> cartItems = new ArrayList<>();
        for (CartItem cartItem : cartItemRepository.findAll()){
            if (Objects.equals(cartItem.getUser().getId(), userId)){
                cartItems.add(cartItem);
            }
        }
        for (CartItem cartItem : cartItems) {
            if (Objects.equals(cartItem.getProduct().getId(), productId)) {
                cartItemRepository.delete(cartItem);
            }
        }
    }
}
