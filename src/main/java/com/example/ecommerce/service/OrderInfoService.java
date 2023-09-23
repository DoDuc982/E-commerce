package com.example.ecommerce.service;

import com.example.ecommerce.DTO.mapper.Mapper;
import com.example.ecommerce.DTO.request.OrderInfoRequestDTO;
import com.example.ecommerce.DTO.response.OrderInfoResponseDTO;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.repository.OrderInfoRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return orderInfoRepository.findAll().stream()
                .map(Mapper::orderInfoToOrderInfoResponseDTO)
                .collect(Collectors.toList());
    }
    public List<OrderInfoResponseDTO> getAllOrderOfAnUser(Long userId) {
        return orderInfoRepository.findAllOrdersByUserId(userId).stream()
                .map(Mapper::orderInfoToOrderInfoResponseDTO)
                .collect(Collectors.toList());
    }
    public OrderInfoResponseDTO postInfo(OrderInfoRequestDTO orderInfoRequestDTO, Long orderId){
        Order order = new Order();
        order.setFirstname(orderInfoRequestDTO.getFirstname());
        order.setLastname(orderInfoRequestDTO.getLastname());
        order.setMobile(orderInfoRequestDTO.getMobile());
        order.setEmail(orderInfoRequestDTO.getEmail());
        order.setAddress(orderInfoRequestDTO.getAddress());
        order.setCity(orderInfoRequestDTO.getCity());
        order.setProvince(orderInfoRequestDTO.getProvince());
        order.setContent(orderInfoRequestDTO.getContent());
        List<OrderItem> orderItems = order.getOrderItems();
        Double sum = (double) 0;

        for (OrderItem orderItem : orderItems){
            sum += orderItem.getPrice() * orderItem.getQuantity();
        }
        orderInfoRepository.save(order);
        return Mapper.orderInfoToOrderInfoResponseDTO();
    }
}
