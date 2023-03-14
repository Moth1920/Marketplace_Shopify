package com.example.marketplace.Repositories;

import com.example.marketplace.Entities.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findTop3ByOrderByLikesNumberDesc();

}
