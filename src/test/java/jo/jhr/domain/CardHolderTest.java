package jo.jhr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class CardHolderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CardHolder.class);
        CardHolder cardHolder1 = new CardHolder();
        cardHolder1.setId(1L);
        CardHolder cardHolder2 = new CardHolder();
        cardHolder2.setId(cardHolder1.getId());
        assertThat(cardHolder1).isEqualTo(cardHolder2);
        cardHolder2.setId(2L);
        assertThat(cardHolder1).isNotEqualTo(cardHolder2);
        cardHolder1.setId(null);
        assertThat(cardHolder1).isNotEqualTo(cardHolder2);
    }
}
