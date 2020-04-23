package jo.jhr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CardHolderMapperTest {

    private CardHolderMapper cardHolderMapper;

    @BeforeEach
    public void setUp() {
        cardHolderMapper = new CardHolderMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cardHolderMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cardHolderMapper.fromId(null)).isNull();
    }
}
