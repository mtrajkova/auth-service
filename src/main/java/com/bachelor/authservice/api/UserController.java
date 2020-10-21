package com.bachelor.authservice.api;

import com.bachelor.authservice.exception.UserNotFound;
import com.bachelor.authservice.model.User;
import com.bachelor.authservice.service.UserService;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) throws UserNotFound {
        Gson gson = new Gson();
        return ResponseEntity.ok(gson.toJson(this.userService.getJwtTokenForUser(user)));
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody User user) {
        this.userService.signUpUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
