package com.example.ecommerce.DTO.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDTO {
    private Long id;
    private String name;
    private Long price;
    private Long discountPrice;
    private String imageUrl;
    private Integer quantity;
    private Integer soldQuantity;
    private String content;
    private String category;
}
