package com.example.marketplace.Services;

import com.example.marketplace.Entities.Cart;
import com.example.marketplace.Entities.Commande;
import com.example.marketplace.Entities.OrderLine;
import com.example.marketplace.Entities.OrderStatus;
import com.example.marketplace.Repositories.ICartRepository;
import com.example.marketplace.Repositories.ICommandeRepository;
import com.example.marketplace.Repositories.IOrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImplements implements IOrderService{

    @Autowired
    ICommandeRepository orderRepository;
    @Autowired
    IOrderLineRepository orderLineRepository;
    @Autowired
    IOrderNotificationService notificationService;

    @Autowired
    ICartRepository cartRepository;
    @Autowired
    ICartService cartService;
    @Override
    public Commande getOrderById(Long idOrder) {
//        return orderRepository.findById(idOrder).orElse(null);
        return orderRepository.findById(idOrder).orElse(null);

    }

    @Override
    public List<Commande> getAllOrders() {
        List<Commande> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;

    }

    @Override
    public Commande createOrder(Commande order) {
        return orderRepository.save(order);

    }

    @Override
    public Commande updateOrder(Commande order) {
        return orderRepository.save(order);

    }

    @Override
    public void deleteOrder(Commande order) {
        orderRepository.delete(order);
    }

    @Override
    public void deleteOrderById(Long idOrder) {
        orderRepository.deleteById(idOrder);

    }

    // creer commande depuis les orderlines de la panier
    @Override
    public Commande placeOrder(String userName, String userAddress, List<OrderLine> orderLines) {
        Float orderTotalPrice = calculateTotalAmount(orderLines);
        // Commande order = new Commande(userName, userAddress, orderLines, orderTotalPrice);
        //Commande order = new Commande(userName, userAddress, orderLines, orderTotalPrice);

        //return orderRepository.save(order);
        return null;
    }

    private Float calculateTotalAmount(List<OrderLine> orderLines) {
        return (float) orderLines.stream().count();
    }
    @Override
    public List<Commande> getOrdersByUserId(Long idUser) {
        // return orderRepository.findByUserId(idUser);
        return null;

    }

    @Override
    public void updateOrderStatus(Commande order) {
//        order.setOrderStatus(orderStatus);
//       //  Code to update order status in database
//
//        // Send notification to user
//        String message = "Your order #" + order.getIdOrder() + " is now " + orderStatus;
//        notificationService.sendNotification(message, order.getUser().getEmail());
    }


    @Override
    public Commande createOrder(Long cartId) {
        // Retrieve the cart associated with the order
        Cart cart = cartRepository.findById(cartId).orElse(null);

        // Retrieve all order lines from the cart
        List<OrderLine> orderLines = (List<OrderLine>) cart.getOrderLines();

        // Create an Order entity
        Float somme = calculateTotalAmount(orderLines);
        Commande order = new Commande();
        // order.setCustomerName(cart.getCustomerName());
        // order.setCustomerEmail(cart.getCustomerEmail());
        order.setOrderTotalPrice(somme);
        order.setDateOrder(new Date());
        order.setOrderStatus(OrderStatus.valueOf("PROCESSING"));


        //  order.setOrderLines(newOrderLines);
        orderRepository.save(order);

        // Remove the order lines from the cart

        cartService.emptyTheCart();
        cartRepository.save(cart);

        return order;


    }

}


