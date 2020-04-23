package jo.jhr.service.mapper;


import jo.jhr.domain.*;
import jo.jhr.service.dto.DeliveryAddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DeliveryAddress} and its DTO {@link DeliveryAddressDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DeliveryAddressMapper extends EntityMapper<DeliveryAddressDTO, DeliveryAddress> {



    default DeliveryAddress fromId(Long id) {
        if (id == null) {
            return null;
        }
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setId(id);
        return deliveryAddress;
    }
}
