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
    public Discount editDiscount(Long id, Discount discount){
        Discount edittedDiscount = discountRepository.findById(id).orElse(null);
        assert edittedDiscount != null;
        edittedDiscount.setQuantity(discount.getQuantity());
        edittedDiscount.setDiscountType(discount.getDiscountType());
        edittedDiscount.setCode(discount.getCode());
        edittedDiscount.setContent(discount.getContent());
        edittedDiscount.setPrice(discount.getPrice());
        edittedDiscount.setDiscountType(discount.getDiscountType());
        edittedDiscount.setDiscountFor(discount.getDiscountFor());
        edittedDiscount.setPercentage(discount.getPercentage());
        return edittedDiscount;
    }
    public void deleteDiscount(Long id){
        discountRepository.deleteById(id);
    }
}
