package com.bachelor.authservice.service;

import com.bachelor.authservice.exception.UserNotFound;
import com.bachelor.authservice.model.LoginResponse;
import com.bachelor.authservice.model.User;

public interface UserService {
    String getJwtTokenForUser(User user) throws UserNotFound;

    void signUpUser(User user);

    User getUserDetails(String username);

    LoginResponse loginUser(User user);

    User updateUser(String username, User user);

    void deleteUser(String email);
}
