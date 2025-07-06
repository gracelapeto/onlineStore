package com.example.onlineStore.services.impl;

import com.example.onlineStore.dtos.UserDto;
import com.example.onlineStore.entities.Role;
import com.example.onlineStore.entities.User;
import com.example.onlineStore.repositories.RoleRepository;
import com.example.onlineStore.repositories.UserRepository;
import com.example.onlineStore.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }


    @Override
    public User registerUser(UserDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            return null;
        } else {
            Role userRole = roleRepository.findById("ROLE_USER").get();

            User newUser = new User();
            newUser.setUsername(dto.getUsername());
            newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
            newUser.setAddress(dto.getAddress());
            newUser.setAvatarUrl(dto.getAvatarUrl());
            newUser.setActive(true);
            newUser.setRole(userRole);

            return userRepository.save(newUser);
        }
    }

    @Override
    public User getLoggedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow();
    }

}