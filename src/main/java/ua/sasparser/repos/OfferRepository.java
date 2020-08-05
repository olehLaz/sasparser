package ua.sasparser.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.sasparser.domain.Offer;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAll();

    @Override
    Offer getOne(Long aLong);

    @Override
    List<Offer> findAllById(Iterable<Long> iterable);

    Offer save(Offer offers);
}
