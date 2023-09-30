package com.example.ecommerce.controller.user;

import com.example.ecommerce.DTO.mapper.Mapper;
import com.example.ecommerce.DTO.request.OrderInfoRequestDTO;
import com.example.ecommerce.DTO.response.OrderInfoResponseDTO;
import com.example.ecommerce.DTO.response.OrderItemResponseDTO;
import com.example.ecommerce.DTO.response.UserResponseDTO;
import com.example.ecommerce.security.JwtGenerator;
import com.example.ecommerce.service.EmailService;
import com.example.ecommerce.service.OrderInfoService;
import com.example.ecommerce.service.OrderItemService;
import com.example.ecommerce.service.UserService;
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
    private final OrderInfoService orderInfoService;
    private final JwtGenerator jwtGenerator;
    private final UserService userService;
    private final OrderItemService orderItemService;
    private final EmailService emailService;
    @Autowired
    public UOrderInfoController(OrderInfoService orderInfoService, JwtGenerator jwtGenerator, UserService userService, OrderItemService orderItemService, EmailService emailService) {
        this.orderInfoService = orderInfoService;
        this.jwtGenerator = jwtGenerator;
        this.userService = userService;
        this.orderItemService = orderItemService;
        this.emailService = emailService;
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
            UserResponseDTO user = Mapper.userToUserResponseDTO(userService.getByUserId(userId));
            OrderInfoResponseDTO orderInfo = orderInfoService.postInfo(orderInfoRequestDTO, userId);
            List<OrderItemResponseDTO> orderItems = orderItemService.getItemFromOrder(orderInfo.getId());
            emailService.sendHtmlEmail(user.getEmail(), user, orderItems, orderInfo);
            return new ResponseEntity<>(orderInfo, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
