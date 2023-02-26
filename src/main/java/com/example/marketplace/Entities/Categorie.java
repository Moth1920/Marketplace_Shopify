package com.example.marketplace.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCat;
    private String nomCat;
    private String descriptionCat;
    @JsonIgnore
    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits;
    @ManyToOne
    private Boutique boutique;
}
