package jo.jhr.repository;

import jo.jhr.domain.ClosedOrder;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ClosedOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClosedOrderRepository extends JpaRepository<ClosedOrder, Long> {
}
