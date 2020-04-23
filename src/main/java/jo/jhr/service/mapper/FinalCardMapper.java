package jo.jhr.service.mapper;


import jo.jhr.domain.*;
import jo.jhr.service.dto.FinalCardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FinalCard} and its DTO {@link FinalCardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FinalCardMapper extends EntityMapper<FinalCardDTO, FinalCard> {



    default FinalCard fromId(Long id) {
        if (id == null) {
            return null;
        }
        FinalCard finalCard = new FinalCard();
        finalCard.setId(id);
        return finalCard;
    }
}
