package com.example.marketplace.Repositories;

import com.example.marketplace.Entities.Productlike;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface ProductlikeRepository extends CrudRepository<Productlike,Long> {


}

