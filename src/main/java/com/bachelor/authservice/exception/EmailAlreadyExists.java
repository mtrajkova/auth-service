package com.bachelor.authservice.exception;

public class EmailAlreadyExists extends RuntimeException {
    public EmailAlreadyExists(String email) {
        super(String.format("Email %s already exists", email));
    }
}
