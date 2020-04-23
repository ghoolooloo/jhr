package jo.jhr.service.mapper;


import jo.jhr.domain.*;
import jo.jhr.service.dto.FoodSalesReportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FoodSalesReport} and its DTO {@link FoodSalesReportDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FoodSalesReportMapper extends EntityMapper<FoodSalesReportDTO, FoodSalesReport> {



    default FoodSalesReport fromId(Long id) {
        if (id == null) {
            return null;
        }
        FoodSalesReport foodSalesReport = new FoodSalesReport();
        foodSalesReport.setId(id);
        return foodSalesReport;
    }
}
