package com.example.marketplace.Services;

import com.example.marketplace.Entities.*;
import com.example.marketplace.Repositories.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

@Service
@Slf4j
public class FactureServiceImpl implements IFactureService {
    @Autowired
    IFactureRepository factureRepository;
    @Autowired
    IDeliveryRepository deliveryRepository;
    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    ICommandeRepository commandeRepository;
    @Autowired
    IOrderLineRepository orderLineRepository;
    @Autowired
    UserRepository userRepository;




    @Override
    public Facture createFacture(Long IdCmd) {
        Optional<Commande> commande = commandeRepository.findById(IdCmd);
        Facture facture = new Facture();
        facture.setCommande(commande.get());


        float totalPrice = 0.0f;
        for (OrderLine orderLine : commande.get().getOrderLines()) {
            totalPrice += orderLine.getOrderTotal();
        }
        facture.setPrix(totalPrice);
        facture.setDateFacture(new Date());

        commande.get().setFacture(facture);

        factureRepository.save(facture);

        try {

            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream("facture-" + facture.getIdFacture() + ".pdf"));
            document.open();


            Paragraph titre = new Paragraph("Facture #" + facture.getIdFacture(), new Font(Font.FontFamily.HELVETICA, 18));
            document.add(titre);
            document.add(new Paragraph("Date: " + facture.getDateFacture().toString()));
            document.add(new Paragraph("Client: " + commande.get().getUser().getNomUser()));

            document.add(new Paragraph("Total: " + facture.getPrix()));


            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }

        return facture;
    }

    @Override
    public void sendFacture(Facture facture, Long userId) {

        User user = userRepository.findById(userId).get();

        ///////////////////
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("noreplyshopify23@gmail.com");
        mailSender.setPassword("hsusyyliovncpseb");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreplyshopify23@gmail.com");
        message.setTo(user.getEmailUser());
        message.setSubject("Facture");
        message.setText("Facture ! Prix à payer : "+facture.getPrix());
        mailSender.send(message);


        System.out.println("Envoi de la facture par e-mail à " + user.getEmailUser() + "...");

        System.out.println("Montant: " + facture.getPrix() + " dinars");

    }

    @Override
    public void deleteFacture(Facture facture) {
        factureRepository.delete(facture);
    }

    @Override
    public void deleteFactureById(Long id) {
        factureRepository.deleteById(id);
    }

    /*@Override
    public float calculeTotal(List<OrderLine> orderLines) {
        float total = 0;
        for (OrderLine orderLine : orderLines) {
            total += orderLine.getQuantity() * orderLine.getProduit().getPrixProduit();
        }
        return total;
    }*/


    @Override
    public Facture getFactureById(Long id) {
        return factureRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Facture not found with id: " + id));
    }

    @Override
    public List<Facture> getAllFactures() {
        return (List<Facture>) factureRepository.findAll();
    }
}



