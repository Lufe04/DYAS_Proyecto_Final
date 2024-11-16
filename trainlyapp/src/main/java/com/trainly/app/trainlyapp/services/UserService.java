package com.trainly.app.trainlyapp.services;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trainly.app.trainlyapp.models.UserEntity;
import com.trainly.app.trainlyapp.repositories.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity registerUser(String username, String password, String user_type) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe.");
        }
        String encodedPassword = passwordEncoder.encode(password);
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setUserType(user_type);
        return userRepository.save(user);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
