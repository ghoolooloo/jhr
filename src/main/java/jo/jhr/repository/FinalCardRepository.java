package jo.jhr.repository;

import jo.jhr.domain.FinalCard;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FinalCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FinalCardRepository extends JpaRepository<FinalCard, Long> {
}
