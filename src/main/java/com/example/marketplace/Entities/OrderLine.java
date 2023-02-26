package com.example.marketplace.Entities;

import javax.persistence.*;

@Entity

public class OrderLine {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idOrderLine;
    private Long productreference;

    private Integer quantity;



    private Long orderTotal;

    //  @ManyToOne
    // Order order;

    // @ManyToOne
    // Cart cart;

    //relation between orderLine and Product One to One
    @OneToOne(mappedBy="orderLine")
    private Produit produit;

//Dans la classe Product
    //@OneToOne
//  private OrderLine orderLine;

}

