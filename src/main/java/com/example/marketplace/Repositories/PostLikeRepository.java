package com.example.marketplace.Repositories;

import com.example.marketplace.Entities.PostLike;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends CrudRepository<PostLike,Long> {


}

