package jo.jhr.web.rest;

import jo.jhr.service.FoodSalesReportService;
import jo.jhr.web.rest.errors.BadRequestAlertException;
import jo.jhr.service.dto.FoodSalesReportDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link jo.jhr.domain.FoodSalesReport}.
 */
@RestController
@RequestMapping("/api")
public class FoodSalesReportResource {

    private final Logger log = LoggerFactory.getLogger(FoodSalesReportResource.class);

    private static final String ENTITY_NAME = "foodSalesReport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FoodSalesReportService foodSalesReportService;

    public FoodSalesReportResource(FoodSalesReportService foodSalesReportService) {
        this.foodSalesReportService = foodSalesReportService;
    }

    /**
     * {@code POST  /food-sales-reports} : Create a new foodSalesReport.
     *
     * @param foodSalesReportDTO the foodSalesReportDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new foodSalesReportDTO, or with status {@code 400 (Bad Request)} if the foodSalesReport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/food-sales-reports")
    public ResponseEntity<FoodSalesReportDTO> createFoodSalesReport(@Valid @RequestBody FoodSalesReportDTO foodSalesReportDTO) throws URISyntaxException {
        log.debug("REST request to save FoodSalesReport : {}", foodSalesReportDTO);
        if (foodSalesReportDTO.getId() != null) {
            throw new BadRequestAlertException("A new foodSalesReport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FoodSalesReportDTO result = foodSalesReportService.save(foodSalesReportDTO);
        return ResponseEntity.created(new URI("/api/food-sales-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /food-sales-reports} : Updates an existing foodSalesReport.
     *
     * @param foodSalesReportDTO the foodSalesReportDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated foodSalesReportDTO,
     * or with status {@code 400 (Bad Request)} if the foodSalesReportDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the foodSalesReportDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/food-sales-reports")
    public ResponseEntity<FoodSalesReportDTO> updateFoodSalesReport(@Valid @RequestBody FoodSalesReportDTO foodSalesReportDTO) throws URISyntaxException {
        log.debug("REST request to update FoodSalesReport : {}", foodSalesReportDTO);
        if (foodSalesReportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FoodSalesReportDTO result = foodSalesReportService.save(foodSalesReportDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, foodSalesReportDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /food-sales-reports} : get all the foodSalesReports.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of foodSalesReports in body.
     */
    @GetMapping("/food-sales-reports")
    public ResponseEntity<List<FoodSalesReportDTO>> getAllFoodSalesReports(Pageable pageable) {
        log.debug("REST request to get a page of FoodSalesReports");
        Page<FoodSalesReportDTO> page = foodSalesReportService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /food-sales-reports/:id} : get the "id" foodSalesReport.
     *
     * @param id the id of the foodSalesReportDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the foodSalesReportDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/food-sales-reports/{id}")
    public ResponseEntity<FoodSalesReportDTO> getFoodSalesReport(@PathVariable Long id) {
        log.debug("REST request to get FoodSalesReport : {}", id);
        Optional<FoodSalesReportDTO> foodSalesReportDTO = foodSalesReportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(foodSalesReportDTO);
    }

    /**
     * {@code DELETE  /food-sales-reports/:id} : delete the "id" foodSalesReport.
     *
     * @param id the id of the foodSalesReportDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/food-sales-reports/{id}")
    public ResponseEntity<Void> deleteFoodSalesReport(@PathVariable Long id) {
        log.debug("REST request to delete FoodSalesReport : {}", id);
        foodSalesReportService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
