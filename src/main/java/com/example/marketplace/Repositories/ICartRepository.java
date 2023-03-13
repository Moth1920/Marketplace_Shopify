package com.example.marketplace.Repositories;

import com.example.marketplace.Entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart,Long> {
}
