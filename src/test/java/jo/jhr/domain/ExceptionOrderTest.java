package jo.jhr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class ExceptionOrderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExceptionOrder.class);
        ExceptionOrder exceptionOrder1 = new ExceptionOrder();
        exceptionOrder1.setId(1L);
        ExceptionOrder exceptionOrder2 = new ExceptionOrder();
        exceptionOrder2.setId(exceptionOrder1.getId());
        assertThat(exceptionOrder1).isEqualTo(exceptionOrder2);
        exceptionOrder2.setId(2L);
        assertThat(exceptionOrder1).isNotEqualTo(exceptionOrder2);
        exceptionOrder1.setId(null);
        assertThat(exceptionOrder1).isNotEqualTo(exceptionOrder2);
    }
}
