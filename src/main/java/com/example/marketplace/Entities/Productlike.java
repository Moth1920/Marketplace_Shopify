package com.example.marketplace.Entities;

import javax.persistence.*;

@Entity

public class Productlike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idproductlike;
    private Boolean Etatproductlike;

    @ManyToOne
    private Produit productlike;



}
