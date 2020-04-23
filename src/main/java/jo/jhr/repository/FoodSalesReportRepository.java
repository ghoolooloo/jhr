package jo.jhr.repository;

import jo.jhr.domain.FoodSalesReport;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FoodSalesReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FoodSalesReportRepository extends JpaRepository<FoodSalesReport, Long> {
}
