package com.example.marketplace.Entities;

import com.fasterxml.jackson.databind.DatabindException;

import javax.persistence.*;
import java.util.Date;
@Entity

public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idrec;
    private Date daterec;
    private String descriptionrec;
    @Enumerated(EnumType.STRING)
    private Etatrec etatRec;

    @OneToOne
    private OrderLine orderLine;

    @ManyToOne
    User user;
}
