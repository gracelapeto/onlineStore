package com.example.onlineStore.exception;

public class UserException extends RuntimeException {

    public static UserException userAlreadyExistsException(String username) {
        String message = String.format("User with username %s exists!", username);
        return new UserException(message);
    }
}
