package com.example.marketplace.Services;

import com.example.marketplace.Entities.Boutique;
import com.example.marketplace.Entities.Categorie;
import com.example.marketplace.Entities.Produit;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProduitService {

    void affectprodtocat(Produit produit, Long idCategorie, Long idUser);


    void affectcattobou(Categorie categorie, Long idbou);

    List<Produit> findAllProduits();
    List<Categorie> findAllCategories();

    //void addProd(Produit produit, MultipartFile image);
    List<Produit> filterProduit(float minPrixProduit, float maxPrixProduit);


    Categorie saveCategorie(Categorie c);
    Boutique saveBoutique(Boutique b);

    Produit updateProduit(Produit p);
    Categorie updateCategorie(Categorie c);
    Boutique updateBoutique(Boutique b);

    String showAlert();
    List<Produit> findByQuantityLessThanEqual(int quantity);

    void deleteProduit(Produit p);

    void deleteProduitById(Long idProduit);



}
