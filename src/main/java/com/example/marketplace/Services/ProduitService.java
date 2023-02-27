package com.example.marketplace.Services;

import com.example.marketplace.Entities.Categorie;
import com.example.marketplace.Entities.Produit;

import java.util.List;

public interface ProduitService {

    List<Produit> findAllProduits();
    List<Categorie> findAllCategories();




    Produit saveProduit(Produit p);
    Categorie saveCategorie(Categorie c);

    Produit updateProduit(Produit p);
    Categorie updateCategorie(Categorie c);



    void deleteProduit(Produit p);

    void deleteProduitById(Long id);

}
