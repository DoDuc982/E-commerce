package com.example.ecommerce.repository;

import com.example.ecommerce.model.ShopInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopInfoRepository extends JpaRepository<ShopInfo, Long> {
}
