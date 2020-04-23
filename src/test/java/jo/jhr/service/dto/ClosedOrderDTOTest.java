package jo.jhr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class ClosedOrderDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClosedOrderDTO.class);
        ClosedOrderDTO closedOrderDTO1 = new ClosedOrderDTO();
        closedOrderDTO1.setId(1L);
        ClosedOrderDTO closedOrderDTO2 = new ClosedOrderDTO();
        assertThat(closedOrderDTO1).isNotEqualTo(closedOrderDTO2);
        closedOrderDTO2.setId(closedOrderDTO1.getId());
        assertThat(closedOrderDTO1).isEqualTo(closedOrderDTO2);
        closedOrderDTO2.setId(2L);
        assertThat(closedOrderDTO1).isNotEqualTo(closedOrderDTO2);
        closedOrderDTO1.setId(null);
        assertThat(closedOrderDTO1).isNotEqualTo(closedOrderDTO2);
    }
}
