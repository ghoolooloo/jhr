package jo.jhr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class CarouselDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CarouselDTO.class);
        CarouselDTO carouselDTO1 = new CarouselDTO();
        carouselDTO1.setId(1L);
        CarouselDTO carouselDTO2 = new CarouselDTO();
        assertThat(carouselDTO1).isNotEqualTo(carouselDTO2);
        carouselDTO2.setId(carouselDTO1.getId());
        assertThat(carouselDTO1).isEqualTo(carouselDTO2);
        carouselDTO2.setId(2L);
        assertThat(carouselDTO1).isNotEqualTo(carouselDTO2);
        carouselDTO1.setId(null);
        assertThat(carouselDTO1).isNotEqualTo(carouselDTO2);
    }
}
