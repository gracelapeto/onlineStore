package com.example.onlineStore.controllers;

import com.example.onlineStore.dtos.AddProductBucketDto;
import com.example.onlineStore.dtos.BucketResponseDto;
import com.example.onlineStore.dtos.mapper.BucketMapper;
import com.example.onlineStore.entities.Bucket;
import com.example.onlineStore.entities.Product;
import com.example.onlineStore.entities.User;
import com.example.onlineStore.repositories.UserRepository;
import com.example.onlineStore.services.impl.BucketServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buckets")
public class BucketController {

    private final BucketServiceImp bucketService;
    private final BucketMapper bucketMapper;
    private final UserRepository userRepository;

    @Autowired
    public BucketController(BucketServiceImp bucketService, BucketMapper bucketMapper, UserRepository userRepository) {
        this.bucketService = bucketService;
        this.bucketMapper = bucketMapper;
        this.userRepository = userRepository;
    }

    @PostMapping("/createBucketByUser")
    public ResponseEntity<BucketResponseDto> createBucket(@RequestBody User user) {
        Bucket bucket = bucketService.createBucketForUser(user);
        return ResponseEntity.ok(BucketMapper.entityToDto(bucket));
    }


    //to be seen
    @PostMapping("/addProduct")
    public ResponseEntity<BucketResponseDto> addProductToBucket(@RequestBody AddProductBucketDto dto) {
        Bucket bucket = bucketService.addProductToBucket(dto.getUserId(), dto.getProductId(), dto.getQuantity());
        return ResponseEntity.ok(BucketMapper.entityToDto(bucket));
    }

    @PostMapping("/remove/")
    public ResponseEntity<BucketResponseDto> removeProduct(@RequestParam Long userId,
                                                           @RequestParam Long productId) {
        bucketService.removeProductFromBucket(userId,productId);
        return 

    }



}
