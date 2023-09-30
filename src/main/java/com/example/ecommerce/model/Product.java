package com.example.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name of product is required")
    @NotEmpty(message = "Name of product is required")
    private String name;

    private Double price;

    private Double discountPrice;

    @NotNull(message = "Image url of product is required")
    @NotEmpty(message = "Image url of product is required")
    private String imageUrl;

    private Long quantity;

    private Long soldQuantity;

    @NotNull(message = "Content of product is required")
    @NotEmpty(message = "Content of product is required")
    private String content;

    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
