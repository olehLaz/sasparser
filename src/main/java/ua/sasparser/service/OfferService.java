package ua.sasparser.service;

import org.springframework.stereotype.Service;
import ua.sasparser.domain.Offer;
import ua.sasparser.domain.Supplier;

import org.springframework.transaction.annotation.Transactional;
import ua.sasparser.repos.SupplierRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class OfferService {

    final SupplierRepository supplierRepository;

    public OfferService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Transactional(readOnly = true)
    public Map<Offer, Integer> getOfferYesterday(Supplier supplier){

        Date dateNow1 = new Date();
            Date dateNow2 = new Date(new Date().getTime() - 24*3600*1000);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate date1 = LocalDate.parse(dateFormat.format(dateNow1));
            LocalDate date2 = LocalDate.parse(dateFormat.format(dateNow2));

        List<Offer> offerList = new ArrayList<>();
        offerList = supplierRepository.getOfferBySuppler(supplier, date1, date2);
        Map<Offer, Integer> resultOffer = new HashMap<>();
        Map<Offer, Integer> finalOffer = new HashMap<>();
        Integer chk;
        for (Offer offer : offerList) {
              chk = resultOffer.get(offer);
              resultOffer.put(offer, chk == null ? 1 : chk + 1);

        }
        //List<Offer> result = new ArrayList(resultOffer.keySet());
               // result.forEach(System.out::println);
               // System.out.println("\n2. Export Map Value to List...");
//                List<Integer> result2 = new ArrayList(resultOffer.values());
 //               result2.forEach(System.out::println);

                Set<Map.Entry<Offer, Integer>> setOffr = resultOffer.entrySet();
                for (Map.Entry<Offer, Integer> hmof : setOffr) {
                    if(hmof.getValue().equals(Integer.parseInt("1"))) {
                        System.out.println("++++++++++++++++++++++++++++++++++++");
                        finalOffer.put(hmof.getKey(), hmof.getValue());
                    }
                }
        System.out.println(finalOffer);
        return finalOffer;
    }
}
