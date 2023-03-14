package com.example.marketplace.RestControllers;

import com.example.marketplace.Entities.Delivery;
import com.example.marketplace.Entities.Facture;
import com.example.marketplace.Services.IDeliveryService;
import com.example.marketplace.Services.IFactureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Delivery")
@Slf4j
public class DeliveryRESTController {
    @Autowired
    IDeliveryService deliveryService;
    @Autowired
    IFactureService factureService;
    @PostMapping("/create/{adresseId}")

    public ResponseEntity<Delivery> createDelivery(@RequestBody List<Long> facturesIds, @PathVariable("adresseId") long adresseId) {
        Delivery delivery = deliveryService.createDelivery(facturesIds, adresseId);
        return new ResponseEntity<>(delivery, HttpStatus.CREATED);

    }
    @GetMapping("/all")
    public ResponseEntity<List<Delivery>>getAllDeliveries() {
        List<Delivery> deliveries = deliveryService.getAllDeliveries();
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    @GetMapping("/distance/{delivId}")
    public ResponseEntity calculateDistance(@PathVariable("delivId") Long delivId) {
        Double distance = deliveryService.calculateDistance(delivId);
        return new ResponseEntity(distance, HttpStatus.OK);
    }
    @GetMapping("/allBetween/{startDate}/{endDate}")
    public ResponseEntity<List<Delivery>>  getDeliveriesBetweenDates(@PathVariable("startDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date startDate, @PathVariable("endDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date endDate) {

        List<Delivery> deliveries = deliveryService.getDeliveriesBetweenDates(startDate, endDate);
        return new ResponseEntity<>(deliveries, HttpStatus.OK);

    }}
