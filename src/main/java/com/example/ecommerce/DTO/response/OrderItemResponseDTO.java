package com.example.ecommerce.DTO.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderItemResponseDTO {
    private String name;
    private Long price;
    private Long discountPrice;
    private String imageUrl;
    private Integer quantity;
    private Long totalPrice;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
