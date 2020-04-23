package jo.jhr.service.mapper;


import jo.jhr.domain.*;
import jo.jhr.service.dto.FoodCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FoodCategory} and its DTO {@link FoodCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FoodCategoryMapper extends EntityMapper<FoodCategoryDTO, FoodCategory> {



    default FoodCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        FoodCategory foodCategory = new FoodCategory();
        foodCategory.setId(id);
        return foodCategory;
    }
}
