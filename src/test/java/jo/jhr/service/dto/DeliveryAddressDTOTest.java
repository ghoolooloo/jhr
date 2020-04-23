package jo.jhr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class DeliveryAddressDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryAddressDTO.class);
        DeliveryAddressDTO deliveryAddressDTO1 = new DeliveryAddressDTO();
        deliveryAddressDTO1.setId(1L);
        DeliveryAddressDTO deliveryAddressDTO2 = new DeliveryAddressDTO();
        assertThat(deliveryAddressDTO1).isNotEqualTo(deliveryAddressDTO2);
        deliveryAddressDTO2.setId(deliveryAddressDTO1.getId());
        assertThat(deliveryAddressDTO1).isEqualTo(deliveryAddressDTO2);
        deliveryAddressDTO2.setId(2L);
        assertThat(deliveryAddressDTO1).isNotEqualTo(deliveryAddressDTO2);
        deliveryAddressDTO1.setId(null);
        assertThat(deliveryAddressDTO1).isNotEqualTo(deliveryAddressDTO2);
    }
}
