package jo.jhr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class FinalCardDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinalCardDTO.class);
        FinalCardDTO finalCardDTO1 = new FinalCardDTO();
        finalCardDTO1.setId(1L);
        FinalCardDTO finalCardDTO2 = new FinalCardDTO();
        assertThat(finalCardDTO1).isNotEqualTo(finalCardDTO2);
        finalCardDTO2.setId(finalCardDTO1.getId());
        assertThat(finalCardDTO1).isEqualTo(finalCardDTO2);
        finalCardDTO2.setId(2L);
        assertThat(finalCardDTO1).isNotEqualTo(finalCardDTO2);
        finalCardDTO1.setId(null);
        assertThat(finalCardDTO1).isNotEqualTo(finalCardDTO2);
    }
}
