package jo.jhr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class FoodSalesReportTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FoodSalesReport.class);
        FoodSalesReport foodSalesReport1 = new FoodSalesReport();
        foodSalesReport1.setId(1L);
        FoodSalesReport foodSalesReport2 = new FoodSalesReport();
        foodSalesReport2.setId(foodSalesReport1.getId());
        assertThat(foodSalesReport1).isEqualTo(foodSalesReport2);
        foodSalesReport2.setId(2L);
        assertThat(foodSalesReport1).isNotEqualTo(foodSalesReport2);
        foodSalesReport1.setId(null);
        assertThat(foodSalesReport1).isNotEqualTo(foodSalesReport2);
    }
}
