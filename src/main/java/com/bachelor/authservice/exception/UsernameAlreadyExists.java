package com.bachelor.authservice.exception;

public class UsernameAlreadyExists extends RuntimeException {
    public UsernameAlreadyExists(String username) {
        super(String.format("Username %s already exists", username));
    }
}
