package com.example.ecommerce.service;

import com.example.ecommerce.DTO.mapper.Mapper;
import com.example.ecommerce.DTO.response.OrderItemResponseDTO;
import com.example.ecommerce.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
    public List<OrderItemResponseDTO> getItemFromOrder(Long orderId){
        return orderItemRepository.findByOrderId(orderId).stream()
                .map(Mapper::orderItemToOrderItemResponseDTO)
                .toList();
    }
}
