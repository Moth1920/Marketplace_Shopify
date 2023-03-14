package com.example.marketplace.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Commande implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long idOrder;
    @Temporal(TemporalType.DATE)
    private Date dateOrder;
    private Float orderTotalPrice;
    //private int ProductId;
    //private string ProductName;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus=OrderStatus.NEW;



    @OneToOne
    @JsonIgnore
    private Facture facture;
    @OneToOne
    private Cart cart;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="commande")
    @JsonIgnore
    private Set<OrderLine> orderLines;







}


