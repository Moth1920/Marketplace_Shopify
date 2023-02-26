package com.example.marketplace.Entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class PromotionCode {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long discountId;
    private String discountCode;
    private Long discountAmount;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private PromoStatus promoStatus;
    //promotion code can be applied to many  carts

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "promotionCode")
    private Set<Cart> carts;
}
