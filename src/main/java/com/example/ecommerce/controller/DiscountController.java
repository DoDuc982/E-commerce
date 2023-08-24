package com.example.ecommerce.controller;

import com.example.ecommerce.model.Discount;
import com.example.ecommerce.model.SEO;
import com.example.ecommerce.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
public class DiscountController {
    private final DiscountService discountService;
    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

}
