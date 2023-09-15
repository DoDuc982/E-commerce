package com.example.ecommerce.DTO.request;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String phoneNumber;
    private boolean sex;
}
