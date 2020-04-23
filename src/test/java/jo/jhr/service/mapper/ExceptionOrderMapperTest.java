package jo.jhr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ExceptionOrderMapperTest {

    private ExceptionOrderMapper exceptionOrderMapper;

    @BeforeEach
    public void setUp() {
        exceptionOrderMapper = new ExceptionOrderMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(exceptionOrderMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(exceptionOrderMapper.fromId(null)).isNull();
    }
}
