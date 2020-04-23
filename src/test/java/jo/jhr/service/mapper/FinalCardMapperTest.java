package jo.jhr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FinalCardMapperTest {

    private FinalCardMapper finalCardMapper;

    @BeforeEach
    public void setUp() {
        finalCardMapper = new FinalCardMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(finalCardMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(finalCardMapper.fromId(null)).isNull();
    }
}
