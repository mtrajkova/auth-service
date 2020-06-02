package com.bachelor.authservice.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String username) {
        super(String.format("User with username %s was not found!", username));
    }
}
