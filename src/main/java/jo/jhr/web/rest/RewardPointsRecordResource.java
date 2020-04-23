package jo.jhr.web.rest;

import jo.jhr.service.RewardPointsRecordService;
import jo.jhr.web.rest.errors.BadRequestAlertException;
import jo.jhr.service.dto.RewardPointsRecordDTO;

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
 * REST controller for managing {@link jo.jhr.domain.RewardPointsRecord}.
 */
@RestController
@RequestMapping("/api")
public class RewardPointsRecordResource {

    private final Logger log = LoggerFactory.getLogger(RewardPointsRecordResource.class);

    private static final String ENTITY_NAME = "rewardPointsRecord";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RewardPointsRecordService rewardPointsRecordService;

    public RewardPointsRecordResource(RewardPointsRecordService rewardPointsRecordService) {
        this.rewardPointsRecordService = rewardPointsRecordService;
    }

    /**
     * {@code POST  /reward-points-records} : Create a new rewardPointsRecord.
     *
     * @param rewardPointsRecordDTO the rewardPointsRecordDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rewardPointsRecordDTO, or with status {@code 400 (Bad Request)} if the rewardPointsRecord has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reward-points-records")
    public ResponseEntity<RewardPointsRecordDTO> createRewardPointsRecord(@Valid @RequestBody RewardPointsRecordDTO rewardPointsRecordDTO) throws URISyntaxException {
        log.debug("REST request to save RewardPointsRecord : {}", rewardPointsRecordDTO);
        if (rewardPointsRecordDTO.getId() != null) {
            throw new BadRequestAlertException("A new rewardPointsRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RewardPointsRecordDTO result = rewardPointsRecordService.save(rewardPointsRecordDTO);
        return ResponseEntity.created(new URI("/api/reward-points-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reward-points-records} : Updates an existing rewardPointsRecord.
     *
     * @param rewardPointsRecordDTO the rewardPointsRecordDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rewardPointsRecordDTO,
     * or with status {@code 400 (Bad Request)} if the rewardPointsRecordDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rewardPointsRecordDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reward-points-records")
    public ResponseEntity<RewardPointsRecordDTO> updateRewardPointsRecord(@Valid @RequestBody RewardPointsRecordDTO rewardPointsRecordDTO) throws URISyntaxException {
        log.debug("REST request to update RewardPointsRecord : {}", rewardPointsRecordDTO);
        if (rewardPointsRecordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RewardPointsRecordDTO result = rewardPointsRecordService.save(rewardPointsRecordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rewardPointsRecordDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /reward-points-records} : get all the rewardPointsRecords.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rewardPointsRecords in body.
     */
    @GetMapping("/reward-points-records")
    public ResponseEntity<List<RewardPointsRecordDTO>> getAllRewardPointsRecords(Pageable pageable) {
        log.debug("REST request to get a page of RewardPointsRecords");
        Page<RewardPointsRecordDTO> page = rewardPointsRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /reward-points-records/:id} : get the "id" rewardPointsRecord.
     *
     * @param id the id of the rewardPointsRecordDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rewardPointsRecordDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reward-points-records/{id}")
    public ResponseEntity<RewardPointsRecordDTO> getRewardPointsRecord(@PathVariable Long id) {
        log.debug("REST request to get RewardPointsRecord : {}", id);
        Optional<RewardPointsRecordDTO> rewardPointsRecordDTO = rewardPointsRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rewardPointsRecordDTO);
    }

    /**
     * {@code DELETE  /reward-points-records/:id} : delete the "id" rewardPointsRecord.
     *
     * @param id the id of the rewardPointsRecordDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reward-points-records/{id}")
    public ResponseEntity<Void> deleteRewardPointsRecord(@PathVariable Long id) {
        log.debug("REST request to delete RewardPointsRecord : {}", id);
        rewardPointsRecordService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
