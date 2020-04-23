package jo.jhr.service.mapper;


import jo.jhr.domain.*;
import jo.jhr.service.dto.CardHolderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CardHolder} and its DTO {@link CardHolderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CardHolderMapper extends EntityMapper<CardHolderDTO, CardHolder> {



    default CardHolder fromId(Long id) {
        if (id == null) {
            return null;
        }
        CardHolder cardHolder = new CardHolder();
        cardHolder.setId(id);
        return cardHolder;
    }
}
