package jo.jhr.web.rest;

import jo.jhr.service.CardHolderService;
import jo.jhr.web.rest.errors.BadRequestAlertException;
import jo.jhr.service.dto.CardHolderDTO;

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
 * REST controller for managing {@link jo.jhr.domain.CardHolder}.
 */
@RestController
@RequestMapping("/api")
public class CardHolderResource {

    private final Logger log = LoggerFactory.getLogger(CardHolderResource.class);

    private static final String ENTITY_NAME = "cardHolder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CardHolderService cardHolderService;

    public CardHolderResource(CardHolderService cardHolderService) {
        this.cardHolderService = cardHolderService;
    }

    /**
     * {@code POST  /card-holders} : Create a new cardHolder.
     *
     * @param cardHolderDTO the cardHolderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cardHolderDTO, or with status {@code 400 (Bad Request)} if the cardHolder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/card-holders")
    public ResponseEntity<CardHolderDTO> createCardHolder(@Valid @RequestBody CardHolderDTO cardHolderDTO) throws URISyntaxException {
        log.debug("REST request to save CardHolder : {}", cardHolderDTO);
        if (cardHolderDTO.getId() != null) {
            throw new BadRequestAlertException("A new cardHolder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CardHolderDTO result = cardHolderService.save(cardHolderDTO);
        return ResponseEntity.created(new URI("/api/card-holders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /card-holders} : Updates an existing cardHolder.
     *
     * @param cardHolderDTO the cardHolderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cardHolderDTO,
     * or with status {@code 400 (Bad Request)} if the cardHolderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cardHolderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/card-holders")
    public ResponseEntity<CardHolderDTO> updateCardHolder(@Valid @RequestBody CardHolderDTO cardHolderDTO) throws URISyntaxException {
        log.debug("REST request to update CardHolder : {}", cardHolderDTO);
        if (cardHolderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CardHolderDTO result = cardHolderService.save(cardHolderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cardHolderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /card-holders} : get all the cardHolders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cardHolders in body.
     */
    @GetMapping("/card-holders")
    public ResponseEntity<List<CardHolderDTO>> getAllCardHolders(Pageable pageable) {
        log.debug("REST request to get a page of CardHolders");
        Page<CardHolderDTO> page = cardHolderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /card-holders/:id} : get the "id" cardHolder.
     *
     * @param id the id of the cardHolderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cardHolderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/card-holders/{id}")
    public ResponseEntity<CardHolderDTO> getCardHolder(@PathVariable Long id) {
        log.debug("REST request to get CardHolder : {}", id);
        Optional<CardHolderDTO> cardHolderDTO = cardHolderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cardHolderDTO);
    }

    /**
     * {@code DELETE  /card-holders/:id} : delete the "id" cardHolder.
     *
     * @param id the id of the cardHolderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/card-holders/{id}")
    public ResponseEntity<Void> deleteCardHolder(@PathVariable Long id) {
        log.debug("REST request to delete CardHolder : {}", id);
        cardHolderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
