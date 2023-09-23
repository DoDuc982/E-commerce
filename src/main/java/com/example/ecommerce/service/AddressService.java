package com.example.ecommerce.service;

import com.example.ecommerce.DTO.mapper.Mapper;
import com.example.ecommerce.DTO.request.AddressRequestDTO;
import com.example.ecommerce.DTO.response.AddressResponseDTO;
import com.example.ecommerce.model.Address;
import com.example.ecommerce.repository.AddressRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {
    @Autowired
    private final AddressRepository addressRepository;
    @Autowired
    private final UserRepository userRepository;
    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
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

    public AddressResponseDTO createAddress(AddressRequestDTO addressRequestDTO, Long userId) {
        Address address = new Address();
        address.setCity(addressRequestDTO.getCity());
        address.setDistrict(addressRequestDTO.getDistrict());
        address.setProvince(addressRequestDTO.getProvince());
        address.setAddress(addressRequestDTO.getAddress());
        address.setUser(userRepository.findById(userId).orElse(null));
        addressRepository.save(address);
        return Mapper.addressToAddressResponseDTO(address);
    }

    public AddressResponseDTO updateAddress(AddressRequestDTO addressRequestDTO, Long userId, Long id) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address != null) {
            address.setCity(addressRequestDTO.getCity());
            address.setDistrict(addressRequestDTO.getAddress());
            address.setProvince(addressRequestDTO.getProvince());
            address.setAddress(addressRequestDTO.getAddress());
            address.getUser().setId(userId);
            addressRepository.save(address);
            return Mapper.addressToAddressResponseDTO(address);
        }
        return null;
    }
    public Boolean deleteAddress(Long userId, Long id){
        List<Address> addresses = addressRepository.findAllAddressByUserId(userId);
        for (Address a : addresses) {
            if (a.getId().equals(id)){
                addressRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }
}
