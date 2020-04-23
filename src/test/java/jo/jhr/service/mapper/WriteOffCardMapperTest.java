package jo.jhr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WriteOffCardMapperTest {

    private WriteOffCardMapper writeOffCardMapper;

    @BeforeEach
    public void setUp() {
        writeOffCardMapper = new WriteOffCardMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(writeOffCardMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(writeOffCardMapper.fromId(null)).isNull();
    }
}
