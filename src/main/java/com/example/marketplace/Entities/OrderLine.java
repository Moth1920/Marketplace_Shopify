package com.example.marketplace.Entities;

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

