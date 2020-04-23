package jo.jhr.service.mapper;


import jo.jhr.domain.*;
import jo.jhr.service.dto.WriteOffCardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WriteOffCard} and its DTO {@link WriteOffCardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WriteOffCardMapper extends EntityMapper<WriteOffCardDTO, WriteOffCard> {



    default WriteOffCard fromId(Long id) {
        if (id == null) {
            return null;
        }
        WriteOffCard writeOffCard = new WriteOffCard();
        writeOffCard.setId(id);
        return writeOffCard;
    }
}
