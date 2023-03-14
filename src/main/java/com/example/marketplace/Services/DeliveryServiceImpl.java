package com.example.marketplace.Services;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.example.marketplace.Entities.Adresse;
import com.example.marketplace.Entities.Delivery;
import com.example.marketplace.Entities.Facture;
import com.example.marketplace.Repositories.IAdresseRepository;
import com.example.marketplace.Repositories.IDeliveryRepository;
import com.example.marketplace.Repositories.IFactureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DeliveryServiceImpl implements IDeliveryService {

    private static final double EARTH_RADIUS = 6371;

    @Autowired
    IDeliveryRepository deliveryRepository;
    @Autowired
    IFactureRepository factureRepository;

    @Autowired
    IAdresseRepository adresseRepository;


    @Override
    //public Delivery createDelivery(List<Facture> factures) {
    public Delivery createDelivery(List<Long> facturesIds, Long adresseId) {
        List<Facture> factures = new ArrayList<>();
        facturesIds.forEach(
                id -> factures.add(factureRepository.findById(id).get())
        );
        Delivery delivery = new Delivery();
        delivery.setFactures(factures);
        delivery.setNumdelivery(Integer.valueOf(generateDeliveryNumber()));
        delivery.setDatedelivery(new Date());
        //delivery.setAdresselivraison(delivery.getAdresselivraison());

        Adresse adresse = adresseRepository.findById(adresseId).get();

        log.info("HERE: ",adresse.toString());

        delivery.setAdresse(adresse);

        deliveryRepository.save(delivery);
        return delivery;
    }


    @Override
    public List<Delivery> getAllDeliveries() {
        return (List<Delivery>) deliveryRepository.findAll();
    }

    @Override
    public void updateDelivery(Delivery delivery) {
        deliveryRepository.save(delivery);
    }

    @Override
    public void deleteDelivery(Delivery delivery) {
        deliveryRepository.delete(delivery);
    }

    @Override
    public void deleteDeliveryById(Long idDelivery) {
        deliveryRepository.deleteById(idDelivery);
    }

    @Override
    public Delivery getDeliveryById(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Facture not found with id: " + id));
    }

    @Override
    public List<Delivery> getDeliveriesBetweenDates(Date startDate, Date endDate) {
        List<Delivery> deliveries = (List<Delivery>) deliveryRepository.findAll();
        List<Delivery> filteredDeliveries = new ArrayList<>();
        for (Delivery delivery : deliveries) {
            Date deliveryDate = delivery.getDatedelivery();
            if (deliveryDate.compareTo(startDate) >= 0 && deliveryDate.compareTo(endDate) <= 0) {
                filteredDeliveries.add(delivery);
            }
        }
        return filteredDeliveries;
    }




    private String generateDeliveryNumber() {
        Random random = new Random();
        int num = random.nextInt(99999);
        return String.format("%05d", num);
    }


    /////////////////////////////////////////////

    public double[] getAddressCoordinate(String address){
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("44ac5638d15a4e3fb710c1473ce1affe"); // TODO GET KEY
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(address);
        request.setMinConfidence(1);
        request.setNoAnnotations(false);
        request.setNoDedupe(true);
        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        return new double[] {response.getResults().get(0).getGeometry().getLat(),response.getResults().get(0).getGeometry().getLng()};
    }

    public double calculateDistance(Long delivId) {
        /////////////
        String address1 = "Ariana";
        String address2 = deliveryRepository.findById(delivId).get().getAdresse().getVille();
        /////////////

        double lat1=getAddressCoordinate(address1)[0];
        double lon1=getAddressCoordinate(address1)[1];
        double lat2=getAddressCoordinate(address2)[0];
        double lon2=getAddressCoordinate(address2)[1];
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

}

