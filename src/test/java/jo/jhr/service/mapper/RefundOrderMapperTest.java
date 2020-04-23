package jo.jhr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RefundOrderMapperTest {

    private RefundOrderMapper refundOrderMapper;

    @BeforeEach
    public void setUp() {
        refundOrderMapper = new RefundOrderMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(refundOrderMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(refundOrderMapper.fromId(null)).isNull();
    }
}
