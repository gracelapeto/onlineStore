package com.example.onlineStore.services;

import com.example.onlineStore.entities.Bucket;
import com.example.onlineStore.entities.Product;
import com.example.onlineStore.entities.User;
import com.example.onlineStore.entities.BucketItem;

import java.util.List;

public interface BucketService {

    Bucket createBucketForUser(User user);

    Bucket createBucketForUser(Long userId);

    public void addProductToBucket(User user, Product product, int quantity);

    public void removeProductFromBucket(User user, BucketItem bucketItem);

    void removeProductFromBucket(Long userId, Long productId);

    public void updateProductQuantity(User user, BucketItem bucketItem, int newQuantity);

    public List<BucketItem> getBucketItems(User user);

    public void clearBucket(User user);

    public Double getTotalPrice(User user);











}
