package jo.jhr.web.rest;

import jo.jhr.service.ExceptionOrderService;
import jo.jhr.web.rest.errors.BadRequestAlertException;
import jo.jhr.service.dto.ExceptionOrderDTO;

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
 * REST controller for managing {@link jo.jhr.domain.ExceptionOrder}.
 */
@RestController
@RequestMapping("/api")
public class ExceptionOrderResource {

    private final Logger log = LoggerFactory.getLogger(ExceptionOrderResource.class);

    private static final String ENTITY_NAME = "exceptionOrder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExceptionOrderService exceptionOrderService;

    public ExceptionOrderResource(ExceptionOrderService exceptionOrderService) {
        this.exceptionOrderService = exceptionOrderService;
    }

    /**
     * {@code POST  /exception-orders} : Create a new exceptionOrder.
     *
     * @param exceptionOrderDTO the exceptionOrderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new exceptionOrderDTO, or with status {@code 400 (Bad Request)} if the exceptionOrder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/exception-orders")
    public ResponseEntity<ExceptionOrderDTO> createExceptionOrder(@Valid @RequestBody ExceptionOrderDTO exceptionOrderDTO) throws URISyntaxException {
        log.debug("REST request to save ExceptionOrder : {}", exceptionOrderDTO);
        if (exceptionOrderDTO.getId() != null) {
            throw new BadRequestAlertException("A new exceptionOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExceptionOrderDTO result = exceptionOrderService.save(exceptionOrderDTO);
        return ResponseEntity.created(new URI("/api/exception-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /exception-orders} : Updates an existing exceptionOrder.
     *
     * @param exceptionOrderDTO the exceptionOrderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated exceptionOrderDTO,
     * or with status {@code 400 (Bad Request)} if the exceptionOrderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the exceptionOrderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/exception-orders")
    public ResponseEntity<ExceptionOrderDTO> updateExceptionOrder(@Valid @RequestBody ExceptionOrderDTO exceptionOrderDTO) throws URISyntaxException {
        log.debug("REST request to update ExceptionOrder : {}", exceptionOrderDTO);
        if (exceptionOrderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExceptionOrderDTO result = exceptionOrderService.save(exceptionOrderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, exceptionOrderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /exception-orders} : get all the exceptionOrders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of exceptionOrders in body.
     */
    @GetMapping("/exception-orders")
    public ResponseEntity<List<ExceptionOrderDTO>> getAllExceptionOrders(Pageable pageable) {
        log.debug("REST request to get a page of ExceptionOrders");
        Page<ExceptionOrderDTO> page = exceptionOrderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /exception-orders/:id} : get the "id" exceptionOrder.
     *
     * @param id the id of the exceptionOrderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the exceptionOrderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/exception-orders/{id}")
    public ResponseEntity<ExceptionOrderDTO> getExceptionOrder(@PathVariable Long id) {
        log.debug("REST request to get ExceptionOrder : {}", id);
        Optional<ExceptionOrderDTO> exceptionOrderDTO = exceptionOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(exceptionOrderDTO);
    }

    /**
     * {@code DELETE  /exception-orders/:id} : delete the "id" exceptionOrder.
     *
     * @param id the id of the exceptionOrderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/exception-orders/{id}")
    public ResponseEntity<Void> deleteExceptionOrder(@PathVariable Long id) {
        log.debug("REST request to delete ExceptionOrder : {}", id);
        exceptionOrderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
