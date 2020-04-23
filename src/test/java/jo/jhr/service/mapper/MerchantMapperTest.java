package jo.jhr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MerchantMapperTest {

    private MerchantMapper merchantMapper;

    @BeforeEach
    public void setUp() {
        merchantMapper = new MerchantMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(merchantMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(merchantMapper.fromId(null)).isNull();
    }
}
