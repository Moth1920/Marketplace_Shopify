package com.example.marketplace.Repositories;

import com.example.marketplace.Entities.Delivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery,Long> {
}
