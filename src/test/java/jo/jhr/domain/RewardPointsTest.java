package jo.jhr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class RewardPointsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RewardPoints.class);
        RewardPoints rewardPoints1 = new RewardPoints();
        rewardPoints1.setId(1L);
        RewardPoints rewardPoints2 = new RewardPoints();
        rewardPoints2.setId(rewardPoints1.getId());
        assertThat(rewardPoints1).isEqualTo(rewardPoints2);
        rewardPoints2.setId(2L);
        assertThat(rewardPoints1).isNotEqualTo(rewardPoints2);
        rewardPoints1.setId(null);
        assertThat(rewardPoints1).isNotEqualTo(rewardPoints2);
    }
}
