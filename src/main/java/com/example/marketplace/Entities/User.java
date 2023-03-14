package com.example.marketplace.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Produit> produits;


   /* @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Order> order;*/

    @OneToOne
    private Cart cart;
    @OneToOne
    private Adresse adresse;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Reclamation> reclamations;
    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            // cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "postId")
    private List<Post> posts = new ArrayList<>();

    // TODO
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
            // orphanRemoval = true
    )
    @JsonIgnore
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "postCommentId")
    private List<PostComment> postComments = new ArrayList<>();
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostLike> postLikes = new ArrayList<>();
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostCommentLike> postCommentLikes = new ArrayList<>();

}

