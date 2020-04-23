package jo.jhr.service.mapper;


import jo.jhr.domain.*;
import jo.jhr.service.dto.CarouselDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Carousel} and its DTO {@link CarouselDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CarouselMapper extends EntityMapper<CarouselDTO, Carousel> {



    default Carousel fromId(Long id) {
        if (id == null) {
            return null;
        }
        Carousel carousel = new Carousel();
        carousel.setId(id);
        return carousel;
    }
}
