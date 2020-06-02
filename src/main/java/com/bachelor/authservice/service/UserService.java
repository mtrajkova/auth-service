package com.bachelor.authservice.service;

import com.bachelor.authservice.exception.UserNotFound;
import com.bachelor.authservice.model.User;

public interface UserService {
    String getJwtTokenForUser(User user) throws UserNotFound;

    void signUpUser(User user);
}
