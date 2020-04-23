package jo.jhr.service.mapper;


import jo.jhr.domain.*;
import jo.jhr.service.dto.DeliveryOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DeliveryOrder} and its DTO {@link DeliveryOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DeliveryOrderMapper extends EntityMapper<DeliveryOrderDTO, DeliveryOrder> {



    default DeliveryOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setId(id);
        return deliveryOrder;
    }
}
