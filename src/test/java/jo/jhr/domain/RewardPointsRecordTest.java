package jo.jhr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class RewardPointsRecordTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RewardPointsRecord.class);
        RewardPointsRecord rewardPointsRecord1 = new RewardPointsRecord();
        rewardPointsRecord1.setId(1L);
        RewardPointsRecord rewardPointsRecord2 = new RewardPointsRecord();
        rewardPointsRecord2.setId(rewardPointsRecord1.getId());
        assertThat(rewardPointsRecord1).isEqualTo(rewardPointsRecord2);
        rewardPointsRecord2.setId(2L);
        assertThat(rewardPointsRecord1).isNotEqualTo(rewardPointsRecord2);
        rewardPointsRecord1.setId(null);
        assertThat(rewardPointsRecord1).isNotEqualTo(rewardPointsRecord2);
    }
}
