package jo.jhr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class RefundOrderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefundOrder.class);
        RefundOrder refundOrder1 = new RefundOrder();
        refundOrder1.setId(1L);
        RefundOrder refundOrder2 = new RefundOrder();
        refundOrder2.setId(refundOrder1.getId());
        assertThat(refundOrder1).isEqualTo(refundOrder2);
        refundOrder2.setId(2L);
        assertThat(refundOrder1).isNotEqualTo(refundOrder2);
        refundOrder1.setId(null);
        assertThat(refundOrder1).isNotEqualTo(refundOrder2);
    }
}
