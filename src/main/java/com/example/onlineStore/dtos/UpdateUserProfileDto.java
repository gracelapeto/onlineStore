package com.example.onlineStore.dtos;

import lombok.Data;

@Data
public class UpdateUserProfileDto {
    private Long userId;
    private String address;
    private String avatarUrl;
}

