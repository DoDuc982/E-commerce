package com.example.ecommerce.DTO.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartItemResponseDTO {
    private Long userId;
    private String username;
    private String phoneNumber;

    private String productName;
    private String productUrl;
    private Double productPrice;
    private Integer quantity;
}