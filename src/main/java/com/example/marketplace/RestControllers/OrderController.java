package com.example.marketplace.RestControllers;

import com.example.marketplace.Entities.Cart;
import com.example.marketplace.Entities.Commande;
import com.example.marketplace.Repositories.ICartRepository;
import com.example.marketplace.Services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService orderService;
    @Autowired
    ICartRepository cartRepository;

    @PostMapping("/addOrder")
    @ResponseBody
    public Commande saveOrder(@RequestBody Commande order)
    {
        return orderService.createOrder(order);

    }
    @PutMapping("/UpdateOrder")
    @ResponseBody
    public Commande updateOrder(@RequestBody Commande order)
    {
        return orderService.updateOrder(order);
    }
    @DeleteMapping("/DeleteOrder")

    public void deleteOrder(@RequestBody Commande order)
    {
        orderService.deleteOrder(order);
    }
    @DeleteMapping("/DeleteOrderById/{idOrder}")

    public void deleteOrderById(@PathVariable("idOrder") Long idOrder)
    {
        orderService.deleteOrderById(idOrder);
    }

    @GetMapping("/getOrders")
    public List<Commande> getOrders(){

        return orderService.getAllOrders();
    }

    @GetMapping("/getOrder/{idOrder}")
    public Commande getOrderById(@PathVariable("idOrder") Long idOrder)
    {

        return orderService.getOrderById(idOrder);
    }

//    @PutMapping("/{orderId}/status")
//    public void updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus newStatus) {
//        Commande order = orderService.getOrderById(orderId);
//        orderService.updateOrderStatus(order, newStatus);
//    }

    @PostMapping("/orders/{idCart}")
    public void createOrder(@PathVariable("idCart") Long idCart) {
        // Retrieve the cart associated with the order
        Cart cart = cartRepository.findById(idCart).orElse(null);

        orderService.createOrder(idCart);

//        // Retrieve all order lines from the cart
//        Set<OrderLine> orderLines = cart.getOrderLines();
//
//        // Create an Order entity
//        Commande order = new Commande();
//        order.setCustomerName(cart.getCustomerName());
//        order.setCustomerEmail(cart.getCustomerEmail());
//        order.setTotalAmount(cart.getTotalAmount());
//        order.setDateOrder(new Date());
//
//        // Create a list of OrderLine entities
//        List<OrderLine> newOrderLines = new ArrayList<>();
//        for (OrderLine orderLine : orderLines) {
//            OrderLine newOrderLine = new OrderLine();
//            newOrderLine.setProduct(orderLine.getProduct());
//            newOrderLine.setQuantity(orderLine.getQuantity());
//            newOrderLine.setPrice(orderLine.getPrice());
//            newOrderLine.setOrder(order);
//            newOrderLines.add(newOrderLine);
//        }
//
//        // Save the order entity and the list of order line entities to the database
//        order.setOrderLines(newOrderLines);
//        orderRepository.save(order);
//
//        // Remove the order lines from the cart
//        cart.clearOrderLines();
//        cartRepository.save(cart);
//return order;
//      //  return ResponseEntity.ok("Order created successfully");
//    }

    }


}

