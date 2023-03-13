package com.example.marketplace.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Adresse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idA;
    @Column(name="adreese1")
    private String adresse1;
   /* @Column(name="adreese2")
    private String adresse2;*/
    @Column(name="ville")
    private String ville;
    @Column(name="codePostale")
    private String codePostale;
    @Column(name="pays")
    private String pays;
    @OneToOne(mappedBy="adresse")
    private User user;
}
