package com.example.ecommerce.service;

import com.example.ecommerce.DTO.mapper.Mapper;
import com.example.ecommerce.DTO.request.OrderInfoRequestDTO;
import com.example.ecommerce.DTO.response.OrderInfoResponseDTO;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.OrderInfoRepository;
import com.example.ecommerce.repository.OrderItemRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderInfoService {
    private final OrderInfoRepository orderInfoRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    @Autowired
    public OrderInfoService(OrderInfoRepository orderInfoRepository, OrderItemRepository orderItemRepository, CartItemRepository cartItemRepository, UserRepository userRepository) {
        this.orderItemRepository = orderItemRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderInfoRepository = orderInfoRepository;
        this.userRepository = userRepository;
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
    public void cartToOrderItem(Long userId, Long orderId){
        for (CartItem cartItem : cartItemRepository.findAllCartItemByUserId(userId)){
            OrderItem orderItem = new OrderItem();
            orderItem.setImageUrl(cartItem.getProduct().getImageUrl());
            orderItem.setName(cartItem.getProduct().getName());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getQuantity() * cartItem.getProduct().getPrice());
            orderItem.setOrder(orderInfoRepository.findById(orderId).orElse(null));
            orderItemRepository.save(orderItem);
        }
    }
    public OrderInfoResponseDTO postInfo(OrderInfoRequestDTO orderInfoRequestDTO, Long userId){
        Order order = new Order();
        order.setFirstname(orderInfoRequestDTO.getFirstname());
        order.setLastname(orderInfoRequestDTO.getLastname());
        order.setMobile(orderInfoRequestDTO.getMobile());
        order.setEmail(orderInfoRequestDTO.getEmail());
        order.setAddress(orderInfoRequestDTO.getAddress());
        order.setCity(orderInfoRequestDTO.getCity());
        order.setProvince(orderInfoRequestDTO.getProvince());
        order.setContent(orderInfoRequestDTO.getContent());
        order.setUser(userRepository.findById(userId).orElse(null));
        orderInfoRepository.save(order);
        double sum = 0;
        for (CartItem cartItem : cartItemRepository.findAllCartItemByUserId(userId)) {
            sum += cartItem.getQuantity() * cartItem.getProduct().getPrice();
        }

        cartToOrderItem(userId, order.getId());
        order.setGrandTotal(sum);
        orderInfoRepository.save(order);
        return Mapper.orderInfoToOrderInfoResponseDTO(order);
    }
}
