package jo.jhr.repository;

import jo.jhr.domain.ExceptionOrder;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ExceptionOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExceptionOrderRepository extends JpaRepository<ExceptionOrder, Long> {
}
