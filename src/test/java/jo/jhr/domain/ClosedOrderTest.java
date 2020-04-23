package jo.jhr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class ClosedOrderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClosedOrder.class);
        ClosedOrder closedOrder1 = new ClosedOrder();
        closedOrder1.setId(1L);
        ClosedOrder closedOrder2 = new ClosedOrder();
        closedOrder2.setId(closedOrder1.getId());
        assertThat(closedOrder1).isEqualTo(closedOrder2);
        closedOrder2.setId(2L);
        assertThat(closedOrder1).isNotEqualTo(closedOrder2);
        closedOrder1.setId(null);
        assertThat(closedOrder1).isNotEqualTo(closedOrder2);
    }
}
