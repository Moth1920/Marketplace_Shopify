package com.example.marketplace.Services;

import com.example.marketplace.Entities.Delivery;
import com.example.marketplace.Entities.Facture;

import java.util.Date;
import java.util.List;

public interface IDeliveryService {
    //Delivery createDelivery(List<Facture> factures);
    Delivery createDelivery(List<Long> facturesIds, Long adresseId);



    List<Delivery> getAllDeliveries();

    void updateDelivery(Delivery delivery);

    void deleteDelivery(Delivery delivery);

    void deleteDeliveryById(Long idDelivery);
    Delivery getDeliveryById(Long id);
    List<Delivery> getDeliveriesBetweenDates(Date startDate, Date endDate);

    public double calculateDistance(Long delivId);
}
