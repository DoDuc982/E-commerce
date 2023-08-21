package com.example.ecommerce.mapper;

import com.example.ecommerce.DTO.CategoryDTO;
import com.example.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;

public class MapToEntity {
    public static Product mapToProduct(ProductDTO productDTO){
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .discountPrice(productDTO.getDiscountPrice())
                .imageUrl(productDTO.getImageUrl())
                .quantity(productDTO.getQuantity())
                .soldQuantity(productDTO.getSoldQuantity())
                .content(productDTO.getContent())
                .category(productDTO.getCategory())
                .build();
    }
    public static Category mapToCategory(CategoryDTO categoryDTO){
        return Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .content(categoryDTO.getContent())
                .products(categoryDTO.getProducts())
                .build();
    }
}
