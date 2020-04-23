package jo.jhr.repository;

import jo.jhr.domain.CardHolder;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CardHolder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CardHolderRepository extends JpaRepository<CardHolder, Long> {
}
