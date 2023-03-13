package com.example.marketplace.Repositories;

import com.example.marketplace.Entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommandeRepository extends JpaRepository<Commande,Long> {
}
