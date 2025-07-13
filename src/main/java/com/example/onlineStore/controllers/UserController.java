package com.example.onlineStore.controllers;

import com.example.onlineStore.dtos.UpdateUserProfileDto;
import com.example.onlineStore.dtos.UserDto;
import com.example.onlineStore.entities.User;
import com.example.onlineStore.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/active")
    public ResponseEntity<List<User>> getAllActiveUsers() {
        List<User> activeUsers = userService.getAllActiveUsers();
        return ResponseEntity.ok(activeUsers);
    }
    @PutMapping("/update-profile")
    public ResponseEntity<User> updateUserProfile(@RequestBody UpdateUserProfileDto dto) {
        User updatedUser = userService.updateUserProfile(dto);
        return ResponseEntity.ok(updatedUser);
    }
    @PutMapping("/inactivate/{id}")
    public ResponseEntity<String> inactivateUser(@PathVariable Long id) {
        userService.inactivateUser(id);
        return ResponseEntity.ok("User with id " + id + " has been deactivated");
    }

@PostMapping("/register")
public ResponseEntity<User> register(@RequestBody UserDto dto) {
    User registered = userService.registerUser(dto);
    return ResponseEntity.ok(registered);
}
}

