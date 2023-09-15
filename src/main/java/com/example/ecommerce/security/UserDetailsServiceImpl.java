package com.example.ecommerce.security;

import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUser(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getRole());
    }
    private Collection<GrantedAuthority> mapRoleToAuth(Role roles){
        return roles.stream().toList();
    }
}
