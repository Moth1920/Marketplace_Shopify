package com.example.marketplace.Services;

import com.example.marketplace.Entities.User;

import java.util.Optional;

public interface UserService {
    public Optional<User> getUserById(Long id);
}
