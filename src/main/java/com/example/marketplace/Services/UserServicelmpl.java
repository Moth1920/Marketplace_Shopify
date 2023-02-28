package com.example.marketplace.Services;

import com.example.marketplace.Entities.User;
import com.example.marketplace.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class UserServicelmpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setNomUser(user.getNomUser());
        existingUser.setPasswordUser(user.getPasswordUser());
        existingUser.setEmailUser(user.getEmailUser());
        existingUser.setNumdetelUser(user.getNumdetelUser());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUser(id);
        userRepository.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

}




