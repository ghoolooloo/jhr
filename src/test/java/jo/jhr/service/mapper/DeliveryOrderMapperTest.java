package jo.jhr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DeliveryOrderMapperTest {

    private DeliveryOrderMapper deliveryOrderMapper;

    @BeforeEach
    public void setUp() {
        deliveryOrderMapper = new DeliveryOrderMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(deliveryOrderMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(deliveryOrderMapper.fromId(null)).isNull();
    }
}
