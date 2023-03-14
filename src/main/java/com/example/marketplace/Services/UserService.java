package com.example.marketplace.Services;

import com.example.marketplace.Entities.User;
import com.example.marketplace.utils.PagingResponse;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> retrieveAllUsers();
    User retrieveUser (Long id);
    User updateUser (User user);
    User addUser (User user);
    void removeUser (Long id);

    PagingResponse get3(Specification<User> spec, HttpHeaders headers, Sort sort);
}
