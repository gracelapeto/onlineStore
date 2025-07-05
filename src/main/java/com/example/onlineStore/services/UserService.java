package com.example.onlineStore.services;

import com.example.onlineStore.dtos.UserDto;
import com.example.onlineStore.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService  {

    List<User> findAll();

    Optional<User> findById(Long id);

    User registerUser(UserDto dto);
}
