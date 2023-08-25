package com.example.ecommerce.controller.admin;

import com.example.ecommerce.model.Discount;
import com.example.ecommerce.model.SEO;
import com.example.ecommerce.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api/discount")
public class DiscountController {
    private final DiscountService discountService;
    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }
    @PostMapping
    public ResponseEntity<Discount> createDiscount(@RequestBody Discount discount){
        Discount createdDiscount = discountService.createDiscount(discount);
        if (createdDiscount != null){
            return new ResponseEntity<>(createdDiscount, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<List<Discount>> getAllDiscounts(){
        List<Discount> discounts = discountService.getAllDiscounts();
        if (discounts != null) {
            return new ResponseEntity<>(discounts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Discount> getDiscountById(@PathVariable Long id){
        Discount discount = discountService.getDiscountById(id);
        if (discount != null){
            return new ResponseEntity<>(discount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Discount> updateDiscount(@PathVariable Long id, @RequestBody Discount updatedDiscount){
        Discount discount = discountService.editDiscount(id, updatedDiscount);
        if (discount != null) {
            return new ResponseEntity<>(discount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable Long id){
        discountService.deleteDiscount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
