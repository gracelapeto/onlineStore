package com.example.onlineStore.dtos;

import lombok.Data;

@Data
public class ProductCreateDto {
    private String title;
    private String description;
    private Double price;
    private Integer quantity;
    private Long authorId;
    private Long categoryId;
}
