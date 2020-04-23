package jo.jhr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class FinalCardTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinalCard.class);
        FinalCard finalCard1 = new FinalCard();
        finalCard1.setId(1L);
        FinalCard finalCard2 = new FinalCard();
        finalCard2.setId(finalCard1.getId());
        assertThat(finalCard1).isEqualTo(finalCard2);
        finalCard2.setId(2L);
        assertThat(finalCard1).isNotEqualTo(finalCard2);
        finalCard1.setId(null);
        assertThat(finalCard1).isNotEqualTo(finalCard2);
    }
}
