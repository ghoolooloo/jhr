package jo.jhr.service.mapper;


import jo.jhr.domain.*;
import jo.jhr.service.dto.RefundOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RefundOrder} and its DTO {@link RefundOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RefundOrderMapper extends EntityMapper<RefundOrderDTO, RefundOrder> {



    default RefundOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefundOrder refundOrder = new RefundOrder();
        refundOrder.setId(id);
        return refundOrder;
    }
}
