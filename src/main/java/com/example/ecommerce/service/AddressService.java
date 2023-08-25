package com.example.ecommerce.service;

import com.example.ecommerce.repository.AddressRepository;

public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

}
