package com.example.ecommerce.controller.user;

import com.example.ecommerce.DTO.request.OrderInfoRequestDTO;
import com.example.ecommerce.DTO.response.OrderInfoResponseDTO;
import com.example.ecommerce.security.JwtGenerator;
import com.example.ecommerce.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user/api/order_info")
public class UOrderInfoController {
    @Autowired
    private final OrderInfoService orderInfoService;
    @Autowired
    private final JwtGenerator jwtGenerator;
    public UOrderInfoController(OrderInfoService orderInfoService, JwtGenerator jwtGenerator) {
        this.orderInfoService = orderInfoService;
        this.jwtGenerator = jwtGenerator;
    }
    @GetMapping
    public ResponseEntity<List<OrderInfoResponseDTO>> getAllOrdersOfAnUser(HttpServletRequest request){
        String token = jwtGenerator.getJwtFromRequest(request);
        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
            Long userId = jwtGenerator.getUserIdFromJwt(token);
            return new ResponseEntity<>(orderInfoService.getAllOrderOfAnUser(userId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderInfoResponseDTO> getOrderInfo(@PathVariable Long orderId, HttpServletRequest request){
        String token = jwtGenerator.getJwtFromRequest(request);
        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
            Long userId = jwtGenerator.getUserIdFromJwt(token);
            return new ResponseEntity<>(orderInfoService.getOrderInfoById(orderId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping
    public ResponseEntity<OrderInfoResponseDTO> sendInfo(@RequestBody OrderInfoRequestDTO orderInfoRequestDTO, HttpServletRequest request){
        String token = jwtGenerator.getJwtFromRequest(request);
        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
            Long userId = jwtGenerator.getUserIdFromJwt(token);
            return new ResponseEntity<>(orderInfoService., HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
