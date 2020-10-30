package com.bachelor.authservice.service;

import com.bachelor.authservice.client.UserServiceCaller;
import com.bachelor.authservice.configuration.TokenService;
import com.bachelor.authservice.exception.UserNotFound;
import com.bachelor.authservice.exception.EmailAlreadyExists;
import com.bachelor.authservice.model.LoginResponse;
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
        user = userRepository.save(user);
        updateUsername(user);
    }

    @Override
    public User getUserDetails(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFound::new);
    }

    @Override
    public LoginResponse loginUser(User user) {
        User foundUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).orElseThrow(UserNotFound::new);
        return new LoginResponse(getJwtTokenForUser(user), foundUser.getUsername());
    }

    @Override
    public User updateUser(String username, User user) {
        User foundUser = userRepository.findByUsername(username).orElseThrow(UserNotFound::new);
        if (!foundUser.getEmail().equals(user.getEmail()) && !user.getEmail().isEmpty()) {
            foundUser.setEmail(user.getEmail());
        }
        if (!foundUser.getFullName().equals(user.getFullName()) && !user.getFullName().isEmpty()) {
            foundUser.setFullName(user.getFullName());
        }
        if (foundUser.getPhoneNumber() != null && !foundUser.getPhoneNumber().equals(user.getPhoneNumber()) && !user.getPhoneNumber().isEmpty()) {
            foundUser.setPhoneNumber(user.getPhoneNumber());
        }

        return userRepository.save(foundUser);
    }

    private void updateUsername(User user) {
        String newUsername = user.getFullName().toLowerCase().replaceAll("\\s+", ".");
        if (userRepository.findByUsername(newUsername).isPresent()) {
            user.setUsername(newUsername.concat(user.getId().toString()));
        } else {
            user.setUsername(newUsername);
        }

        userRepository.save(user);
        userServiceCaller.saveUser(new UserDto(newUsername));
    }
}
