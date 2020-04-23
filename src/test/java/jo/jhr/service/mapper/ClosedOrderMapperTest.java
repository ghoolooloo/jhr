package jo.jhr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ClosedOrderMapperTest {

    private ClosedOrderMapper closedOrderMapper;

    @BeforeEach
    public void setUp() {
        closedOrderMapper = new ClosedOrderMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(closedOrderMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(closedOrderMapper.fromId(null)).isNull();
    }
}
