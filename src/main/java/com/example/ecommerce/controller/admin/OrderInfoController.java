package com.example.ecommerce.controller.admin;

import com.example.ecommerce.DTO.response.OrderInfoResponseDTO;
import com.example.ecommerce.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/api/order_info")
public class OrderInfoController {
    private final OrderInfoService orderInfoService;
    @Autowired
    public OrderInfoController(OrderInfoService orderInfoService) {
        this.orderInfoService = orderInfoService;
    }
    @GetMapping()
    public ResponseEntity<List<OrderInfoResponseDTO>> getAllOrderInfo(){
        return new ResponseEntity<>(orderInfoService.getAllOrderInfo(),HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderInfoResponseDTO>> getOrderInfoOfAnUser(@PathVariable Long userId){
        return new ResponseEntity<>(orderInfoService.getAllOrderOfAnUser(userId), HttpStatus.OK);
    }
    @GetMapping("/info/{orderId}")
    public ResponseEntity<OrderInfoResponseDTO> getOrderInfo(@PathVariable Long orderId){
        return new ResponseEntity<>(orderInfoService.getOrderInfoById(orderId), HttpStatus.OK);
    }

}
