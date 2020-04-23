package jo.jhr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class WriteOffCardTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WriteOffCard.class);
        WriteOffCard writeOffCard1 = new WriteOffCard();
        writeOffCard1.setId(1L);
        WriteOffCard writeOffCard2 = new WriteOffCard();
        writeOffCard2.setId(writeOffCard1.getId());
        assertThat(writeOffCard1).isEqualTo(writeOffCard2);
        writeOffCard2.setId(2L);
        assertThat(writeOffCard1).isNotEqualTo(writeOffCard2);
        writeOffCard1.setId(null);
        assertThat(writeOffCard1).isNotEqualTo(writeOffCard2);
    }
}
