package com.example.ecommerce.service;

import com.example.ecommerce.DTO.mapper.Mapper;
import com.example.ecommerce.DTO.response.AddressResponseDTO;
import com.example.ecommerce.model.Address;
import com.example.ecommerce.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    public List<AddressResponseDTO> getAllAddress(){
        return addressRepository.findAll().stream()
                .map(Mapper::addressToAddressResponseDTO)
                .collect(Collectors.toList());
    }
    public List<AddressResponseDTO> getAllAddressOfUser(Long userId){
        return addressRepository.findAllAddressByUserId(userId).stream()
                .map(Mapper::addressToAddressResponseDTO)
                .collect(Collectors.toList());
    }
}
