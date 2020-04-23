package jo.jhr.service.mapper;


import jo.jhr.domain.*;
import jo.jhr.service.dto.SalesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Sales} and its DTO {@link SalesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SalesMapper extends EntityMapper<SalesDTO, Sales> {



    default Sales fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sales sales = new Sales();
        sales.setId(id);
        return sales;
    }
}
