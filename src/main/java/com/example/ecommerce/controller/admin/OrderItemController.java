package com.example.ecommerce.controller.admin;

import com.example.ecommerce.DTO.response.OrderItemResponseDTO;
import com.example.ecommerce.service.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/api/order_items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<OrderItemResponseDTO>> getAllItemByOrderId(@PathVariable Long id){
        List<OrderItemResponseDTO> orderItemResponseDTOS = orderItemService.getItemOfAnOrder(id);
        return new ResponseEntity<>(orderItemResponseDTOS, HttpStatus.OK);
    }
}
