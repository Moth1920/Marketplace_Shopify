package com.example.marketplace.Services;

import com.example.marketplace.Entities.Facture;
import com.example.marketplace.Entities.Commande;
import com.example.marketplace.Entities.User;

import java.util.List;
//test
public interface IFactureService {
    Facture createFacture(Long IdCmd);

    void sendFacture(Facture facture, Long userId);
    void deleteFacture(Facture facture);
    void deleteFactureById(Long id);
    /* float calculeTotal(List<OrderLine> orderLines);*/
    Facture getFactureById(Long id);
    List<Facture> getAllFactures();
}


