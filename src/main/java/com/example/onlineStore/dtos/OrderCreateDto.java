package com.example.onlineStore.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OrderCreateDto {
    private List<Long> orderLinesIds;
    private String name;
    private String address;
}
