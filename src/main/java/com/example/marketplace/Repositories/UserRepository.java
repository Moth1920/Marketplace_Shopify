package com.example.marketplace.Repositories;

import com.example.marketplace.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
