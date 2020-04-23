package jo.jhr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import jo.jhr.web.rest.TestUtil;

public class FoodSalesReportDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FoodSalesReportDTO.class);
        FoodSalesReportDTO foodSalesReportDTO1 = new FoodSalesReportDTO();
        foodSalesReportDTO1.setId(1L);
        FoodSalesReportDTO foodSalesReportDTO2 = new FoodSalesReportDTO();
        assertThat(foodSalesReportDTO1).isNotEqualTo(foodSalesReportDTO2);
        foodSalesReportDTO2.setId(foodSalesReportDTO1.getId());
        assertThat(foodSalesReportDTO1).isEqualTo(foodSalesReportDTO2);
        foodSalesReportDTO2.setId(2L);
        assertThat(foodSalesReportDTO1).isNotEqualTo(foodSalesReportDTO2);
        foodSalesReportDTO1.setId(null);
        assertThat(foodSalesReportDTO1).isNotEqualTo(foodSalesReportDTO2);
    }
}
