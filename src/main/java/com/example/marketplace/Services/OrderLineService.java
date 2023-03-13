package com.example.marketplace.Services;

import com.example.marketplace.Entities.OrderLine;
import com.example.marketplace.Entities.Produit;
import com.example.marketplace.Repositories.IOrderLineRepository;
import com.example.marketplace.Repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderLineService implements IOrderLineService{

    @Autowired
    IOrderLineRepository orderLinerepo;
    @Autowired
    ProduitRepository produitRepository;
//    @Autowired
//    private ProduitService produitService;


    @Override
    public List<OrderLine> retrieveAllOrderLines() {
        List<OrderLine> orderLines = new ArrayList<>();
        orderLinerepo.findAll().forEach(orderLines::add);
        return orderLines
                ;
    }

    @Override
    public OrderLine getOrderLineById(Long idOrderLine) {
        return orderLinerepo.findById(idOrderLine).orElse(null);
    }

    @Override
    public OrderLine createOrderLine(OrderLine orderLine) {
        return orderLinerepo.save(orderLine);
    }

    @Override
    public OrderLine updateOrderLine(OrderLine orderLine) {
        return orderLinerepo.save(orderLine);
    }

    @Override
    public void deleteOrderLine(OrderLine orderLine) {
        orderLinerepo.delete(orderLine);
    }

    @Override
    public void deleteOrderLineById(Long idOrderLine) {
        orderLinerepo.deleteById(idOrderLine);
    }

    @Override
    public void assignProduitToOrderLine(Long idProduit, Long idOrderLine) {



        OrderLine orderLine = orderLinerepo.findById(idOrderLine).orElse(null);
        Produit produit = produitRepository.findById(idProduit).orElse(null);


        orderLine.setProduit(produit);
        orderLinerepo.save(orderLine);











//        Produit p = produitService.retrieveProduit(idProduit);
//        OrderLine ol = this.getOrderLineById(idOrderLine);
//
//
//
//        p.setOrderline(ol);
//        prodservc.updateProduit(p);
//
//        Set<Produit> produits= s.getProduits();
//        produits.add(p);
//        ol.setProduits(produits);
//        ol.setQuantity(ol.getQuantity()+1);
//        this.updateOrderline(ol);
//
//        return produits;

    }
}

