package jo.jhr.service;

import jo.jhr.domain.Carousel;
import jo.jhr.repository.CarouselRepository;
import jo.jhr.service.dto.CarouselDTO;
import jo.jhr.service.mapper.CarouselMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Carousel}.
 */
@Service
@Transactional
public class CarouselService {

    private final Logger log = LoggerFactory.getLogger(CarouselService.class);

    private final CarouselRepository carouselRepository;

    private final CarouselMapper carouselMapper;

    public CarouselService(CarouselRepository carouselRepository, CarouselMapper carouselMapper) {
        this.carouselRepository = carouselRepository;
        this.carouselMapper = carouselMapper;
    }

    /**
     * Save a carousel.
     *
     * @param carouselDTO the entity to save.
     * @return the persisted entity.
     */
    public CarouselDTO save(CarouselDTO carouselDTO) {
        log.debug("Request to save Carousel : {}", carouselDTO);
        Carousel carousel = carouselMapper.toEntity(carouselDTO);
        carousel = carouselRepository.save(carousel);
        return carouselMapper.toDto(carousel);
    }

    /**
     * Get all the carousels.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CarouselDTO> findAll() {
        log.debug("Request to get all Carousels");
        return carouselRepository.findAll().stream()
            .map(carouselMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one carousel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CarouselDTO> findOne(Long id) {
        log.debug("Request to get Carousel : {}", id);
        return carouselRepository.findById(id)
            .map(carouselMapper::toDto);
    }

    /**
     * Delete the carousel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Carousel : {}", id);
        carouselRepository.deleteById(id);
    }
}
