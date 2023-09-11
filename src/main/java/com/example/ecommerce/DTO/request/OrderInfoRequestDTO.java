package com.example.ecommerce.DTO.request;

import lombok.Data;

@Data
public class OrderInfoRequestDTO {
    private String firstname;
    private String lastname;
    private String mobile;
    private String email;
    private String address;
    private String district;
    private String city;
    private String province;
    private String content;
}
