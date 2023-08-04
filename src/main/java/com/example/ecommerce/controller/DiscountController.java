package com.example.ecommerce.controller;

import com.example.ecommerce.model.Discount;
import com.example.ecommerce.model.SEO;
import com.example.ecommerce.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping
    public ResponseEntity<List<Discount>> getAllDiscount() {
        List<Discount> discountList = discountService.getAllDiscounts();
        return new ResponseEntity<>(discountList, HttpStatus.OK);
    }
    @GetMapping("/id")
    public ResponseEntity<Discount> getSEOById(@PathVariable Long id) {
        Discount discount = discountService.getDiscountById(id);
        if (discount != null) {
            return new ResponseEntity<>(discount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<Discount> createDiscount(@RequestBody Discount discount) {
        Discount createdDiscount = discountService.createDiscount(discount);
        return new ResponseEntity<>(createdDiscount, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable Long id) {
        discountService.deletedDiscount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Discount> updateDiscount(@PathVariable Long id, @RequestBody Discount updatedDiscount) {
        updatedDiscount.setId(id);
        Discount updatedDiscountData = discountService.updateDiscount(updatedDiscount);
        if (updatedDiscountData != null) {
            return new ResponseEntity<>(updatedDiscountData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
