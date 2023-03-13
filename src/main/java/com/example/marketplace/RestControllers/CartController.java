package com.example.marketplace.RestControllers;

import com.example.marketplace.Entities.Cart;
import com.example.marketplace.Entities.OrderLine;
import com.example.marketplace.Entities.Produit;
import com.example.marketplace.Repositories.ICartRepository;
import com.example.marketplace.Repositories.ProduitRepository;
import com.example.marketplace.Services.ICartService;
import com.example.marketplace.Services.IOrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;
    @Autowired
    private IOrderLineService orderLineService;
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private ProduitRepository produitRepository;

    @PostMapping("AddorderLines/{idCart}")
    public void addOrderLineToCart(@RequestBody List<OrderLine> orderLines, @PathVariable Long idCart) {
        cartService.addProductToCart(orderLines,idCart);
    }


    //Affect Product to orderline

    @PostMapping("AffectProductToOrderLine/{idOrderLine}/{idProduit}")
    public void affectProduct(@PathVariable Long idProduit , @PathVariable Long idOrderLine){
        orderLineService.assignProduitToOrderLine(idProduit,idOrderLine);
    }



    //   methode affect product to orderline and add it to the cart        it s working !!!!

    @PostMapping("/orders/{idCart}")
    public OrderLine createOrderLine(@PathVariable Long idCart, @RequestParam Long idProduit, @RequestParam int quantity) {
        Cart cart = cartRepository.findById(idCart).orElse(null);
        Produit produit = produitRepository.findById(idProduit).orElse(null);
        OrderLine orderLine = new OrderLine();
        orderLine.setProduit(produit);
        orderLine.setQuantity(quantity);
        // orderLine.setPrixOrderLine(prixOrderLine);
        orderLine.setCart(cart);
        cartRepository.save(cart);
        cartService.addOrderLineToCart(idCart,idProduit,quantity);
        return orderLine;
    }





//    @PostMapping("/cart/{idProduit}")
//    public String updateCartItem(@PathVariable("idProduit") Long idProduit,
//                                 @RequestParam("quantity") Integer quantity,
//                                 HttpSession session) {
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart == null) {
//            return "redirect:/cart";
//        }
//        cart.updateQuantity(productId, quantity);
//        session.setAttribute("cart", cart);
//        return "redirect:/cart";
//    }

//    @PostMapping("/cart/modifier")
//    public Cart modifierQuantite(@RequestParam Long idCart,@RequestParam Long idProduit, @RequestParam Integer quantity, HttpSession session) {
//        // Code pour modifier la quantité du produit dans le panier
//        //  return "redirect:/cart"; // Rediriger vers la page du panier
//        List<OrderLine> cart = (List<OrderLine>) session.getAttribute("cart");
//
//        for (OrderLine orderLine : cart) {
//            if (orderLine.getProduit().getIdProduit().equals(idProduit)) {
//                // orderLine.setQuantity(quantity);
//                cartService.updateCartItem(idCart, idProduit, quantity);
//                break;
//            }
//
//        }
//       // return cart;
//        return null;
//    }
}
