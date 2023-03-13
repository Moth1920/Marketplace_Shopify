package com.example.marketplace.Repositories;

import com.example.marketplace.Entities.Produit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends CrudRepository<Produit, Long> {
    List<Produit> findByQuantityLessThanEqual(int quantity);
}
