package com.example.onlineStore.controllers;

import com.example.onlineStore.dtos.AddProductBucketDto;
import com.example.onlineStore.dtos.BucketResponseDto;
import com.example.onlineStore.dtos.RemoveProductDto;
import com.example.onlineStore.dtos.mapper.BucketMapper;
import com.example.onlineStore.entities.Bucket;
import com.example.onlineStore.entities.Product;
import com.example.onlineStore.entities.User;
import com.example.onlineStore.repositories.ProductRepository;
import com.example.onlineStore.repositories.UserRepository;
import com.example.onlineStore.services.BucketService;
import com.example.onlineStore.services.impl.BucketServiceImp;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buckets")
@RequiredArgsConstructor
public class BucketController {

    private final BucketService bucketService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @PostMapping("/createBucketByUser")
    public ResponseEntity<BucketResponseDto> createBucket(@RequestBody User user) {
        Bucket bucket = bucketService.createBucketForUser(user.getId());
        return ResponseEntity.ok(BucketMapper.entityToDto(bucket));
    }


    @PostMapping("/addProduct")
    public ResponseEntity<String> addProductToBucket(@RequestParam AddProductBucketDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        bucketService.addProductToBucket(user, product, dto.getQuantity());

        return ResponseEntity.ok("Product added to bucket successfully.");
    }

    @GetMapping("/get")
    public ResponseEntity<BucketResponseDto> getBucket() {
        return ResponseEntity.ok(bucketService.getByUser());
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearUserBucket(@PathVariable Long userId) {
        bucketService.clearBucket(userId);
        return ResponseEntity.ok("Bucket cleared for user with ID: " + userId);
    }
}




