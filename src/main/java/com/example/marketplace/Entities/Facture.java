package com.example.marketplace.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity

public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFacture;
    private Float prix;
    private Date dateFacture;
}
