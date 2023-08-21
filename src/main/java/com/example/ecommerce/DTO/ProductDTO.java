package com.example.ecommerce.DTO;

import com.example.ecommerce.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private Double discountPrice;
    private String imageUrl;
    private Long quantity;
    private Long soldQuantity;
    private String content;
    private Category category;
}
