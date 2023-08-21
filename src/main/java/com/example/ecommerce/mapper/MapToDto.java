package com.example.ecommerce.mapper;

import com.example.ecommerce.DTO.CategoryDTO;
import com.example.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;

import java.util.Stack;

public class MapToDto {
    public static ProductDTO mapToProductDTO(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .imageUrl(product.getImageUrl())
                .soldQuantity(product.getSoldQuantity())
                .content(product.getContent())
                .category(product.getCategory())
                .build();
    }
    public static CategoryDTO mapToCategoryDTO(Category category){
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .content(category.getContent())
                .products(category.getProducts())
                .build();
    }
}
