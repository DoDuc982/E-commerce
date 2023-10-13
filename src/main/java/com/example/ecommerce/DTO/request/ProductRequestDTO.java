package com.example.ecommerce.DTO.request;

import com.example.ecommerce.model.Category;
import lombok.Data;

@Data
public class ProductRequestDTO {
    private String name;
    private Long price;
    private Long discountPrice;
    private String imageUrl;
    private Integer quantity;
    private Integer soldQuantity;
    private String content;
    private Long categoryId;
}
