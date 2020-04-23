package jo.jhr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class CardHolderDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CardHolderDTO.class);
        CardHolderDTO cardHolderDTO1 = new CardHolderDTO();
        cardHolderDTO1.setId(1L);
        CardHolderDTO cardHolderDTO2 = new CardHolderDTO();
        assertThat(cardHolderDTO1).isNotEqualTo(cardHolderDTO2);
        cardHolderDTO2.setId(cardHolderDTO1.getId());
        assertThat(cardHolderDTO1).isEqualTo(cardHolderDTO2);
        cardHolderDTO2.setId(2L);
        assertThat(cardHolderDTO1).isNotEqualTo(cardHolderDTO2);
        cardHolderDTO1.setId(null);
        assertThat(cardHolderDTO1).isNotEqualTo(cardHolderDTO2);
    }
}
