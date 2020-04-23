package jo.jhr.web.rest;

import jo.jhr.service.RefundOrderService;
import jo.jhr.web.rest.errors.BadRequestAlertException;
import jo.jhr.service.dto.RefundOrderDTO;

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
 * REST controller for managing {@link jo.jhr.domain.RefundOrder}.
 */
@RestController
@RequestMapping("/api")
public class RefundOrderResource {

    private final Logger log = LoggerFactory.getLogger(RefundOrderResource.class);

    private static final String ENTITY_NAME = "refundOrder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RefundOrderService refundOrderService;

    public RefundOrderResource(RefundOrderService refundOrderService) {
        this.refundOrderService = refundOrderService;
    }

    /**
     * {@code POST  /refund-orders} : Create a new refundOrder.
     *
     * @param refundOrderDTO the refundOrderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new refundOrderDTO, or with status {@code 400 (Bad Request)} if the refundOrder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/refund-orders")
    public ResponseEntity<RefundOrderDTO> createRefundOrder(@Valid @RequestBody RefundOrderDTO refundOrderDTO) throws URISyntaxException {
        log.debug("REST request to save RefundOrder : {}", refundOrderDTO);
        if (refundOrderDTO.getId() != null) {
            throw new BadRequestAlertException("A new refundOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefundOrderDTO result = refundOrderService.save(refundOrderDTO);
        return ResponseEntity.created(new URI("/api/refund-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /refund-orders} : Updates an existing refundOrder.
     *
     * @param refundOrderDTO the refundOrderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refundOrderDTO,
     * or with status {@code 400 (Bad Request)} if the refundOrderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the refundOrderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/refund-orders")
    public ResponseEntity<RefundOrderDTO> updateRefundOrder(@Valid @RequestBody RefundOrderDTO refundOrderDTO) throws URISyntaxException {
        log.debug("REST request to update RefundOrder : {}", refundOrderDTO);
        if (refundOrderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefundOrderDTO result = refundOrderService.save(refundOrderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refundOrderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /refund-orders} : get all the refundOrders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of refundOrders in body.
     */
    @GetMapping("/refund-orders")
    public ResponseEntity<List<RefundOrderDTO>> getAllRefundOrders(Pageable pageable) {
        log.debug("REST request to get a page of RefundOrders");
        Page<RefundOrderDTO> page = refundOrderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /refund-orders/:id} : get the "id" refundOrder.
     *
     * @param id the id of the refundOrderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the refundOrderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/refund-orders/{id}")
    public ResponseEntity<RefundOrderDTO> getRefundOrder(@PathVariable Long id) {
        log.debug("REST request to get RefundOrder : {}", id);
        Optional<RefundOrderDTO> refundOrderDTO = refundOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refundOrderDTO);
    }

    /**
     * {@code DELETE  /refund-orders/:id} : delete the "id" refundOrder.
     *
     * @param id the id of the refundOrderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/refund-orders/{id}")
    public ResponseEntity<Void> deleteRefundOrder(@PathVariable Long id) {
        log.debug("REST request to delete RefundOrder : {}", id);
        refundOrderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
