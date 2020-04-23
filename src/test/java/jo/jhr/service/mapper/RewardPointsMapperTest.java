package jo.jhr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RewardPointsMapperTest {

    private RewardPointsMapper rewardPointsMapper;

    @BeforeEach
    public void setUp() {
        rewardPointsMapper = new RewardPointsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(rewardPointsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(rewardPointsMapper.fromId(null)).isNull();
    }
}
