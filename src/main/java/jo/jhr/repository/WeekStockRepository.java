package jo.jhr.repository;

import jo.jhr.domain.WeekStock;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WeekStock entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WeekStockRepository extends JpaRepository<WeekStock, Long> {
}
