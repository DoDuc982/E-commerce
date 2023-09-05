package com.example.ecommerce.service;

import com.example.ecommerce.DTO.mapper.Mapper;
import com.example.ecommerce.DTO.response.OrderInfoResponseDTO;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.repository.OrderInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderInfoService {
    private final OrderInfoRepository orderInfoRepository;
    @Autowired
    public OrderInfoService(OrderInfoRepository orderInfoRepository) {
        this.orderInfoRepository = orderInfoRepository;
    }
    public Order getOrderInfo(Long id){
        return orderInfoRepository.findById(id).orElse(null);
    }
    public OrderInfoResponseDTO getOrderInfoById(Long id){
        return Mapper.orderInfoToOrderInfoResponseDTO(this.getOrderInfo(id));
    }
    public List<OrderInfoResponseDTO> getAllOrderInfo(){
        List<Order> orders = orderInfoRepository.findAll();
        List<OrderInfoResponseDTO> orderInfoResponseDTOS = new ArrayList<>();
        for (Order order : orders) {
            orderInfoResponseDTOS.add(Mapper.orderInfoToOrderInfoResponseDTO(order));
        }
        return orderInfoResponseDTOS;
    }
}
