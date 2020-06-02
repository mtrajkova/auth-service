package com.bachelor.authservice.configuration;

import com.bachelor.authservice.model.User;

public interface TokenService {
    String generateToken(User user);
}
