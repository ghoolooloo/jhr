package jo.jhr.web.rest;

import jo.jhr.service.ClosedOrderService;
import jo.jhr.web.rest.errors.BadRequestAlertException;
import jo.jhr.service.dto.ClosedOrderDTO;

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
 * REST controller for managing {@link jo.jhr.domain.ClosedOrder}.
 */
@RestController
@RequestMapping("/api")
public class ClosedOrderResource {

    private final Logger log = LoggerFactory.getLogger(ClosedOrderResource.class);

    private static final String ENTITY_NAME = "closedOrder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClosedOrderService closedOrderService;

    public ClosedOrderResource(ClosedOrderService closedOrderService) {
        this.closedOrderService = closedOrderService;
    }

    /**
     * {@code POST  /closed-orders} : Create a new closedOrder.
     *
     * @param closedOrderDTO the closedOrderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new closedOrderDTO, or with status {@code 400 (Bad Request)} if the closedOrder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/closed-orders")
    public ResponseEntity<ClosedOrderDTO> createClosedOrder(@Valid @RequestBody ClosedOrderDTO closedOrderDTO) throws URISyntaxException {
        log.debug("REST request to save ClosedOrder : {}", closedOrderDTO);
        if (closedOrderDTO.getId() != null) {
            throw new BadRequestAlertException("A new closedOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClosedOrderDTO result = closedOrderService.save(closedOrderDTO);
        return ResponseEntity.created(new URI("/api/closed-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /closed-orders} : Updates an existing closedOrder.
     *
     * @param closedOrderDTO the closedOrderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated closedOrderDTO,
     * or with status {@code 400 (Bad Request)} if the closedOrderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the closedOrderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/closed-orders")
    public ResponseEntity<ClosedOrderDTO> updateClosedOrder(@Valid @RequestBody ClosedOrderDTO closedOrderDTO) throws URISyntaxException {
        log.debug("REST request to update ClosedOrder : {}", closedOrderDTO);
        if (closedOrderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClosedOrderDTO result = closedOrderService.save(closedOrderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, closedOrderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /closed-orders} : get all the closedOrders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of closedOrders in body.
     */
    @GetMapping("/closed-orders")
    public ResponseEntity<List<ClosedOrderDTO>> getAllClosedOrders(Pageable pageable) {
        log.debug("REST request to get a page of ClosedOrders");
        Page<ClosedOrderDTO> page = closedOrderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /closed-orders/:id} : get the "id" closedOrder.
     *
     * @param id the id of the closedOrderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the closedOrderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/closed-orders/{id}")
    public ResponseEntity<ClosedOrderDTO> getClosedOrder(@PathVariable Long id) {
        log.debug("REST request to get ClosedOrder : {}", id);
        Optional<ClosedOrderDTO> closedOrderDTO = closedOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(closedOrderDTO);
    }

    /**
     * {@code DELETE  /closed-orders/:id} : delete the "id" closedOrder.
     *
     * @param id the id of the closedOrderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/closed-orders/{id}")
    public ResponseEntity<Void> deleteClosedOrder(@PathVariable Long id) {
        log.debug("REST request to delete ClosedOrder : {}", id);
        closedOrderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
