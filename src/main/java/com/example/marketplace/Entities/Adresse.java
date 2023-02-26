package com.example.marketplace.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Entity
public class Adresse {
    //test2
    //test3
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="adreese1")
    private String adresse1;
    @Column(name="adreese2")
    private String adresse2;
    @Column(name="ville")
    private String ville;
    @Column(name="codePostale")
    private String codePostale;
    @Column(name="pays")
    private String pays;
    @OneToOne(mappedBy="adresse")
    private User user;
}
