package com.bachelor.authservice.client;

import com.bachelor.authservice.model.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserServiceCaller {

    private final UserServiceClient userServiceClient;

    public UserServiceCaller(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    public void saveUser(UserDto user) {
        this.userServiceClient.saveUser(user);
    }
}
