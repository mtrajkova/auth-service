package com.bachelor.authservice.security;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyUu";
    public static final long EXPIRATION_TIME = 86_400_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
}
