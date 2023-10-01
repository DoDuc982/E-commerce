package com.example.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Address is required")
    private String address;

    @NotNull(message = "District is required")
    private String district;

    @NotNull(message = "City is required")
    private String city;

    @NotNull(message = "Province is required")
    private String province;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
