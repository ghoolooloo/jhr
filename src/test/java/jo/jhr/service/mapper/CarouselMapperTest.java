package jo.jhr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CarouselMapperTest {

    private CarouselMapper carouselMapper;

    @BeforeEach
    public void setUp() {
        carouselMapper = new CarouselMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(carouselMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(carouselMapper.fromId(null)).isNull();
    }
}
