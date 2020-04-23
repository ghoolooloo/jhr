package jo.jhr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class SalesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesDTO.class);
        SalesDTO salesDTO1 = new SalesDTO();
        salesDTO1.setId(1L);
        SalesDTO salesDTO2 = new SalesDTO();
        assertThat(salesDTO1).isNotEqualTo(salesDTO2);
        salesDTO2.setId(salesDTO1.getId());
        assertThat(salesDTO1).isEqualTo(salesDTO2);
        salesDTO2.setId(2L);
        assertThat(salesDTO1).isNotEqualTo(salesDTO2);
        salesDTO1.setId(null);
        assertThat(salesDTO1).isNotEqualTo(salesDTO2);
    }
}
