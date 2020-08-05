package ua.sasparser.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.sasparser.domain.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
