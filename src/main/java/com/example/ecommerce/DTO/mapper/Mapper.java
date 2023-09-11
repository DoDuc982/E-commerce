package com.example.ecommerce.DTO.mapper;

import com.example.ecommerce.DTO.response.*;
import com.example.ecommerce.model.*;

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
    public static AddressResponseDTO addressToAddressResponseDTO(Address address){
        return AddressResponseDTO.builder()
                .address(address.getAddress())
                .province(address.getProvince())
                .district(address.getDistrict())
                .city(address.getCity())
                .build();
    }
    public static CartItemResponseDTO cartItemToCartItemResponseDTO (CartItem cartItem){

        return CartItemResponseDTO.builder()
                .userId(cartItem.getUser().getId())
                .username(cartItem.getUser().getName())
                .productName(cartItem.getProduct().getName())
                .productUrl(cartItem.getProduct().getImageUrl())
                .productPrice(cartItem.getProduct().getPrice())
                .quantity(cartItem.getQuantity())
                .build();
    }
    public static OrderItemResponseDTO orderItemToOrderItemResponseDTO(OrderItem orderItem){
        return OrderItemResponseDTO.builder()
                .name(orderItem.getName())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .imageUrl(orderItem.getImageUrl())
                .discountPrice(orderItem.getDiscountPrice()) // can luu y khi apply ma giam gia,...
                .totalPrice((orderItem.getPrice() - orderItem.getDiscountPrice()) * orderItem.getQuantity())
                .createdOn(orderItem.getCreatedOn())
                .updatedOn(orderItem.getUpdatedOn())
                .build();
    }
    public static OrderInfoResponseDTO orderInfoToOrderInfoResponseDTO(Order order){
        double sum = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            sum += ((orderItem.getPrice() - orderItem.getDiscountPrice())*orderItem.getQuantity());
        }
        return OrderInfoResponseDTO.builder()
                .subTotal(sum)
                .shippingPrice(order.getShippingPrice())
                .total(sum + order.getShippingPrice())
                .discount(order.getDiscount())
                .grandTotal(sum) //phan nay cam xem lai khi app ma giam gia
                .firstname(order.getFirstname())
                .lastname(order.getLastname())
                .mobile(order.getMobile())
                .email(order.getEmail())
                .address(order.getAddress())
                .district(order.getDistrict())
                .city(order.getCity())
                .province(order.getProvince())
                .content(order.getContent())
                .build();
    }
}
