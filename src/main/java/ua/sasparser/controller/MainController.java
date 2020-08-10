package ua.sasparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.sasparser.repos.CategoryRepository;
import ua.sasparser.repos.OfferRepository;
import ua.sasparser.domain.Category;
import ua.sasparser.domain.Offer;
import ua.sasparser.parser.XMLParserSAX;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private final OfferRepository offerRepository;
    private final CategoryRepository categoryRepository;

    public MainController(OfferRepository offerRepository, CategoryRepository categoryRepository) {
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/satellite") //адрес
    public String main(Map<String, Object> model) {
    //    Iterable<Offers> offers1 = offerRepository.saveAll(XMLParserSAX.xmlParserSAX());
        Iterable<Offer> offers =  offerRepository.findAll();
        model.put("offers", offers);

        return "satellite"; //main.mustache
    }

    @PostMapping("/satellite")
    public String savingOffer() {
        List<Category> categoryList = XMLParserSAX.xmlParserSAXCategory();

        List<Offer> offersList = XMLParserSAX.xmlParserSAX();
        for (Category category : categoryList){
            categoryRepository.save(category);
        }
        for (Offer offer : offersList) {
            System.out.println("save -- "  + offer.toString() + " !!!!! ");
            offerRepository.save(offer);
        }
        return "redirect:/satellite";
    }





}
