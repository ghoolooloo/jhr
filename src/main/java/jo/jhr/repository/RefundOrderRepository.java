package jo.jhr.repository;

import jo.jhr.domain.RefundOrder;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RefundOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefundOrderRepository extends JpaRepository<RefundOrder, Long> {
}
