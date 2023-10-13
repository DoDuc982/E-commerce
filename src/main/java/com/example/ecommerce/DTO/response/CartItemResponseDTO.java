package com.example.ecommerce.DTO.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartItemResponseDTO {
    private Long userId;
    private String username;
    private String productName;
    private String productUrl;
    private Long productPrice;
    private Integer quantity;
}
