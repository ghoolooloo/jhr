package jo.jhr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SalesMapperTest {

    private SalesMapper salesMapper;

    @BeforeEach
    public void setUp() {
        salesMapper = new SalesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(salesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(salesMapper.fromId(null)).isNull();
    }
}
