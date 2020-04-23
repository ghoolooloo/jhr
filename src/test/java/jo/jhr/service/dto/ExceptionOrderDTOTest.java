package jo.jhr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class ExceptionOrderDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExceptionOrderDTO.class);
        ExceptionOrderDTO exceptionOrderDTO1 = new ExceptionOrderDTO();
        exceptionOrderDTO1.setId(1L);
        ExceptionOrderDTO exceptionOrderDTO2 = new ExceptionOrderDTO();
        assertThat(exceptionOrderDTO1).isNotEqualTo(exceptionOrderDTO2);
        exceptionOrderDTO2.setId(exceptionOrderDTO1.getId());
        assertThat(exceptionOrderDTO1).isEqualTo(exceptionOrderDTO2);
        exceptionOrderDTO2.setId(2L);
        assertThat(exceptionOrderDTO1).isNotEqualTo(exceptionOrderDTO2);
        exceptionOrderDTO1.setId(null);
        assertThat(exceptionOrderDTO1).isNotEqualTo(exceptionOrderDTO2);
    }
}
