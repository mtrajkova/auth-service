package com.bachelor.authservice.api;

import com.bachelor.authservice.exception.UserNotFound;
import com.bachelor.authservice.model.LoginResponse;
import com.bachelor.authservice.model.User;
import com.bachelor.authservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody User user) throws UserNotFound {
        return ResponseEntity.ok(userService.loginUser(user));
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody User user) {
        this.userService.signUpUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/my/{username}")
    public ResponseEntity<User> signUp(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.getUserDetails(username));
    }

    @PatchMapping("/my/{username}")
    public ResponseEntity<User> updateUser(@PathVariable("username") String username, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(username, user));
    }

}
