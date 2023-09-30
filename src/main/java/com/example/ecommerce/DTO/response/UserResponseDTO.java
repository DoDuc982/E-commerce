package com.example.ecommerce.DTO.response;

import com.example.ecommerce.model.Role;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UserResponseDTO {
    private String name;
    private String phoneNumber;
    private String password;
    private boolean sex;
    private String email;
    private LocalDateTime createdOn;
    private Role role;
    private List<String> addresses;
}
