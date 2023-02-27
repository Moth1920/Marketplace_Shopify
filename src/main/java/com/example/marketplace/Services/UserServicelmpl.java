package com.example.marketplace.Services;

import com.example.marketplace.Entities.User;
import com.example.marketplace.Repositories.UserRepository;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;

import java.util.ListIterator;


public class UserServicelmpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        existingUser.setNomUser(user.getNomUser());
        existingUser.setPasswordUser(user.getPasswordUser());
        existingUser.setEmailUser(user.getEmailUser());
        existingUser.setNumdetelUser(user.getNumdetelUser());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    //jjjj
}

}


