package com.example.onlineStore.services;

import com.example.onlineStore.dtos.UpdateUserProfileDto;
import com.example.onlineStore.dtos.UserDto;
import com.example.onlineStore.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService  {

    List<User> findAll();

    User findById(Long id);

    User registerUser(UserDto dto);

    User createAdmin(UserDto dto);

    List<User> getAllActiveUsers();

    User updateUserProfile(UpdateUserProfileDto dto);

    void inactivateUser(Long userId);

    User getLoggedUser();
}
