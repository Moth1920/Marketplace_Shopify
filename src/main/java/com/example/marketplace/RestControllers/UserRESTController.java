package com.example.marketplace.RestControllers;
import com.example.marketplace.Entities.User;
import com.example.marketplace.Services.UserServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


import java.util.List;



@RestController
@RequestMapping("/api/v1/users")
public class UserRESTController {
    @Autowired
    private UserServicelmpl userServicelmpl;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userServicelmpl.createUser(user);
        return ResponseEntity.created(null).body(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userServicelmpl.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userServicelmpl.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServicelmpl.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userServicelmpl.getAllUsers();
        return ResponseEntity.ok(users);
    }
}

