package com.example.marketplace.Services;

import com.example.marketplace.Entities.Commande;
import com.example.marketplace.Entities.OrderLine;

import java.util.List;

public interface IOrderService {
    Commande getOrderById(Long idOrder);
    List<Commande> getAllOrders();
    Commande createOrder(Commande order);
    Commande updateOrder(Commande order);
    void deleteOrder(Commande order);
    void deleteOrderById(Long idOrder);
    Commande placeOrder(String userName, String userAddress, List<OrderLine> orderLines);
    List<Commande> getOrdersByUserId(Long idUser);
    void updateOrderStatus(Commande order);
    Commande createOrder(Long cartId);
}
