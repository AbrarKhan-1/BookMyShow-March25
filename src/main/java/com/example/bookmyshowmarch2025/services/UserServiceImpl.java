package com.example.bookmyshowmarch2025.services;

import com.example.bookmyshowmarch2025.models.User;
import com.example.bookmyshowmarch2025.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(int userId) {
        return userRepository.findById(userId);
    }

}
