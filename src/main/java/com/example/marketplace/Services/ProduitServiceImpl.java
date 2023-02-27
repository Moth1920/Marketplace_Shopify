package com.example.marketplace.Services;

import com.example.marketplace.Entities.Categorie;
import com.example.marketplace.Entities.Produit;
import com.example.marketplace.Repositories.CategorieRepository;
import com.example.marketplace.Repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProduitServiceImpl implements ProduitService{
    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    CategorieRepository categorieRepository;

    @Override
    public List<Produit> findAllProduits(){
        return (List<Produit>) produitRepository.findAll();
    }

    @Override
    public List<Categorie> findAllCategories() {
        return (List<Categorie>) categorieRepository.findAll();
    }




    @Override
    public Produit saveProduit(Produit p) {
        return produitRepository.save(p);
    }
    @Override
    public Categorie saveCategorie(Categorie c) {
        return categorieRepository.save(c);
    }

    @Override
    public Produit updateProduit(Produit p) {
        return produitRepository.save(p);
    }
    @Override
    public Categorie updateCategorie(Categorie c) {
        return categorieRepository.save(c);
    }




    @Override
    public void deleteProduit(Produit p) {
        produitRepository.delete(p);
    }

    @Override
    public void deleteProduitById(Long id) {
        produitRepository.deleteById(id);
    }




}
