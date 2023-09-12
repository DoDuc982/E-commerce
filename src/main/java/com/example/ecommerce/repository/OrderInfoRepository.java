package com.example.ecommerce.repository;

import com.example.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderInfoRepository extends JpaRepository<Order, Long> {
    @Query("SELECT ci FROM OrderInfo ci WHERE ci.user.id = :userId")
    List<Order> findAllOrdersByUserId(@Param("userId") Long userId);
}
