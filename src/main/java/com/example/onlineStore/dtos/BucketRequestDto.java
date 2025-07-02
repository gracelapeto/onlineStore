package com.example.onlineStore.dtos;

import com.example.onlineStore.entities.BucketItem;
import com.example.onlineStore.entities.User;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class BucketRequestDto {
    private Long id;
    private User user;
    private List<BucketItem> items = new ArrayList<>();
}
