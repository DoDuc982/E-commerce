package com.example.ecommerce.controller.admin;

import com.example.ecommerce.DTO.request.LoginRequestDTO;
import com.example.ecommerce.DTO.request.RegisterRequestDTO;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/api")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        if (userService.register(registerRequestDTO)) {
            return new ResponseEntity<>("User registered success!", HttpStatus.OK);}
        else {
            return new ResponseEntity<>("User registered failed!", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("Sign in successfully!", HttpStatus.OK);
    }
}
