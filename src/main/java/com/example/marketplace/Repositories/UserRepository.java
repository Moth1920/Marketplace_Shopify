package com.example.marketplace.Repositories;

import com.example.marketplace.Entities.Role;
import com.example.marketplace.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>
{
    List<User> finByRole (Role role);
}

