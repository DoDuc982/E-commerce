package com.example.ecommerce.controller.user;

import com.example.ecommerce.DTO.response.OrderInfoResponseDTO;
import com.example.ecommerce.service.OrderInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/order_info")
public class UOrderInfoController {
    private final OrderInfoService orderInfoService;

    public UOrderInfoController(OrderInfoService orderInfoService) {
        this.orderInfoService = orderInfoService;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderInfoResponseDTO>> getAllOrder(@PathVariable Long userId){
        return new ResponseEntity<>(orderInfoService.getAllOrderOfAnUser(userId), HttpStatus.OK);
    }
    @GetMapping("/info/{orderId}")
    public ResponseEntity<OrderInfoResponseDTO> getOrderInfo(@PathVariable Long orderId){
        return new ResponseEntity<>(orderInfoService.getOrderInfoById(orderId), HttpStatus.OK);
    }
//    @PutMapping("/{userId}")
}
