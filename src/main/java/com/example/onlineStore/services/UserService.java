package com.example.onlineStore.services;

import com.example.onlineStore.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService  {
    User save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);
}
