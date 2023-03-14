package com.example.marketplace.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Delivery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelvery;
    private Integer numdelivery;
    private Date datedelivery;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Adresse adresse;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private List<Facture> factures;
}


