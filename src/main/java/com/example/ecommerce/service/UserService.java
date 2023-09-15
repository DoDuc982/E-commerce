package com.example.ecommerce.service;

import com.example.ecommerce.DTO.mapper.Mapper;
import com.example.ecommerce.DTO.request.RegisterRequestDTO;
import com.example.ecommerce.DTO.request.UserRequestDTO;
import com.example.ecommerce.DTO.response.UserResponseDTO;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        for (User user : users){
            userResponseDTOS.add(Mapper.userToUserResponseDTO(user));
        }
        return userResponseDTOS;
    }

    public User getUserId(Long id) {
        return userRepository.findById(id)
                .orElse(null);
    }
    public UserResponseDTO getUserById(Long id){
        return Mapper.userToUserResponseDTO(this.getUserId(id));
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(userRequestDTO.getRole());
        user.setSex(userRequestDTO.isSex());
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        userRepository.save(user);
        return Mapper.userToUserResponseDTO(user);
    }
    public UserResponseDTO updateUser(Long id, UserRequestDTO updatedUser) {
        User user = getUserId(id);
        user.setName(updatedUser.getName());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setSex(updatedUser.isSex());
        user.setRole(updatedUser.getRole());
        //Còn phần order và invoice
        userRepository.save(user);
        return Mapper.userToUserResponseDTO(user);
    }
    public User findByUser(String username){
        return userRepository.findByUsername(username);
    }
    public Boolean register(RegisterRequestDTO registerRequestDTO){
        if(userRepository.findByUsername(registerRequestDTO.getUsername()) == null){
            User user = new User();
            user.setUsername(registerRequestDTO.getUsername());
            user.setPhoneNumber(registerRequestDTO.getPhoneNumber());
            user.setPassword(registerRequestDTO.getPassword());
            user.setSex(registerRequestDTO.isSex());
            user.setRole(Role.valueOf("USER"));
            userRepository.save(user);
            return true;
        } else return false;
    }
}