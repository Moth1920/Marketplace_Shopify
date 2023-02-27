package com.example.marketplace.Entities;

import com.fasterxml.jackson.databind.DatabindException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Reclamation implements Serializable {
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
