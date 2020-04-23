package jo.jhr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class RewardPointsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RewardPointsDTO.class);
        RewardPointsDTO rewardPointsDTO1 = new RewardPointsDTO();
        rewardPointsDTO1.setId(1L);
        RewardPointsDTO rewardPointsDTO2 = new RewardPointsDTO();
        assertThat(rewardPointsDTO1).isNotEqualTo(rewardPointsDTO2);
        rewardPointsDTO2.setId(rewardPointsDTO1.getId());
        assertThat(rewardPointsDTO1).isEqualTo(rewardPointsDTO2);
        rewardPointsDTO2.setId(2L);
        assertThat(rewardPointsDTO1).isNotEqualTo(rewardPointsDTO2);
        rewardPointsDTO1.setId(null);
        assertThat(rewardPointsDTO1).isNotEqualTo(rewardPointsDTO2);
    }
}
