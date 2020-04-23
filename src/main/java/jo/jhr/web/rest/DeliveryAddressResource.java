package jo.jhr.web.rest;

import jo.jhr.service.DeliveryAddressService;
import jo.jhr.web.rest.errors.BadRequestAlertException;
import jo.jhr.service.dto.DeliveryAddressDTO;

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
 * REST controller for managing {@link jo.jhr.domain.DeliveryAddress}.
 */
@RestController
@RequestMapping("/api")
public class DeliveryAddressResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryAddressResource.class);

    private static final String ENTITY_NAME = "deliveryAddress";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeliveryAddressService deliveryAddressService;

    public DeliveryAddressResource(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

    /**
     * {@code POST  /delivery-addresses} : Create a new deliveryAddress.
     *
     * @param deliveryAddressDTO the deliveryAddressDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deliveryAddressDTO, or with status {@code 400 (Bad Request)} if the deliveryAddress has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/delivery-addresses")
    public ResponseEntity<DeliveryAddressDTO> createDeliveryAddress(@Valid @RequestBody DeliveryAddressDTO deliveryAddressDTO) throws URISyntaxException {
        log.debug("REST request to save DeliveryAddress : {}", deliveryAddressDTO);
        if (deliveryAddressDTO.getId() != null) {
            throw new BadRequestAlertException("A new deliveryAddress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeliveryAddressDTO result = deliveryAddressService.save(deliveryAddressDTO);
        return ResponseEntity.created(new URI("/api/delivery-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /delivery-addresses} : Updates an existing deliveryAddress.
     *
     * @param deliveryAddressDTO the deliveryAddressDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryAddressDTO,
     * or with status {@code 400 (Bad Request)} if the deliveryAddressDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deliveryAddressDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/delivery-addresses")
    public ResponseEntity<DeliveryAddressDTO> updateDeliveryAddress(@Valid @RequestBody DeliveryAddressDTO deliveryAddressDTO) throws URISyntaxException {
        log.debug("REST request to update DeliveryAddress : {}", deliveryAddressDTO);
        if (deliveryAddressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeliveryAddressDTO result = deliveryAddressService.save(deliveryAddressDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deliveryAddressDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /delivery-addresses} : get all the deliveryAddresses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deliveryAddresses in body.
     */
    @GetMapping("/delivery-addresses")
    public List<DeliveryAddressDTO> getAllDeliveryAddresses() {
        log.debug("REST request to get all DeliveryAddresses");
        return deliveryAddressService.findAll();
    }

    /**
     * {@code GET  /delivery-addresses/:id} : get the "id" deliveryAddress.
     *
     * @param id the id of the deliveryAddressDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deliveryAddressDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/delivery-addresses/{id}")
    public ResponseEntity<DeliveryAddressDTO> getDeliveryAddress(@PathVariable Long id) {
        log.debug("REST request to get DeliveryAddress : {}", id);
        Optional<DeliveryAddressDTO> deliveryAddressDTO = deliveryAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliveryAddressDTO);
    }

    /**
     * {@code DELETE  /delivery-addresses/:id} : delete the "id" deliveryAddress.
     *
     * @param id the id of the deliveryAddressDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/delivery-addresses/{id}")
    public ResponseEntity<Void> deleteDeliveryAddress(@PathVariable Long id) {
        log.debug("REST request to delete DeliveryAddress : {}", id);
        deliveryAddressService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
