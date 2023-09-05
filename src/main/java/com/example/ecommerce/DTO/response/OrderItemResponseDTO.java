package com.example.ecommerce.DTO.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderItemResponseDTO {
    private Long id;
    private String name;
    private Double price;
    private Double discountPrice;
    private String imageUrl;
    private Long quantity;
    private Double totalPrice;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
