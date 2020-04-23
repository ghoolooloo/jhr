package jo.jhr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class CarouselTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Carousel.class);
        Carousel carousel1 = new Carousel();
        carousel1.setId(1L);
        Carousel carousel2 = new Carousel();
        carousel2.setId(carousel1.getId());
        assertThat(carousel1).isEqualTo(carousel2);
        carousel2.setId(2L);
        assertThat(carousel1).isNotEqualTo(carousel2);
        carousel1.setId(null);
        assertThat(carousel1).isNotEqualTo(carousel2);
    }
}
