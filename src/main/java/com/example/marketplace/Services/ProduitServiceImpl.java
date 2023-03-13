package com.example.marketplace.Services;

import com.example.marketplace.Entities.Boutique;
import com.example.marketplace.Entities.Categorie;
import com.example.marketplace.Entities.Produit;
import com.example.marketplace.Entities.User;
import com.example.marketplace.Repositories.BoutiqueRepository;
import com.example.marketplace.Repositories.CategorieRepository;
import com.example.marketplace.Repositories.ProduitRepository;
import com.example.marketplace.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProduitServiceImpl implements ProduitService{
    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    BoutiqueRepository boutiqueRepository;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    TemplateEngine  templateEngine;
    @Override
    public void affectprodtocat(Produit p,Long idCategorie, Long idUser) {
        Categorie c = categorieRepository.findById(idCategorie).orElse(null);
        p.setCategorie(c);
        User u = userRepository.findById(idUser).orElse(null);
        p.setUser(u);
        produitRepository.save(p);
        List<User> userList = (List<User>) userRepository.findAll();
        for (User user : userList) {
            sendEmail(user.getEmailUser(), c.getNomCat());
        }
    }
    public void sendEmail(String Recipient,String nomCat) {
        try {
            Context context = new Context();
            context.setVariable("nomCat", nomCat);
            String htmlContent = templateEngine.process("email", context);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
            helper.setTo(Recipient);
            helper.setSubject("Product added");
            helper.setText(htmlContent, true);

            javaMailSender.send(message);

        } catch (MessagingException e) {
            System.out.println("error sending email:" + e.getMessage());
        }

    }
    @Override
    public List<Produit> findByQuantityLessThanEqual(int quantity) {
        return produitRepository.findByQuantityLessThanEqual(quantity);
    }
    @Override
    public String showAlert() {
        List<Produit> produits = produitRepository.findByQuantityLessThanEqual(2);
        if (produits.size() > 0) {

            return "ALERT: There are " + produits.size() + " product(s) with quantity less than or equal to 2";
        } else {
            return "No products found with quantity less than or equal to 2";
        }
    }
    @Override
    public void affectcattobou(Categorie categorie, Long idbou) {
        Boutique boutique = boutiqueRepository.findById(idbou).orElse(null);
        categorie.setBoutique(boutique);

        categorieRepository.save(categorie);
    }
    @Override
    public List<Produit> findAllProduits(){
        return (List<Produit>) produitRepository.findAll();
    }

    @Override
    public List<Categorie> findAllCategories() {
        return (List<Categorie>) categorieRepository.findAll();
    }

    //@Override
    //public void addProd(Produit produit, MultipartFile image) {
    //  if(!image.isEmpty()){
    //}
    //}

    @Override
    public List<Produit> filterProduit(float minPrixProduit, float maxPrixProduit) {
        List<Produit> allProduit = (List<Produit>) produitRepository.findAll();

        List<Produit> filterProduit = allProduit.stream().filter(p -> p.getPrixProduit() >= minPrixProduit && p.getPrixProduit() <= maxPrixProduit).collect(Collectors.toList());
        return filterProduit;

    }



    @Override
    public Categorie saveCategorie(Categorie c) {
        return categorieRepository.save(c);
    }
    @Override
    public Boutique saveBoutique(Boutique b) { return boutiqueRepository.save(b);
    }

    @Override
    public Produit updateProduit(Produit p) {
        return produitRepository.save(p);
    }
    @Override
    public Categorie updateCategorie(Categorie c) {
        return categorieRepository.save(c);
    }
    @Override
    public Boutique updateBoutique(Boutique b) {
        return boutiqueRepository.save(b);
    }




    @Override
    public void deleteProduit(Produit p) {
        produitRepository.delete(p);
    }

    @Override
    public void deleteProduitById(Long idProduit) {
        produitRepository.deleteById(idProduit);
    }
    @Autowired
    private UserRepository userRepository;

    /*public void sendNotification(User seller, Produit produit) {
        List<User> usersToNotify = userRepository.findUsersByFollowedSellers(seller);
        for (User user : usersToNotify) {
            String message = String.format("Seller %s has added a new tool: %s", seller.getName(), tool.getName());
            Notification notification = new Notification(user, message);
            // Send notification using email, SMS, push notifications, etc.
            // ...
        }*/
}





