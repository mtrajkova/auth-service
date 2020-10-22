package com.bachelor.authservice.service;

import com.bachelor.authservice.client.UserServiceCaller;
import com.bachelor.authservice.configuration.TokenService;
import com.bachelor.authservice.exception.UserNotFound;
import com.bachelor.authservice.exception.EmailAlreadyExists;
import com.bachelor.authservice.model.User;
import com.bachelor.authservice.model.UserDto;
import com.bachelor.authservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final TokenService tokenService;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserServiceCaller userServiceCaller;

    public UserServiceImpl(TokenService tokenService, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserServiceCaller userServiceCaller) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userServiceCaller = userServiceCaller;
    }

    @Override
    public String getJwtTokenForUser(User user) throws UserNotFound {
        User foundUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())
                .orElseThrow(UserNotFound::new);
        return this.tokenService.generateToken(foundUser);
    }

    @Override
    public void signUpUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExists(user.getEmail());
        }

        //TODO refactor this
        if (user.isAdmin() == null) {
            user.setAdmin(false);
        }
        //TODO find a way to encrypt password
        userRepository.save(user);
        updateUsername(user);
    }

    private void updateUsername(User user) {
        String newUsername = user.getFullName().toLowerCase().replaceAll("\\s+", ".");
        if (userRepository.findByUsername(newUsername).isPresent()) {
            user.setUsername(newUsername.concat("." + user.getId()));
        }

        userRepository.save(user);
        userServiceCaller.saveUser(new UserDto(newUsername));
    }
}
