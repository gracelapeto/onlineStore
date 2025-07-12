package com.example.onlineStore.dtos.mapper;

import com.example.onlineStore.dtos.BucketItemDto;
import com.example.onlineStore.entities.Bucket;
import com.example.onlineStore.entities.BucketItem;
import com.example.onlineStore.entities.Product;

public class BucketItemMapper {

    public static BucketItemDto entityToDto(BucketItem item) {
        BucketItemDto dto = new BucketItemDto();
        dto.setId(item.getId());
        dto.setProductId(item.getProduct().getId());
        dto.setQuantity(item.getQuantity());
        return dto;
    }

    public static BucketItem dtoToEntity(BucketItemDto dto, Product product, Bucket bucket) {
        BucketItem item = new BucketItem();
        item.setId(dto.getId());
        item.setProduct(product);
        item.setQuantity(dto.getQuantity());
        item.setBucket(bucket);
        return item;
    }
}
