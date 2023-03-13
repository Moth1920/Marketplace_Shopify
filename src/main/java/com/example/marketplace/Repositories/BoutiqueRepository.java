package com.example.marketplace.Repositories;

import com.example.marketplace.Entities.Boutique;
import com.example.marketplace.Entities.Categorie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoutiqueRepository extends CrudRepository<Boutique, Long> {
}
