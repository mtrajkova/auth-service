package com.bachelor.authservice.exception;

public class WrongCredentials extends RuntimeException {
    public WrongCredentials() {
        super("Wrong credentials");
    }
}
