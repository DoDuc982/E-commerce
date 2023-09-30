package com.example.ecommerce.DTO.request;

import com.example.ecommerce.model.Role;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;
    private String phoneNumber;
    private String password;
    private String email;
    private boolean sex;
    private Role role;
}
