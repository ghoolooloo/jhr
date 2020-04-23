package jo.jhr.web.rest;

import jo.jhr.service.NoticeService;
import jo.jhr.web.rest.errors.BadRequestAlertException;
import jo.jhr.service.dto.NoticeDTO;

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
 * REST controller for managing {@link jo.jhr.domain.Notice}.
 */
@RestController
@RequestMapping("/api")
public class NoticeResource {

    private final Logger log = LoggerFactory.getLogger(NoticeResource.class);

    private static final String ENTITY_NAME = "notice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NoticeService noticeService;

    public NoticeResource(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    /**
     * {@code POST  /notices} : Create a new notice.
     *
     * @param noticeDTO the noticeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new noticeDTO, or with status {@code 400 (Bad Request)} if the notice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/notices")
    public ResponseEntity<NoticeDTO> createNotice(@Valid @RequestBody NoticeDTO noticeDTO) throws URISyntaxException {
        log.debug("REST request to save Notice : {}", noticeDTO);
        if (noticeDTO.getId() != null) {
            throw new BadRequestAlertException("A new notice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NoticeDTO result = noticeService.save(noticeDTO);
        return ResponseEntity.created(new URI("/api/notices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /notices} : Updates an existing notice.
     *
     * @param noticeDTO the noticeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noticeDTO,
     * or with status {@code 400 (Bad Request)} if the noticeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the noticeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/notices")
    public ResponseEntity<NoticeDTO> updateNotice(@Valid @RequestBody NoticeDTO noticeDTO) throws URISyntaxException {
        log.debug("REST request to update Notice : {}", noticeDTO);
        if (noticeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NoticeDTO result = noticeService.save(noticeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, noticeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /notices} : get all the notices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notices in body.
     */
    @GetMapping("/notices")
    public ResponseEntity<List<NoticeDTO>> getAllNotices(Pageable pageable) {
        log.debug("REST request to get a page of Notices");
        Page<NoticeDTO> page = noticeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /notices/:id} : get the "id" notice.
     *
     * @param id the id of the noticeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the noticeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notices/{id}")
    public ResponseEntity<NoticeDTO> getNotice(@PathVariable Long id) {
        log.debug("REST request to get Notice : {}", id);
        Optional<NoticeDTO> noticeDTO = noticeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(noticeDTO);
    }

    /**
     * {@code DELETE  /notices/:id} : delete the "id" notice.
     *
     * @param id the id of the noticeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/notices/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long id) {
        log.debug("REST request to delete Notice : {}", id);
        noticeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
