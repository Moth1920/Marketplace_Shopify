package com.example.marketplace.Repositories;

import com.example.marketplace.Entities.ProductComment;
import com.example.marketplace.Entities.Produit;
import com.example.marketplace.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductCommentRepository extends CrudRepository<ProductComment,Long> {


}
