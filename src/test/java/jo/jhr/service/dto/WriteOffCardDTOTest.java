package jo.jhr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class WriteOffCardDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WriteOffCardDTO.class);
        WriteOffCardDTO writeOffCardDTO1 = new WriteOffCardDTO();
        writeOffCardDTO1.setId(1L);
        WriteOffCardDTO writeOffCardDTO2 = new WriteOffCardDTO();
        assertThat(writeOffCardDTO1).isNotEqualTo(writeOffCardDTO2);
        writeOffCardDTO2.setId(writeOffCardDTO1.getId());
        assertThat(writeOffCardDTO1).isEqualTo(writeOffCardDTO2);
        writeOffCardDTO2.setId(2L);
        assertThat(writeOffCardDTO1).isNotEqualTo(writeOffCardDTO2);
        writeOffCardDTO1.setId(null);
        assertThat(writeOffCardDTO1).isNotEqualTo(writeOffCardDTO2);
    }
}
