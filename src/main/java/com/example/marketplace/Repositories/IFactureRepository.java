package com.example.marketplace.Repositories;

import com.example.marketplace.Entities.Facture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFactureRepository extends CrudRepository<Facture,Long> {
}
