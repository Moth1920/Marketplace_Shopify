package com.example.marketplace.Services;

import com.example.marketplace.Entities.User;
import com.example.marketplace.Repositories.UserRepository;
import com.example.marketplace.utils.PagingHeaders;
import com.example.marketplace.utils.PagingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    @Autowired
    UserRepository ur;

    @Override
    public List<User> retrieveAllUsers() {
        return (List<User>) ur.findAll();
    }
    @Override
    public User retrieveUser(Long id) {
        return ur.findById(id).orElse(null);
    }
    @Override
    public User updateUser(User user) {
        return ur.save(user);
    }
    @Override
    public User addUser(User user) {
        return ur.save(user);
    }
    @Override
    public void removeUser(Long id) {
        if ( retrieveUser(id)==null)
        {
            System.out.print("USER DOES NOT EXISTS");
        }
        ur.deleteById(id);
    }

    public PagingResponse get3(Specification<User> spec, HttpHeaders headers, Sort sort) {
        if (isRequestPaged(headers)) {
            return get(spec, buildPageRequest(headers, sort));
        } else {
            final List<User> entities = get(spec,sort);
            return new PagingResponse((long) entities.size(), 0L, 0L, 0L, 0L, entities);
        }
    }

    public List<User> get(Specification<User> spec, Sort sort) {
        return ur.findAll(spec,sort);
    }
    private boolean isRequestPaged(HttpHeaders headers) {
        return headers.containsKey(PagingHeaders.PAGE_NUMBER.getName()) && headers.containsKey(PagingHeaders.PAGE_SIZE.getName());
    }
    private Pageable buildPageRequest(HttpHeaders headers, Sort sort) {
        int page = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_NUMBER.getName())).get(0));
        int size = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_SIZE.getName())).get(0));
        return PageRequest.of(page, size, sort);
    }
    public PagingResponse get(Specification<User> spec, Pageable pageable) {
        Page<User> page = ur.findAll(spec, pageable);
        List<User> content = page.getContent();
        return new PagingResponse(page.getTotalElements(), (long) page.getNumber(), (long) page.getNumberOfElements(), pageable.getOffset(), (long) page.getTotalPages(), content);
    }


}


