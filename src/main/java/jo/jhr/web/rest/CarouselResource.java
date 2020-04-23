package jo.jhr.web.rest;

import jo.jhr.service.CarouselService;
import jo.jhr.web.rest.errors.BadRequestAlertException;
import jo.jhr.service.dto.CarouselDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link jo.jhr.domain.Carousel}.
 */
@RestController
@RequestMapping("/api")
public class CarouselResource {

    private final Logger log = LoggerFactory.getLogger(CarouselResource.class);

    private static final String ENTITY_NAME = "carousel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CarouselService carouselService;

    public CarouselResource(CarouselService carouselService) {
        this.carouselService = carouselService;
    }

    /**
     * {@code POST  /carousels} : Create a new carousel.
     *
     * @param carouselDTO the carouselDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new carouselDTO, or with status {@code 400 (Bad Request)} if the carousel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/carousels")
    public ResponseEntity<CarouselDTO> createCarousel(@Valid @RequestBody CarouselDTO carouselDTO) throws URISyntaxException {
        log.debug("REST request to save Carousel : {}", carouselDTO);
        if (carouselDTO.getId() != null) {
            throw new BadRequestAlertException("A new carousel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CarouselDTO result = carouselService.save(carouselDTO);
        return ResponseEntity.created(new URI("/api/carousels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /carousels} : Updates an existing carousel.
     *
     * @param carouselDTO the carouselDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carouselDTO,
     * or with status {@code 400 (Bad Request)} if the carouselDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the carouselDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/carousels")
    public ResponseEntity<CarouselDTO> updateCarousel(@Valid @RequestBody CarouselDTO carouselDTO) throws URISyntaxException {
        log.debug("REST request to update Carousel : {}", carouselDTO);
        if (carouselDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CarouselDTO result = carouselService.save(carouselDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, carouselDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /carousels} : get all the carousels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of carousels in body.
     */
    @GetMapping("/carousels")
    public List<CarouselDTO> getAllCarousels() {
        log.debug("REST request to get all Carousels");
        return carouselService.findAll();
    }

    /**
     * {@code GET  /carousels/:id} : get the "id" carousel.
     *
     * @param id the id of the carouselDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the carouselDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/carousels/{id}")
    public ResponseEntity<CarouselDTO> getCarousel(@PathVariable Long id) {
        log.debug("REST request to get Carousel : {}", id);
        Optional<CarouselDTO> carouselDTO = carouselService.findOne(id);
        return ResponseUtil.wrapOrNotFound(carouselDTO);
    }

    /**
     * {@code DELETE  /carousels/:id} : delete the "id" carousel.
     *
     * @param id the id of the carouselDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/carousels/{id}")
    public ResponseEntity<Void> deleteCarousel(@PathVariable Long id) {
        log.debug("REST request to delete Carousel : {}", id);
        carouselService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
