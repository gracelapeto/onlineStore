package com.example.onlineStore.dtos;

import com.example.onlineStore.entities.BucketItem;
import com.example.onlineStore.entities.User;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class BucketResponseDto {
    private Long id;
    private List<BucketItem> items = new ArrayList<>();
}
