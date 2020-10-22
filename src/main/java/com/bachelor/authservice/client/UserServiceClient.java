package com.bachelor.authservice.client;

import com.bachelor.authservice.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Employee-Consumer")
public interface UserServiceClient {
    @PostMapping("/users/save-user")
    void saveUser(@RequestBody UserDto user);
}
