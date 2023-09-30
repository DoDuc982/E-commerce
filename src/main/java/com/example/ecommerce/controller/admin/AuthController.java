package com.example.ecommerce.controller.admin;

import com.example.ecommerce.DTO.request.LoginRequestDTO;
import com.example.ecommerce.DTO.request.RegisterRequestDTO;
import com.example.ecommerce.DTO.response.AuthResponseDTO;
import com.example.ecommerce.security.JwtGenerator;
import com.example.ecommerce.service.EmailService;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/api")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtGenerator jwtGenerator;
    private final EmailService emailService;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtGenerator jwtGenerator, EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
        this.emailService = emailService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        if (userService.register(registerRequestDTO)) {
            emailService.sendSimpleMail("beanbhlc2002@gmail.com");
            return new ResponseEntity<>("User registered success!", HttpStatus.OK);}
        else {
            return new ResponseEntity<>("User registered failed!", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }
}
