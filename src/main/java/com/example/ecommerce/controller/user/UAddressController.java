package com.example.ecommerce.controller.user;

import com.example.ecommerce.DTO.request.AddressRequestDTO;
import com.example.ecommerce.DTO.response.AddressResponseDTO;
import com.example.ecommerce.security.JwtGenerator;
import com.example.ecommerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user/api/address")
public class UAddressController {
    @Autowired
    private final AddressService addressService;
    @Autowired
    private final JwtGenerator jwtGenerator;

    public UAddressController(AddressService addressService, JwtGenerator jwtGenerator) {
        this.addressService = addressService;
        this.jwtGenerator = jwtGenerator;
    }
    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> getAllAddresses(HttpServletRequest request){
        String token = jwtGenerator.getJwtFromRequest(request);
        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
            Long userId = jwtGenerator.getUserIdFromJwt(token);
            return new ResponseEntity<>(addressService.getAllAddressOfUser(userId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<AddressResponseDTO> createAddress(
            HttpServletRequest request,
            @RequestBody AddressRequestDTO addressRequestDTO
    ){
        String token = jwtGenerator.getJwtFromRequest(request);
        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
            Long userId = jwtGenerator.getUserIdFromJwt(token);
            return new ResponseEntity<>(addressService.createAddress(addressRequestDTO, userId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress(
            HttpServletRequest request,
            @PathVariable Long id,
            @RequestBody AddressRequestDTO addressRequestDTO
    ){
        String token = jwtGenerator.getJwtFromRequest(request);
        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
            Long userId = jwtGenerator.getUserIdFromJwt(token);
            AddressResponseDTO addressResponseDTO = addressService.updateAddress(addressRequestDTO, userId, id);
            if(addressResponseDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(addressService.updateAddress(addressRequestDTO, userId, id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(
            HttpServletRequest request,
            @PathVariable Long id
    ){
        String token = jwtGenerator.getJwtFromRequest(request);
        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
            Long userId = jwtGenerator.getUserIdFromJwt(token);
            if(addressService.deleteAddress(userId, id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
