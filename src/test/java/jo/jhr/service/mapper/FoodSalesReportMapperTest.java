package jo.jhr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FoodSalesReportMapperTest {

    private FoodSalesReportMapper foodSalesReportMapper;

    @BeforeEach
    public void setUp() {
        foodSalesReportMapper = new FoodSalesReportMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(foodSalesReportMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(foodSalesReportMapper.fromId(null)).isNull();
    }
}
