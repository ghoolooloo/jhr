package jo.jhr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DeliveryAddressMapperTest {

    private DeliveryAddressMapper deliveryAddressMapper;

    @BeforeEach
    public void setUp() {
        deliveryAddressMapper = new DeliveryAddressMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(deliveryAddressMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(deliveryAddressMapper.fromId(null)).isNull();
    }
}
