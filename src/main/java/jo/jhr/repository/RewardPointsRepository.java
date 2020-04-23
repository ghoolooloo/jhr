package jo.jhr.repository;

import jo.jhr.domain.RewardPoints;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RewardPoints entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RewardPointsRepository extends JpaRepository<RewardPoints, Long> {
}
