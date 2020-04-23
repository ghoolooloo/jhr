package jo.jhr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class DeliveryOrderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryOrder.class);
        DeliveryOrder deliveryOrder1 = new DeliveryOrder();
        deliveryOrder1.setId(1L);
        DeliveryOrder deliveryOrder2 = new DeliveryOrder();
        deliveryOrder2.setId(deliveryOrder1.getId());
        assertThat(deliveryOrder1).isEqualTo(deliveryOrder2);
        deliveryOrder2.setId(2L);
        assertThat(deliveryOrder1).isNotEqualTo(deliveryOrder2);
        deliveryOrder1.setId(null);
        assertThat(deliveryOrder1).isNotEqualTo(deliveryOrder2);
    }
}
