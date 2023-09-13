package com.example.ecommerce.DTO.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserResponseForUserDTO {
    private String name;
    private String phoneNumber;
    private String password;
    private boolean sex;
    private List<String> addresses;
}
