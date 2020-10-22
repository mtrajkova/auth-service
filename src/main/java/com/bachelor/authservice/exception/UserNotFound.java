package com.bachelor.authservice.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("Wrong credentials");
    }
}
