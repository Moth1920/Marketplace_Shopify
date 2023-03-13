package com.example.marketplace.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produit implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idProduit;
    private String nomProduit;
    private Float prixProduit;
    private int quantity;
    private Date dateCreation;
    @ManyToOne
    @JsonIgnore
    private Categorie categorie;

    @OneToMany (mappedBy = "productlike" ,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Productlike> Likes;

    @OneToOne(mappedBy="produit")
    @JsonIgnore
    private OrderLine orderLine;
    @ManyToOne
    @JsonIgnore
    private User user;

}
