package com.example.marketplace.Services;

import com.example.marketplace.Entities.Cart;
import com.example.marketplace.Entities.OrderLine;
import com.example.marketplace.Entities.Produit;
import com.example.marketplace.Repositories.ICartRepository;
import com.example.marketplace.Repositories.IOrderLineRepository;
import com.example.marketplace.Repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Service
public class CartService implements ICartService{
    @Autowired
    IOrderLineRepository orderLinerepo;
    @Autowired
    ICartRepository cartRepository;
    @Autowired
    IOrderLineService orderLineService;
    @Autowired
    ProduitRepository produitRepository;

//    @Override
//    public List<OrderLine> addProductToCart(Long idCart) {
//        Cart cart = this.getCartById(idCart);
//        Set<OrderLine> set = cart.getOrderLines();
//        List<OrderLine> convertList = new ArrayList<OrderLine>();
//        for (OrderLine ol : set) {
//            convertList.add(ol);
//        }
//
//        return convertList;
//    }


    @Override
    public void addProductToCart(List<OrderLine> orderLines, Long idCart) {
        Cart cart = cartRepository.findById(idCart).orElse(null);

        for (OrderLine ol : orderLines) {
            ol.setCart(cart);
            orderLinerepo.save(ol);

        }


    }

    @Override
    public Cart getCartById(Long idCart) {
        return null;
    }

    @Override
    public void deleteOrderLineFromCart(Long idOrderLine, Long idCart) {
        OrderLine orderLine = orderLineService.getOrderLineById(idOrderLine);
        Cart cart = this.getCartById(idCart);


        //  cart.setCart(null);
        cart.setIdCart(null);
        // orderLineService.updateOrderLine(orderLine);

        Set<OrderLine> orderLines= cart.getOrderLines();
        orderLines.remove(orderLine);
        cart.setOrderLines(orderLines);
        //cart.setQte(s.getQte()-1);
        //this.updateCart(cart);
    }


    // it working !!!
    @Override
    public void addOrderLineToCart(Long idCart,Long idProduit,int quantity) {




        Cart cart = cartRepository.findById(idCart)
                .orElse(null);

        Produit produit = produitRepository.findById(idProduit)
                .orElse(null);

        Float  prixOrderLine= produit.getPrixProduit()*quantity;

        OrderLine orderLine = new OrderLine();
        orderLine.setProduit(produit);
        orderLine.setQuantity(quantity);
        ;orderLine.setPrixOrderLine(prixOrderLine);
        orderLine.setCart(cart);
        orderLinerepo.save(orderLine);
        //   cart.addOrderLine(orderLine);




    }

    @Override
    public void emptyTheCart() {
        List<OrderLine> orderLines = new ArrayList<OrderLine>();
        orderLines.clear();
    }

    @Override
    public List<OrderLine> showOrderLine(Long idCart) {

        // return cart.getOrderLines();


        //  return orderLinerepo.findByCartId(idCart) ;
        return null;
    }


//    @Override
//    public void addOrderLineToCart(Long userId, OrderLine orderLine) {
//        // get the user's cart
//        Cart cart = cartRepository.findBy(userId);
//
//        // associate the order line with the cart
//        orderLine.setCart(cart);
//
//        // save the order line
//        orderLinerepo.save(orderLine);
    //   }



    // fonction changer la quantite d un produit dans un orderline
    @Override
    public Cart updateCartItem(Long idCart, Long idProduit, Integer quantity) {
//       // Cart cart = cartRepository.findById(idCart).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", idCart));
//        Cart cart = cartRepository.findById(idCart).orElse(null);
//       // Produit produit = produitRepository.findById(idProduit).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
//       Produit produit = produitRepository.findById(idProduit).orElse(null);
//       // OrderLine orderLine = cart.getOrderLines().stream().filter(item -> item.getProduit().equals(produit)).findFirst().orElse(null);
//        if (orderLine != null) {
//            orderLine.setQuantity(quantity);
//        }
//        cartRepository.save(cart);
//        return cart;
        return  null;
    }


}
