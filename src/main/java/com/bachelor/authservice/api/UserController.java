package com.bachelor.authservice.api;

import com.bachelor.authservice.exception.UserNotFound;
import com.bachelor.authservice.model.User;
import com.bachelor.authservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) throws UserNotFound {
        return ResponseEntity.ok(this.userService.getJwtTokenForUser(user));
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody User user) {
        this.userService.signUpUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
