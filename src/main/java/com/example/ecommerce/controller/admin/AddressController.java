package com.example.ecommerce.controller.admin;

import com.example.ecommerce.DTO.response.AddressResponseDTO;
import com.example.ecommerce.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/api/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> getAllAddresses(){
        return new ResponseEntity<>(addressService.getAllAddress(),HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<AddressResponseDTO>> getAddressesOfUser(@PathVariable Long userId){
        return new ResponseEntity<>(addressService.getAllAddressOfUser(userId),HttpStatus.OK);
    }
}
