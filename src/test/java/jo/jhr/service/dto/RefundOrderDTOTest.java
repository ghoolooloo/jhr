package jo.jhr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class RefundOrderDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefundOrderDTO.class);
        RefundOrderDTO refundOrderDTO1 = new RefundOrderDTO();
        refundOrderDTO1.setId(1L);
        RefundOrderDTO refundOrderDTO2 = new RefundOrderDTO();
        assertThat(refundOrderDTO1).isNotEqualTo(refundOrderDTO2);
        refundOrderDTO2.setId(refundOrderDTO1.getId());
        assertThat(refundOrderDTO1).isEqualTo(refundOrderDTO2);
        refundOrderDTO2.setId(2L);
        assertThat(refundOrderDTO1).isNotEqualTo(refundOrderDTO2);
        refundOrderDTO1.setId(null);
        assertThat(refundOrderDTO1).isNotEqualTo(refundOrderDTO2);
    }
}
