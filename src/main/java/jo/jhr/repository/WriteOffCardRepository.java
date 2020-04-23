package jo.jhr.repository;

import jo.jhr.domain.WriteOffCard;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WriteOffCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WriteOffCardRepository extends JpaRepository<WriteOffCard, Long> {
}
