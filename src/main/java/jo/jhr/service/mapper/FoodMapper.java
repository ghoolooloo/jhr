package jo.jhr.service.mapper;


import jo.jhr.domain.*;
import jo.jhr.service.dto.FoodDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Food} and its DTO {@link FoodDTO}.
 */
@Mapper(componentModel = "spring", uses = {FoodCategoryMapper.class})
public interface FoodMapper extends EntityMapper<FoodDTO, Food> {

    @Mapping(source = "category.id", target = "categoryId")
    FoodDTO toDto(Food food);

    @Mapping(source = "categoryId", target = "category")
    Food toEntity(FoodDTO foodDTO);

    default Food fromId(Long id) {
        if (id == null) {
            return null;
        }
        Food food = new Food();
        food.setId(id);
        return food;
    }
}
