package com.example.onlineStore.dtos;

import lombok.Data;

@Data
public class RemoveProductDto {
    private Long userId;
    private Long productId;
}
