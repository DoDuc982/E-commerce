package com.example.ecommerce.DTO.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponseDTO {
    private String address;
    private String district;
    private String city;
    private String province;
}
