package com.example.onlineStore.dtos.mapper;

import com.example.onlineStore.dtos.BucketRequestDto;
import com.example.onlineStore.dtos.BucketResponseDto;
import com.example.onlineStore.entities.Bucket;
import org.springframework.stereotype.Component;

@Component
public class BucketMapper {

    public static BucketResponseDto entityToDto(Bucket bucket) {
        BucketResponseDto dto = new BucketResponseDto();
        dto.setItems(bucket.getItems());
        dto.setUser(bucket.getUser());
        return dto;
    }

    public static Bucket bucketDtoToEntity(BucketRequestDto dto) {
        Bucket bucket = new Bucket();
        bucket.setId(dto.getId());
        bucket.setItems(dto.getItems());
        bucket.setUser(dto.getUser());
        return bucket;
    }
}