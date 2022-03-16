package com.example.dmteam.service;

import com.example.dmteam.model.User;
import com.example.dmteam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(User user) {
        Optional<User> userFound = userRepository.findUserByEmail(user.getEmail());
        if (userFound.isPresent()) {
            throw new IllegalStateException("Email already in use");
        }
        userRepository.save(user);
    }

    public List<User> index() {
        return userRepository.findAll();
    }
}
