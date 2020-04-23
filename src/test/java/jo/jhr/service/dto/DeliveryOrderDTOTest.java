package jo.jhr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class DeliveryOrderDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryOrderDTO.class);
        DeliveryOrderDTO deliveryOrderDTO1 = new DeliveryOrderDTO();
        deliveryOrderDTO1.setId(1L);
        DeliveryOrderDTO deliveryOrderDTO2 = new DeliveryOrderDTO();
        assertThat(deliveryOrderDTO1).isNotEqualTo(deliveryOrderDTO2);
        deliveryOrderDTO2.setId(deliveryOrderDTO1.getId());
        assertThat(deliveryOrderDTO1).isEqualTo(deliveryOrderDTO2);
        deliveryOrderDTO2.setId(2L);
        assertThat(deliveryOrderDTO1).isNotEqualTo(deliveryOrderDTO2);
        deliveryOrderDTO1.setId(null);
        assertThat(deliveryOrderDTO1).isNotEqualTo(deliveryOrderDTO2);
    }
}
