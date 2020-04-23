package jo.jhr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RewardPointsRecordMapperTest {

    private RewardPointsRecordMapper rewardPointsRecordMapper;

    @BeforeEach
    public void setUp() {
        rewardPointsRecordMapper = new RewardPointsRecordMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(rewardPointsRecordMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(rewardPointsRecordMapper.fromId(null)).isNull();
    }
}
