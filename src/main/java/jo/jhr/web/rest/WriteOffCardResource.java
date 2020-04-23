package jo.jhr.web.rest;

import jo.jhr.service.WriteOffCardService;
import jo.jhr.web.rest.errors.BadRequestAlertException;
import jo.jhr.service.dto.WriteOffCardDTO;

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
 * REST controller for managing {@link jo.jhr.domain.WriteOffCard}.
 */
@RestController
@RequestMapping("/api")
public class WriteOffCardResource {

    private final Logger log = LoggerFactory.getLogger(WriteOffCardResource.class);

    private static final String ENTITY_NAME = "writeOffCard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WriteOffCardService writeOffCardService;

    public WriteOffCardResource(WriteOffCardService writeOffCardService) {
        this.writeOffCardService = writeOffCardService;
    }

    /**
     * {@code POST  /write-off-cards} : Create a new writeOffCard.
     *
     * @param writeOffCardDTO the writeOffCardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new writeOffCardDTO, or with status {@code 400 (Bad Request)} if the writeOffCard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/write-off-cards")
    public ResponseEntity<WriteOffCardDTO> createWriteOffCard(@Valid @RequestBody WriteOffCardDTO writeOffCardDTO) throws URISyntaxException {
        log.debug("REST request to save WriteOffCard : {}", writeOffCardDTO);
        if (writeOffCardDTO.getId() != null) {
            throw new BadRequestAlertException("A new writeOffCard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WriteOffCardDTO result = writeOffCardService.save(writeOffCardDTO);
        return ResponseEntity.created(new URI("/api/write-off-cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /write-off-cards} : Updates an existing writeOffCard.
     *
     * @param writeOffCardDTO the writeOffCardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated writeOffCardDTO,
     * or with status {@code 400 (Bad Request)} if the writeOffCardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the writeOffCardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/write-off-cards")
    public ResponseEntity<WriteOffCardDTO> updateWriteOffCard(@Valid @RequestBody WriteOffCardDTO writeOffCardDTO) throws URISyntaxException {
        log.debug("REST request to update WriteOffCard : {}", writeOffCardDTO);
        if (writeOffCardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WriteOffCardDTO result = writeOffCardService.save(writeOffCardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, writeOffCardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /write-off-cards} : get all the writeOffCards.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of writeOffCards in body.
     */
    @GetMapping("/write-off-cards")
    public ResponseEntity<List<WriteOffCardDTO>> getAllWriteOffCards(Pageable pageable) {
        log.debug("REST request to get a page of WriteOffCards");
        Page<WriteOffCardDTO> page = writeOffCardService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /write-off-cards/:id} : get the "id" writeOffCard.
     *
     * @param id the id of the writeOffCardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the writeOffCardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/write-off-cards/{id}")
    public ResponseEntity<WriteOffCardDTO> getWriteOffCard(@PathVariable Long id) {
        log.debug("REST request to get WriteOffCard : {}", id);
        Optional<WriteOffCardDTO> writeOffCardDTO = writeOffCardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(writeOffCardDTO);
    }

    /**
     * {@code DELETE  /write-off-cards/:id} : delete the "id" writeOffCard.
     *
     * @param id the id of the writeOffCardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/write-off-cards/{id}")
    public ResponseEntity<Void> deleteWriteOffCard(@PathVariable Long id) {
        log.debug("REST request to delete WriteOffCard : {}", id);
        writeOffCardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
