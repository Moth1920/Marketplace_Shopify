package com.example.marketplace.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class OrderLine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idOrderLine;
    private Float prixOrderLine;

    private Integer quantity;


    //  Difference with the main Project
    private Long orderLineTotal;
    @Enumerated(EnumType.STRING)
    private OrderLineStatus orderLineStatus=OrderLineStatus.activated;

    @ManyToOne
    @JsonIgnore
    Commande commande;

    @ManyToOne
    @JsonIgnore
    Cart cart;


    //relation between orderLine and Product One to One
    // @OneToMany(cascade = CascadeType.ALL, mappedBy="orderlines")
    //private Set<Produit> produits;
    @OneToOne
    private Produit produit;


}
