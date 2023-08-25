package com.example.ecommerce.DTO.mapper;

import com.example.ecommerce.DTO.response.CategoryResponseDTO;
import com.example.ecommerce.DTO.response.ProductResponseDTO;
import com.example.ecommerce.DTO.response.UserResponseDTO;
import com.example.ecommerce.model.Address;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static ProductResponseDTO productToProductResponseDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .content(product.getContent())
                .quantity(product.getQuantity())
                .imageUrl(product.getImageUrl())
                .soldQuantity(product.getSoldQuantity())
                .category(product.getCategory().getName())
                .build();
    }
    public static CategoryResponseDTO categoryTocategoryResponseDTO(Category category){
        List<String> names = new ArrayList<>();
        for (Product product : category.getProducts()) {
            names.add(product.getName());
        }
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .content(category.getContent())
                .productsName(names)
                .build();
    }
    public static UserResponseDTO userToUserResponseDTO(User user){
        List<String> addresses = new ArrayList<>();
        for (Address address : user.getAddresses()){
            addresses.add(address.getAddress() + ", " + address.getDistrict() + ", " + address.getCity() + ", " + address.getProvince());
        }
        return UserResponseDTO.builder()
                .sex(user.isSex())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .password(user.getPassword())
                .createdOn(user.getCreatedOn())
                .name(user.getName())
                .addresses(addresses)
                .build();
    }
}
