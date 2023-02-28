package com.example.marketplace.Services;

import com.example.marketplace.Entities.User;

import java.util.List;

public interface UserService {
        User createUser(User user);
        User getUser(Long id);
        User updateUser(Long id, User user);
        void deleteUser(Long id);
        List<User> getAllUsers();

}
