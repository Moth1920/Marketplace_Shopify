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
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idUser;
    private String nomUser;
    private String passwordUser;
    private String emailUser;
    private Integer numdetelUser;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy ="sender")
    @JsonIgnore
    private List<Chat>sentMessages;
    @OneToMany(mappedBy ="receiver")
    @JsonIgnore
    private List<Chat>receivedMessage;



   /* @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Order> order;*/

    @OneToOne
    private Cart cart;
    @OneToOne
    private Adresse adresse;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Reclamation> reclamations;
}
