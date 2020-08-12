package ua.sasparser.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.sasparser.domain.Offer;
import org.springframework.data.domain.Pageable;
import ua.sasparser.domain.Supplier;

import java.util.List;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {
    Page<Offer> findAll(Pageable pageable);


    @Query("select u from Offer u where u.supplier = :supplier")
    Page<Offer> findBySuppliers(@Param("supplier") Supplier supplier,
                               Pageable pageable);


    @Query("select u from Offer u where u.name = :name")
    Page<Offer> findByNames(@Param("name") String name, Pageable pageable);

    Page<Offer> findByNameContaining(String name, Pageable pageable);



    Offer getOne(Long aLong);

    @Override
    List<Offer> findAllById(Iterable<Long> iterable);

    Offer save(Offer offers);
}
