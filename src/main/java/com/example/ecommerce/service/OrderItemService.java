package com.example.ecommerce.service;

import com.example.ecommerce.DTO.mapper.Mapper;
import com.example.ecommerce.DTO.response.OrderItemResponseDTO;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> getAllOrderItem(){
        return orderItemRepository.findAll();
    }
    public List<OrderItemResponseDTO> getItemOfAnOrder(Long id){
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(id);
        return orderItems.stream()
                .map(Mapper::orderItemToOrderItemResponseDTO)
                .collect(Collectors.toList());
    }
}
