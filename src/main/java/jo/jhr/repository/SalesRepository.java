package jo.jhr.repository;

import jo.jhr.domain.Sales;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Sales entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
}
