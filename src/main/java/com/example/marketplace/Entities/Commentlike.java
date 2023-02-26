package com.example.marketplace.Entities;

import javax.persistence.*;

@Entity
//forum
public class Commentlike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommentlike;
    private Boolean Etat;
    @ManyToOne
    private ProductComment commentlike;

}
