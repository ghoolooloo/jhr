package jo.jhr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class RewardPointsRecordDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RewardPointsRecordDTO.class);
        RewardPointsRecordDTO rewardPointsRecordDTO1 = new RewardPointsRecordDTO();
        rewardPointsRecordDTO1.setId(1L);
        RewardPointsRecordDTO rewardPointsRecordDTO2 = new RewardPointsRecordDTO();
        assertThat(rewardPointsRecordDTO1).isNotEqualTo(rewardPointsRecordDTO2);
        rewardPointsRecordDTO2.setId(rewardPointsRecordDTO1.getId());
        assertThat(rewardPointsRecordDTO1).isEqualTo(rewardPointsRecordDTO2);
        rewardPointsRecordDTO2.setId(2L);
        assertThat(rewardPointsRecordDTO1).isNotEqualTo(rewardPointsRecordDTO2);
        rewardPointsRecordDTO1.setId(null);
        assertThat(rewardPointsRecordDTO1).isNotEqualTo(rewardPointsRecordDTO2);
    }
}
