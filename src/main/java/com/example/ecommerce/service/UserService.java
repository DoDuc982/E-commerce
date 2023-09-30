package com.example.ecommerce.service;

import com.example.ecommerce.DTO.mapper.Mapper;
import com.example.ecommerce.DTO.request.RegisterRequestDTO;
import com.example.ecommerce.DTO.request.UserRequestDTO;
import com.example.ecommerce.DTO.response.UserResponseDTO;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long getUserIdByUsername(String username){
        return userRepository.findIdByUsername(username);
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        for (User user : users){
            userResponseDTOS.add(Mapper.userToUserResponseDTO(user));
        }
        return userResponseDTOS;
    }

    public User getByUserId(Long id) {
        return userRepository.findById(id)
                .orElse(null);
    }

    public UserResponseDTO getUserById(Long id){
        return Mapper.userToUserResponseDTO(this.getByUserId(id));
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setPassword(userRequestDTO.getPassword());
        user.setEmail(userRequestDTO.getEmail());
        user.setRole(userRequestDTO.getRole());
        user.setSex(userRequestDTO.isSex());
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        userRepository.save(user);
        return Mapper.userToUserResponseDTO(user);
    }
    public UserResponseDTO updateUser(Long id, UserRequestDTO updatedUser) {
        User user = getByUserId(id);
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setSex(updatedUser.isSex());
        user.setRole(updatedUser.getRole());
        userRepository.save(user);
        return Mapper.userToUserResponseDTO(user);
    }
    public Boolean register(RegisterRequestDTO registerRequestDTO){
        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isEmpty()){
            User user = new User();
            user.setUsername(registerRequestDTO.getUsername());
            user.setPhoneNumber(registerRequestDTO.getPhoneNumber());
            user.setEmail(registerRequestDTO.getEmail());
            user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
            user.setSex(registerRequestDTO.isSex());
            user.setRole(Role.valueOf("USER"));
            userRepository.save(user);
            return true;
        } else return false;
    }
    public UserResponseDTO updateUserForUser(Long id, UserRequestDTO updatedUser) {
        User user = getByUserId(id);
        user.setName(updatedUser.getName());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setEmail(updatedUser.getEmail());
        user.setSex(updatedUser.isSex());
        //Còn phần order và invoice
        userRepository.save(user);
        return Mapper.userToUserResponseDTO(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(null);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),mapRolesToAuthorities(user.getRole()));
    }
    private Collection<GrantedAuthority> mapRolesToAuthorities(Role role){
        String roleName = role.name();
        String authority = "ROLE_" + roleName; // Thêm "ROLE_" theo quy ước Spring Security
        return List.of(new SimpleGrantedAuthority(authority));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}