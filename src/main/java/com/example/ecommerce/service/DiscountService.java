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
    public List<Discount> getAllDiscounts(){
        return discountRepository.findAll();
    }
    public Discount getDiscountById(Long id){
        return discountRepository.findById(id).orElse(null);
    }
    public Discount createDiscount(Discount discount){
        return discountRepository.save(discount);
    }
    public Discount updateDiscount(Discount updatedDiscount){
        Discount discount = discountRepository.findById(updatedDiscount.getId()).orElse(null);
        if (discount != null){
            discount.setCode(updatedDiscount.getCode());
            discount.setContent(updatedDiscount.getContent());
            discount.setPrice(updatedDiscount.getPrice());
            discount.setQuantity(updatedDiscount.getQuantity());
            return discountRepository.save(discount);
        }
        return null;
    }
    public void deletedDiscount(Long id){
        discountRepository.deleteById(id);
    }
}
