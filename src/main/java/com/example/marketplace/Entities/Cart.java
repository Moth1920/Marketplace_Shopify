package com.example.marketplace.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idCart;


    @ManyToOne
    PromotionCode promotionCode;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<OrderLine> orderLines;

    //relation between Cart and User One to One
    @OneToOne(mappedBy="cart")
    private User user;

//Dans la classe User
    //@OneToOne
//  private Cart cart;
}
