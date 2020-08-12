package ua.sasparser.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.sasparser.domain.Supplier;
import ua.sasparser.repos.CategoryRepository;
import ua.sasparser.repos.OfferRepository;
import ua.sasparser.domain.Offer;
import ua.sasparser.parser.XMLParserSAX;
import ua.sasparser.repos.SupplierRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static java.lang.Long.parseLong;

@Controller
public class OfferController {

    private final OfferRepository offerRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;


    public OfferController(OfferRepository offerRepository, CategoryRepository categoryRepository, SupplierRepository supplierRepository) {
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    @GetMapping("/satellite") //адрес
    public String main(
            @RequestParam(required = false, defaultValue = "-9999") String  suppler,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
        ) {

        Long supplerLong = parseLong(String.valueOf(suppler));
        Supplier supplier = supplierRepository.getById(supplerLong);

        Page<Offer> page;
        if(suppler != null ){
            page = offerRepository.findBySuppliers(supplier, pageable);
        } else {
            page = offerRepository.findAll(pageable);
        }

        model.addAttribute("page", page);
        model.addAttribute("url", "/satellite");
        model.addAttribute("suppler", suppler);

        return "satellite";
    }

    @PostMapping("/satellite")
    public String savingOffer() {
          List<Offer> offersList = XMLParserSAX.xmlParserSAX();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (Offer offer : offersList) {
      //      if (offer.getDate() == LocalDate.parse(dateFormat.format(new Date()))) {
                System.out.println("save -- " + offer.toString() + " !!!!! ");
                offerRepository.save(offer);
      //      }
      //      else {
      //          System.out.println("save --  missing  !!!!! ");
      //      }
        }
        return "redirect:/satellite";
    }





}
