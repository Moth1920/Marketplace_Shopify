package com.example.marketplace.Services;

import com.example.marketplace.Entities.OrderLine;

import java.util.List;

public interface IOrderLineService {
    List<OrderLine> retrieveAllOrderLines();
    OrderLine getOrderLineById(Long idOrderLine);
    OrderLine createOrderLine(OrderLine orderLine);
    OrderLine updateOrderLine(OrderLine orderLine);
    void deleteOrderLine(OrderLine orderLine);
    void deleteOrderLineById(Long idOrderLine);
    void assignProduitToOrderLine(Long idProduit, Long idOrderLine);
}
