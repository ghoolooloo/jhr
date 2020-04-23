package jo.jhr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class DeliveryAddressTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryAddress.class);
        DeliveryAddress deliveryAddress1 = new DeliveryAddress();
        deliveryAddress1.setId(1L);
        DeliveryAddress deliveryAddress2 = new DeliveryAddress();
        deliveryAddress2.setId(deliveryAddress1.getId());
        assertThat(deliveryAddress1).isEqualTo(deliveryAddress2);
        deliveryAddress2.setId(2L);
        assertThat(deliveryAddress1).isNotEqualTo(deliveryAddress2);
        deliveryAddress1.setId(null);
        assertThat(deliveryAddress1).isNotEqualTo(deliveryAddress2);
    }
}
