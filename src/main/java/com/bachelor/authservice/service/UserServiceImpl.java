package com.bachelor.authservice.service;

import com.bachelor.authservice.configuration.TokenService;
import com.bachelor.authservice.exception.UserNotFound;
import com.bachelor.authservice.exception.UsernameAlreadyExists;
import com.bachelor.authservice.model.User;
import com.bachelor.authservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final TokenService tokenService;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(TokenService tokenService, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String getJwtTokenForUser(User user) throws UserNotFound {
        User foundUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())
                .orElseThrow(() -> new UserNotFound(user.getUsername()));
        return this.tokenService.generateToken(foundUser);
    }

    @Override
    public void signUpUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExists(user.getUsername());
        }

        //TODO find a way to encrypt password
        userRepository.save(user);
    }
}
