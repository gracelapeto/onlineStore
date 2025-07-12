package com.example.onlineStore.dtos.mapper;

import com.example.onlineStore.dtos.BucketItemDto;
import com.example.onlineStore.dtos.BucketRequestDto;
import com.example.onlineStore.dtos.BucketResponseDto;
import com.example.onlineStore.entities.Bucket;
import com.example.onlineStore.entities.BucketItem;
import com.example.onlineStore.entities.Product;
import com.example.onlineStore.entities.User;

import java.util.ArrayList;
import java.util.List;

public class BucketMapper {



    // Konverton Bucket entitetin në BucketResponseDto (për t’u kthyer tek klienti)
    public static BucketResponseDto entityToDto(Bucket bucket) {
        BucketResponseDto dto = new BucketResponseDto();
        dto.setId(bucket.getId());

        // Mapojmë listën e BucketItem në listë DTO
        List<BucketItemDto> itemDtos = new ArrayList<>();
        for (BucketItem item : bucket.getItems()) {
            itemDtos.add(BucketItemMapper.entityToDto(item));
        }
        dto.setItems(itemDtos);

        return dto;
    }

    // Konverton BucketRequestDto në Bucket entitet për ruajtje në DB
    public static Bucket bucketDtoToEntity(BucketRequestDto dto, User user, List<Product> products) {
        Bucket bucket = new Bucket();
        bucket.setId(dto.getId());
        bucket.setUser(user);

        List<BucketItem> items = new ArrayList<>();
        for (BucketItemDto itemDto : dto.getItems()) {
            // Gjej produktin përkatës në listën e produkteve sipas productId
            Product prod = null;
            for (Product p : products) {
                if (p.getId().equals(itemDto.getProductId())) {
                    prod = p;
                    break;
                }
            }
            if (prod == null) {
                throw new RuntimeException("Product not found: " + itemDto.getProductId());
            }

            // Krijo BucketItem entitet nga DTO dhe e lidh me bucket dhe produktin
            BucketItem item = BucketItemMapper.dtoToEntity(itemDto, prod, bucket);
            items.add(item);
        }
        bucket.setItems(items);

        return bucket;
    }
}