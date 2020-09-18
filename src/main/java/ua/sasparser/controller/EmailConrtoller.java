package ua.sasparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.sasparser.domain.Offer;
import ua.sasparser.domain.Supplier;
import ua.sasparser.repos.SupplierRepository;
import ua.sasparser.service.OfferService;

import java.util.*;

import static java.lang.Long.parseLong;

@Controller
public class EmailConrtoller {

    private final OfferService offerService;
    private final SupplierRepository supplierRepository;

    public EmailConrtoller(OfferService offerService, SupplierRepository supplierRepository) {
        this.offerService = offerService;
        this.supplierRepository = supplierRepository;
    }

    @GetMapping("/email")
    public String emailcont (Model model) {

        Long supplerLong = parseLong(String.valueOf("-9999"));
        Supplier supplier = supplierRepository.getById(supplerLong);
        List<Offer> offers = new ArrayList<Offer>();
        Map<Offer, Integer> rest = offerService.getOfferYesterday(supplier);
        Set<Offer> offers1 = rest.keySet();
        for (Offer offer : offers1) {
              offers.add(offer);
        }
        model.addAttribute("offers", offers);
        return "email";
    }

}
