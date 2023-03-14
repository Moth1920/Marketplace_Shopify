package com.example.marketplace.RestControllers;

import com.example.marketplace.Entities.Facture;
import com.example.marketplace.Entities.Commande;
import com.example.marketplace.Entities.OrderLine;
import com.example.marketplace.Entities.User;
import com.example.marketplace.Repositories.ICommandeRepository;
import com.example.marketplace.Repositories.UserRepository;
import com.example.marketplace.Services.CommandeServiceImpl;
import com.example.marketplace.Services.IFactureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/factures")
@Slf4j
public class FactureRESTController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IFactureService factureService;

    @Autowired
    private ICommandeRepository commandeRepository;



    @PostMapping("/create/{idCmd}")
    public ResponseEntity<Facture> createFacture(@PathVariable Long idCmd) {
        Optional<Commande> commande = commandeRepository.findById(idCmd);
        //commande =  commandeRepository.findById(idCmd);

        Facture facture = factureService.createFacture(idCmd);

        return new ResponseEntity<>(facture, HttpStatus.CREATED);
    }

    @PostMapping("/{factureId}/send/{userId}")
    public ResponseEntity<String> sendFacture(@PathVariable Long factureId, @PathVariable Long userId) {
        Facture facture = factureService.getFactureById(factureId);
        Optional<User> user = userRepository.findById(userId);
        log.info("FACTURE: "+facture.toString());
        factureService.sendFacture(facture, userId);
        return new ResponseEntity<>("Facture envoyée à "+user.get().getEmailUser() , HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Facture>> getAllFactures() {
        List<Facture> factures = factureService.getAllFactures();
        return new ResponseEntity<>(factures, HttpStatus.OK);
    }
    @DeleteMapping("/{factureId}")
    public ResponseEntity<String> deleteFactureById(@PathVariable Long factureId) {
        factureService.deleteFactureById(factureId);
        return new ResponseEntity<>("Facture supprimée avec succès", HttpStatus.OK);
    }

}
