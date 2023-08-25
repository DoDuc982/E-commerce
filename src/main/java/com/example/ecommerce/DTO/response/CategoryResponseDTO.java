package com.example.ecommerce.DTO.response;

import com.example.ecommerce.model.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private String content;
    private List<String> productsName;
}
