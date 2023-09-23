package com.example.ecommerce.DTO.request;

import lombok.Data;


@Data
public class AddressRequestDTO {
    private String address;
    private String district;
    private String city;
    private String province;
}
