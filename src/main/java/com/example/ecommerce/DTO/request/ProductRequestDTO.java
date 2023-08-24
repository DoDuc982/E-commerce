package com.example.ecommerce.DTO.request;

import com.example.ecommerce.model.Category;
import lombok.Data;

@Data
public class ProductRequestDTO {
    private String name;
    private Double price;
    private Double discountPrice;
    private String imageUrl;
    private Long quantity;
    private Long soldQuantity;
    private String content;
    private Long categoryId;
}
