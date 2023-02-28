package com.example.marketplace.Repositories;

import com.example.marketplace.Entities.Commentlike;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentlikeRepository extends CrudRepository<Commentlike,Long> {
}
