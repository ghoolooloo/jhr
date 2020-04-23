package jo.jhr.repository;

import jo.jhr.domain.Carousel;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Carousel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarouselRepository extends JpaRepository<Carousel, Long> {
}
