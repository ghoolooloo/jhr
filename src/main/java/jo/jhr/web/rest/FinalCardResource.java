package jo.jhr.web.rest;

import jo.jhr.service.FinalCardService;
import jo.jhr.web.rest.errors.BadRequestAlertException;
import jo.jhr.service.dto.FinalCardDTO;

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
 * REST controller for managing {@link jo.jhr.domain.FinalCard}.
 */
@RestController
@RequestMapping("/api")
public class FinalCardResource {

    private final Logger log = LoggerFactory.getLogger(FinalCardResource.class);

    private static final String ENTITY_NAME = "finalCard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FinalCardService finalCardService;

    public FinalCardResource(FinalCardService finalCardService) {
        this.finalCardService = finalCardService;
    }

    /**
     * {@code POST  /final-cards} : Create a new finalCard.
     *
     * @param finalCardDTO the finalCardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new finalCardDTO, or with status {@code 400 (Bad Request)} if the finalCard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/final-cards")
    public ResponseEntity<FinalCardDTO> createFinalCard(@Valid @RequestBody FinalCardDTO finalCardDTO) throws URISyntaxException {
        log.debug("REST request to save FinalCard : {}", finalCardDTO);
        if (finalCardDTO.getId() != null) {
            throw new BadRequestAlertException("A new finalCard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FinalCardDTO result = finalCardService.save(finalCardDTO);
        return ResponseEntity.created(new URI("/api/final-cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /final-cards} : Updates an existing finalCard.
     *
     * @param finalCardDTO the finalCardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated finalCardDTO,
     * or with status {@code 400 (Bad Request)} if the finalCardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the finalCardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/final-cards")
    public ResponseEntity<FinalCardDTO> updateFinalCard(@Valid @RequestBody FinalCardDTO finalCardDTO) throws URISyntaxException {
        log.debug("REST request to update FinalCard : {}", finalCardDTO);
        if (finalCardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FinalCardDTO result = finalCardService.save(finalCardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, finalCardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /final-cards} : get all the finalCards.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of finalCards in body.
     */
    @GetMapping("/final-cards")
    public ResponseEntity<List<FinalCardDTO>> getAllFinalCards(Pageable pageable) {
        log.debug("REST request to get a page of FinalCards");
        Page<FinalCardDTO> page = finalCardService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /final-cards/:id} : get the "id" finalCard.
     *
     * @param id the id of the finalCardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the finalCardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/final-cards/{id}")
    public ResponseEntity<FinalCardDTO> getFinalCard(@PathVariable Long id) {
        log.debug("REST request to get FinalCard : {}", id);
        Optional<FinalCardDTO> finalCardDTO = finalCardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(finalCardDTO);
    }

    /**
     * {@code DELETE  /final-cards/:id} : delete the "id" finalCard.
     *
     * @param id the id of the finalCardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/final-cards/{id}")
    public ResponseEntity<Void> deleteFinalCard(@PathVariable Long id) {
        log.debug("REST request to delete FinalCard : {}", id);
        finalCardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
