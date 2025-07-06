package com.example.onlineStore.services.impl;

import com.example.onlineStore.dtos.UpdateUserProfileDto;
import com.example.onlineStore.dtos.UserDto;
import com.example.onlineStore.entities.Role;
import com.example.onlineStore.entities.User;
import com.example.onlineStore.exception.OnlineStoreException;
import com.example.onlineStore.repositories.RoleRepository;
import com.example.onlineStore.repositories.UserRepository;
import com.example.onlineStore.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw OnlineStoreException.notFound(User.class, id.toString());
        }
    }


    @Override
    public User registerUser(UserDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            return null;
        } else {
            Role userRole = roleRepository.findById("ROLE_USER")
                    .orElseThrow(() ->OnlineStoreException.notFound(Role.class, "ROLE_USER"));

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
    public User createAdmin(UserDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw OnlineStoreException.userAlreadyExists(dto.getUsername());
        }

        Role adminRole = roleRepository.findById("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("ROLE_ADMIN not found"));

        User admin = new User();
        admin.setUsername(dto.getUsername());
        admin.setPassword(passwordEncoder.encode(dto.getPassword()));
        admin.setAddress(dto.getAddress());
        admin.setAvatarUrl(dto.getAvatarUrl());
        admin.setActive(true);
        admin.setRole(adminRole);

        return userRepository.save(admin);
    }
    @Override
    public List<User> getAllActiveUsers() {
        return userRepository.findByActiveTrue();
    }
    @Override
    public User updateUserProfile(UpdateUserProfileDto dto) {
        User user = this.findById(dto.getUserId());
        user.setAddress(dto.getAddress());
        user.setAvatarUrl(dto.getAvatarUrl());

        return userRepository.save(user);
    }
    @Override
    public void inactivateUser(Long userId) {
        User user = this.findById(userId);
        user.setActive(false);
        userRepository.save(user);
    }


    @Override
    public User getLoggedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow();
    }

}