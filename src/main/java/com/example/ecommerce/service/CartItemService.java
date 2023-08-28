package com.example.ecommerce.service;

import com.example.ecommerce.DTO.mapper.Mapper;
import com.example.ecommerce.DTO.response.CartItemResponseDTO;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;


    public CartItemService(CartItemRepository cartItemRepository, UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }

    public CartItem getCartItem(Long id){
        return cartItemRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("could not find cart item with id: " + id));
    }
    public List<CartItemResponseDTO> getAllCartItem(Long userId){
        List<CartItemResponseDTO> cartItemResponseDTOS = new ArrayList<>();
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<CartItem> cartItems = user.getCartItems();
            for (CartItem cartItem : cartItemRepository.findAll().stream().toList()) {
                if (cartItem.getUser().getId() == userId){
                    cartItemResponseDTOS.add(Mapper.cartItemToCartItemResponseDTO(cartItem));
                }
            }
            return cartItemResponseDTOS;
        } else return null;
    }
}
