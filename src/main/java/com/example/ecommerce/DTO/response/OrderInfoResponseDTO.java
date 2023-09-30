package com.example.ecommerce.DTO.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderInfoResponseDTO {
    private Long id;
    private Double subTotal;
    private Double shippingPrice;
    private Double total;
    private String discount;
    private Double grandTotal;
    private String firstname;
    private String lastname;
    private String mobile;
    private String email;
    private String address;
    private String district;
    private String city;
    private String province;
    private String content;

    private String username;
}
