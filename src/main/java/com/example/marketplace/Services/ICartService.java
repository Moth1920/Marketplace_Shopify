package com.example.marketplace.Services;

import com.example.marketplace.Entities.Cart;
import com.example.marketplace.Entities.OrderLine;

import java.util.List;

public interface ICartService {
    void addProductToCart(List<OrderLine> orderLines, Long idCart);
    Cart getCartById(Long idCart);
    void deleteOrderLineFromCart(Long idOrderLine, Long idCart);
    void addOrderLineToCart(Long idCart,Long idProduit,int quantity);
    void  emptyTheCart();
    List<OrderLine> showOrderLine(Long idCart);

    Cart updateCartItem(Long idCart, Long idProduit, Integer quantity);
}
