package com.example.marketplace.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity

public class ProductComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductComment;
    private String text;
    private Date dateProductcomment;
    @OneToMany (mappedBy = "commentlike")
    @JsonIgnore
    private List<Commentlike> commentlikes;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "idProduit", referencedColumnName = "idProduit")
    private Produit produit;

    private Instant createdDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private User user;

}
