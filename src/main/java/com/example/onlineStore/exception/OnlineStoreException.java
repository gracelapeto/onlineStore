package com.example.onlineStore.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class OnlineStoreException extends RuntimeException {


 private OnlineStoreException(String message) {
        super(message);
    }

    public static OnlineStoreException userNotFound(Long id) {
        return new OnlineStoreException("User with id " + id + " not found");
    }

    public static OnlineStoreException usernameExists(String username) {
        return new OnlineStoreException("User with username '" + username + "' already exists");
    }

    public static OnlineStoreException productNotFound(Long id) {
        return new OnlineStoreException("Product with id " + id + " not found");
    }

    public static OnlineStoreException insufficientStock(String productName) {
        return new OnlineStoreException("Not enough stock for product: " + productName);
    }


    public static OnlineStoreException categoryNotFound(Long id) {
        return new OnlineStoreException("Category with id " + id + " not found");
    }


    public static OnlineStoreException idMustNotBeNull() {
        return new OnlineStoreException("ID must not be null when updating an existing entity");
    }


    public static OnlineStoreException orderFailed(String reason) {
        return new OnlineStoreException("Order failed: " + reason);
    }
    public static OnlineStoreException notFound(Class<?> clazz, String id) {
        return new OnlineStoreException(clazz.getSimpleName() + " with id " + id + " not found");
    }

    public static OnlineStoreException userAlreadyExists(String username) {
        return new OnlineStoreException("User with username '" + username + "' already exists");
    }
}
