package com.example.ecommerce.controller.user;

import com.example.ecommerce.DTO.request.UserRequestDTO;
import com.example.ecommerce.DTO.response.CartItemResponseDTO;
import com.example.ecommerce.DTO.response.UserResponseDTO;
import com.example.ecommerce.security.JwtGenerator;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("user/api/user_info")
public class UUserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtGenerator jwtGenerator;
    public UUserController(UserService userService, JwtGenerator jwtGenerator) {
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }
    @GetMapping
    public ResponseEntity<UserResponseDTO> getUserInfo(HttpServletRequest request){
        String token = jwtGenerator.getJwtFromRequest(request);
        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
            Long userId = jwtGenerator.getUserIdFromJwt(token); // Lấy ID người dùng từ JWT
            return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping
    public ResponseEntity<UserResponseDTO> updateInfo(HttpServletRequest request, @RequestBody UserRequestDTO userRequestDTO){
        String token = jwtGenerator.getJwtFromRequest(request);
        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
            Long userId = jwtGenerator.getUserIdFromJwt(token); // Lấy ID người dùng từ JWT
            return new ResponseEntity<>(userService.updateUserForUser(userId, userRequestDTO), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
