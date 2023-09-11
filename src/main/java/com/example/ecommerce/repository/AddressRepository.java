package com.example.ecommerce.repository;

import com.example.ecommerce.model.Address;
import com.example.ecommerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.constant.ConstantDesc;
import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT ci FROM Address ci WHERE ci.user.id = :userId")
    List<Address> findAllAddressByUserId(ConstantDesc userId);
}
