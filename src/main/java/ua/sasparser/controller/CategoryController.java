package ua.sasparser.controller;

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

import java.util.List;

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
        return "category.ftl";
    }

    @PostMapping("/category")
    public String savingOffer() {
        List<Category> categoryList = XMLParserSAX.xmlParserSAXCategory();
        for (Category category : categoryList) {
            categoryRepository.save(category);
        }
        return "category.ftl";
    }


}
