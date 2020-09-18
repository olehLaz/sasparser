package ua.sasparser.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.DateUtils;
import ua.sasparser.domain.Offer;
import ua.sasparser.domain.Supplier;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {



    Supplier getById(Long id);

    @Query("select u from Offer u where u.supplier = :supplier and u.date in (:date1, :date2)")
    List<Offer> getOfferBySuppler(@Param("supplier") Supplier supplier,
                                  LocalDate date1,
                                  LocalDate date2 );
}
