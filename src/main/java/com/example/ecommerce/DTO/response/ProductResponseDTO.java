package com.example.ecommerce.DTO.response;

import com.example.ecommerce.model.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDTO {
    private Long id;
    private String name;
    private Double price;
    private Double discountPrice;
    private String imageUrl;
    private Long quantity;
    private Long soldQuantity;
    private String content;
    private String category;
}
