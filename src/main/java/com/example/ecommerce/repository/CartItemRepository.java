package com.example.ecommerce.repository;

import com.example.ecommerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.constant.ConstantDesc;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    @Query("SELECT ci FROM cart_item ci WHERE ci.user.id = :userId")
    List<CartItem> findAllCartItemByUserId(ConstantDesc userId);
}
