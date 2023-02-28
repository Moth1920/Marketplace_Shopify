package com.example.marketplace.Services;

import com.example.marketplace.Entities.ProductComment;
import com.example.marketplace.Entities.Productlike;
import com.example.marketplace.Entities.Produit;
import com.example.marketplace.Entities.User;
import com.example.marketplace.Repositories.ProductCommentRepository;
import com.example.marketplace.Repositories.ProductlikeRepository;
import com.example.marketplace.Repositories.ProduitRepository;
import com.example.marketplace.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ForumServiceImpl implements ForumService {
    @Autowired
    ProductlikeRepository productlikeRepository;
    @Autowired
    ProductCommentRepository productCommentRepository;
    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Productlike saveLike(Productlike productlike) {
        return productlikeRepository.save(productlike);
    }

    @Override
    public void deleteLikeById(Long idproductlike) {
        productlikeRepository.deleteById(idproductlike);

    }

    @Override
    public void saveComment(ProductComment productComment,Long idUser,Long idProduit) {
        User user = userRepository.findById(idUser).orElse(null);
        Produit produit = produitRepository.findById(idProduit).orElse(null);

        productComment.setUser(user);
        productComment.setProduit(produit);

        productCommentRepository.save(productComment);
    }

    @Override
    public void deleteCommentById(Long idProductComment) {
        productCommentRepository.deleteById(idProductComment);
    }

    @Override
    public List<ProductComment> getCommentsByPostId(Long idProduit) {
        return null;
    }

    @Override
    public Productlike likeProduit(Long idProduit, Long idUser) {
      /*  Produit produit = produitRepository.findById(idProduit).orElse(null);

        User user = userRepository.findById(idUser).orElse(null);

        Productlike productlike = new Productlike();
        productlike.setProduit(produit);
        productlike.setUser(user);


        return productlikeRepository.save(productlike);
    }
*/
        return null;}

}
