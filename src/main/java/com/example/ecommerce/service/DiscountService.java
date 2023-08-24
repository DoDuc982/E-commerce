package com.example.ecommerce.service;

import com.example.ecommerce.model.Discount;
import com.example.ecommerce.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DiscountService {
    private final DiscountRepository discountRepository;
    @Autowired
    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

}
