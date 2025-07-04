package com.example.onlineStore.dtos;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String address;
    private String avatarUrl;
}
