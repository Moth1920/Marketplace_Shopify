package com.example.marketplace.Repositories;

import com.example.marketplace.Entities.PostComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepository extends CrudRepository<PostComment,Long> {


}
