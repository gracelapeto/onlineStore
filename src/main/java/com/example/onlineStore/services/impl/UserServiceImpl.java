package com.example.onlineStore.services.impl;

import com.example.onlineStore.entities.User;
import com.example.onlineStore.repositories.UserRepository;
import com.example.onlineStore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
private UserRepository userRepository;

@Autowired
public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
}

@Override
public User save(User user){
    return userRepository.save(user);
}
@Override
public List<User> findAll(){
    return userRepository.findAll();
}
@Override
public Optional<User> findById(Long id){
    return userRepository.findById(id);
}
}
