package com.example.onlineStore.dtos;

import lombok.Data;

@Data
public class AddProductBucketDto {
    private Long userId;
    private Long productId;
    private Integer quantity;
}
