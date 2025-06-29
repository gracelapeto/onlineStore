package com.example.onlineStore.services.impl;

import com.example.onlineStore.entities.User;
import com.example.onlineStore.repositories.RoleRepository;
import com.example.onlineStore.repositories.UserRepository;
import com.example.onlineStore.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User save(User user) {
        User savedUser = userRepository.save(user);
         return savedUser;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
