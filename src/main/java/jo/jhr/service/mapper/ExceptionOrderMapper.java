package jo.jhr.service.mapper;


import jo.jhr.domain.*;
import jo.jhr.service.dto.ExceptionOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ExceptionOrder} and its DTO {@link ExceptionOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ExceptionOrderMapper extends EntityMapper<ExceptionOrderDTO, ExceptionOrder> {



    default ExceptionOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        ExceptionOrder exceptionOrder = new ExceptionOrder();
        exceptionOrder.setId(id);
        return exceptionOrder;
    }
}
