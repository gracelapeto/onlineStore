package com.example.onlineStore.services.impl;

import com.example.onlineStore.dtos.BucketResponseDto;
import com.example.onlineStore.dtos.mapper.BucketMapper;
import com.example.onlineStore.entities.Bucket;
import com.example.onlineStore.entities.BucketItem;
import com.example.onlineStore.entities.Product;
import com.example.onlineStore.entities.User;
import com.example.onlineStore.repositories.BucketRepository;
import com.example.onlineStore.repositories.UserRepository;
import com.example.onlineStore.services.BucketService;
import com.example.onlineStore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BucketServiceImp implements BucketService {

    private final BucketRepository bucketRepository;
    private final UserService userService;

    @Autowired
    public BucketServiceImp(BucketRepository bucketRepository, UserRepository userRepository, UserService userService) {
        this.bucketRepository = bucketRepository;
        this.userService = userService;
    }

    @Override
    public Bucket createBucketForUser(Long userId) {

        User user = userService.findById(userId);
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        return bucketRepository.save(bucket);
    }

    @Override
    public void addProductToBucket(User user, Product product, int quantity) {
        Bucket bucket = user.getBucket();

        Optional<BucketItem> existingItem = bucket.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            BucketItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            BucketItem newItem = new BucketItem();
            newItem.setBucket(bucket);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            bucket.getItems().add(newItem);
        }

        bucketRepository.save(bucket);
    }


    @Override
    public void removeProductFromBucket(Long userId, Long productId) {
        User user = userService.findById(userId);

        Bucket bucket = user.getBucket();
        if (bucket == null) {
            new Bucket();
        }

        Optional<BucketItem> existingItem = bucket.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        existingItem.ifPresent(item -> bucket.getItems().remove(item));

        bucketRepository.save(bucket);
    }

    @Override
    public void updateProductQuantity(User user, BucketItem bucketItem, int newQuantity) {
        Bucket bucket = user.getBucket();

        Optional<BucketItem> existingItem = bucket.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(bucketItem.getProduct().getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            BucketItem item = existingItem.get();
            item.setQuantity(newQuantity);
            bucketRepository.save(bucket);
        } else {
            throw new RuntimeException("Product not found in bucket");
        }

    }

    @Override
    public List<BucketItem> getBucketItems(User user) {
        Bucket bucket = user.getBucket();
        return bucket.getItems();
    }

    @Override
    public void clearBucket(User user) {
        if (user == null) {
            throw new RuntimeException("User is null");
        }
        Bucket bucket = user.getBucket();
        if (bucket == null) {
            throw new RuntimeException("Bucket not found for user: " + user.getId());
        }
        bucket.getItems().clear();
        bucketRepository.save(bucket);
    }
    @Override
    public Double getTotalPrice(User user) {
        Bucket bucket = user.getBucket();
        List<BucketItem> items = bucket.getItems();
        Double totalPrice = 0.0;
        for (BucketItem item : items) {
            totalPrice += item.getProduct().getPrice() * item.getQuantity();
        }
        return totalPrice;
    }

    @Override
    public BucketResponseDto getByUser() {
       return BucketMapper.entityToDto(userService.getLoggedUser().getBucket());

    }

}
