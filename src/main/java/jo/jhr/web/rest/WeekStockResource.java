package jo.jhr.web.rest;

import jo.jhr.service.WeekStockService;
import jo.jhr.web.rest.errors.BadRequestAlertException;
import jo.jhr.service.dto.WeekStockDTO;

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
 * REST controller for managing {@link jo.jhr.domain.WeekStock}.
 */
@RestController
@RequestMapping("/api")
public class WeekStockResource {

    private final Logger log = LoggerFactory.getLogger(WeekStockResource.class);

    private static final String ENTITY_NAME = "weekStock";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WeekStockService weekStockService;

    public WeekStockResource(WeekStockService weekStockService) {
        this.weekStockService = weekStockService;
    }

    /**
     * {@code POST  /week-stocks} : Create a new weekStock.
     *
     * @param weekStockDTO the weekStockDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new weekStockDTO, or with status {@code 400 (Bad Request)} if the weekStock has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/week-stocks")
    public ResponseEntity<WeekStockDTO> createWeekStock(@Valid @RequestBody WeekStockDTO weekStockDTO) throws URISyntaxException {
        log.debug("REST request to save WeekStock : {}", weekStockDTO);
        if (weekStockDTO.getId() != null) {
            throw new BadRequestAlertException("A new weekStock cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WeekStockDTO result = weekStockService.save(weekStockDTO);
        return ResponseEntity.created(new URI("/api/week-stocks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /week-stocks} : Updates an existing weekStock.
     *
     * @param weekStockDTO the weekStockDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated weekStockDTO,
     * or with status {@code 400 (Bad Request)} if the weekStockDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the weekStockDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/week-stocks")
    public ResponseEntity<WeekStockDTO> updateWeekStock(@Valid @RequestBody WeekStockDTO weekStockDTO) throws URISyntaxException {
        log.debug("REST request to update WeekStock : {}", weekStockDTO);
        if (weekStockDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WeekStockDTO result = weekStockService.save(weekStockDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, weekStockDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /week-stocks} : get all the weekStocks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of weekStocks in body.
     */
    @GetMapping("/week-stocks")
    public List<WeekStockDTO> getAllWeekStocks() {
        log.debug("REST request to get all WeekStocks");
        return weekStockService.findAll();
    }

    /**
     * {@code GET  /week-stocks/:id} : get the "id" weekStock.
     *
     * @param id the id of the weekStockDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the weekStockDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/week-stocks/{id}")
    public ResponseEntity<WeekStockDTO> getWeekStock(@PathVariable Long id) {
        log.debug("REST request to get WeekStock : {}", id);
        Optional<WeekStockDTO> weekStockDTO = weekStockService.findOne(id);
        return ResponseUtil.wrapOrNotFound(weekStockDTO);
    }

    /**
     * {@code DELETE  /week-stocks/:id} : delete the "id" weekStock.
     *
     * @param id the id of the weekStockDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/week-stocks/{id}")
    public ResponseEntity<Void> deleteWeekStock(@PathVariable Long id) {
        log.debug("REST request to delete WeekStock : {}", id);
        weekStockService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
