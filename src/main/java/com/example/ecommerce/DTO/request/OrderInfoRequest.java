package com.example.ecommerce.DTO.request;

import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.model.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
public class OrderInfoRequest {
    private String discount;
    private String firstname;
    private String lastname;
    private String mobile;
    private String email;
    private String address;
    private String district;
    private String city;
    private String province;
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();
}