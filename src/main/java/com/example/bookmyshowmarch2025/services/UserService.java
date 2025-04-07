package com.example.bookmyshowmarch2025.services;

import com.example.bookmyshowmarch2025.models.User;

import java.util.Optional;

public interface UserService {

    public Optional<User> findById(int userId);

}
