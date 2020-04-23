package jo.jhr.repository;

import jo.jhr.domain.RewardPointsRecord;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RewardPointsRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RewardPointsRecordRepository extends JpaRepository<RewardPointsRecord, Long> {
}
