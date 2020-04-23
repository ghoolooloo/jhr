package jo.jhr.service.mapper;


import jo.jhr.domain.*;
import jo.jhr.service.dto.ShopDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Shop} and its DTO {@link ShopDTO}.
 */
@Mapper(componentModel = "spring", uses = {MerchantMapper.class})
public interface ShopMapper extends EntityMapper<ShopDTO, Shop> {

    @Mapping(source = "merchant.id", target = "merchantId")
    ShopDTO toDto(Shop shop);

    @Mapping(source = "merchantId", target = "merchant")
    Shop toEntity(ShopDTO shopDTO);

    default Shop fromId(Long id) {
        if (id == null) {
            return null;
        }
        Shop shop = new Shop();
        shop.setId(id);
        return shop;
    }
}
