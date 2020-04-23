package jo.jhr.service.mapper;


import jo.jhr.domain.*;
import jo.jhr.service.dto.ClosedOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClosedOrder} and its DTO {@link ClosedOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClosedOrderMapper extends EntityMapper<ClosedOrderDTO, ClosedOrder> {



    default ClosedOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClosedOrder closedOrder = new ClosedOrder();
        closedOrder.setId(id);
        return closedOrder;
    }
}
