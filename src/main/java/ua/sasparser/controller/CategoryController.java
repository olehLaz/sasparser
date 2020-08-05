package ua.sasparser.controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OfferRepository offerRepository;
    //   @Autowired
    //   private ZabirayRepository zabirayRepository;
    //   @Autowired
    //   private YatextileRepository yatextileRepository;
    @GetMapping("/category")
    public String categ(
            @RequestParam(required = false) String id,
            Model model) {
        Iterable<Category> categories;
        Iterable<Offer> offers;
        List<Long> longs;
        //   List<Long> longsZabiray;
        //   List<Long> longsYatextile;
        if (id != null && !id.isEmpty()) {
            categories = categoryRepository.findCategoriesByid(Long.parseLong(id));
            longs = categoryRepository.findOffersByCategories(Long.parseLong(id));
            offers = offerRepository.findAllById(longs);
        } else {
            // Iterable<Category> offers =  categoryRepository.findCategoriesByid(Long.parseLong("13570"));
            categories = categoryRepository.findAll();
            offers = null;
            //       zabirays = null;
            //       yatextiles =null;
        }
        model.addAttribute("categories", categories);
        model.addAttribute("offers", offers);
        //   model.addAttribute("zabirays", zabirays);
        //   model.addAttribute("yatextiles", yatextiles);
        return "category";
    }

    @PostMapping("/category")
    public String savingOffer() {
        List<Category> categoryList = XMLParserSAX.xmlParserSAXCategory();
        for (Category category : categoryList) {
            categoryRepository.save(category);
        }
        return "/category";
    }


}
