package com.example.onlineStore.services.impl;

import com.example.onlineStore.dtos.UpdateUserProfileDto;
import com.example.onlineStore.dtos.UserDto;
import com.example.onlineStore.entities.User;
import com.example.onlineStore.enums.Role;
import com.example.onlineStore.exception.OnlineStoreException;
import com.example.onlineStore.repositories.UserRepository;
import com.example.onlineStore.services.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{



private final UserRepository userRepository;
private final BCryptPasswordEncoder passwordEncoder;

@Override
public List<User> findAll() {
    return userRepository.findAll();
}

@Override
public User findById(Long id) {
    return userRepository.findById(id)
            .orElseThrow(() -> OnlineStoreException.notFound(User.class, id.toString()));
}

@Override
public User registerUser(UserDto dto) {
    if (userRepository.existsByUsername(dto.getUsername())) {
        throw OnlineStoreException.userAlreadyExists(dto.getUsername());
    }

    User newUser = new User();
    newUser.setUsername(dto.getUsername());
    newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
    newUser.setAddress(dto.getAddress());
    newUser.setAvatarUrl(dto.getAvatarUrl());
    newUser.setActive(true);
    newUser.setRole(Role.ROLE_USER);

    return userRepository.save(newUser);
}

@Override
public User createAdmin(UserDto dto) {
    if (userRepository.existsByUsername(dto.getUsername())) {
        throw OnlineStoreException.userAlreadyExists(dto.getUsername());
    }

    User admin = new User();
    admin.setUsername(dto.getUsername());
    admin.setPassword(passwordEncoder.encode(dto.getPassword()));
    admin.setAddress(dto.getAddress());
    admin.setAvatarUrl(dto.getAvatarUrl());
    admin.setActive(true);
    admin.setRole(Role.ROLE_ADMIN);

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

@PostConstruct
public void createDefaultAdmin() {
    if (!userRepository.existsByUsername("admin")) {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setEmail("admin@example.com");
        admin.setActive(true);
        admin.setRole(Role.ROLE_ADMIN);
        userRepository.save(admin);
    }
}

@Override
public User getLoggedUser() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    return userRepository.findByUsername(username).orElseThrow();
}
}
