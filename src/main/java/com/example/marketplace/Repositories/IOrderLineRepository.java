package com.example.marketplace.Repositories;

import com.example.marketplace.Entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderLineRepository extends JpaRepository<OrderLine,Long> {
}
