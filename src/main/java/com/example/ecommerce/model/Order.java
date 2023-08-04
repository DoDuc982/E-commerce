package com.example.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "OrderInfo")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double total_price;
    private Double discount_price;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;
    @ManyToMany
    @JoinTable(
            name = "Product_Order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();
}
