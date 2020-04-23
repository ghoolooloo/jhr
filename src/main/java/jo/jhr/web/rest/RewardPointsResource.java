package jo.jhr.web.rest;

import jo.jhr.service.RewardPointsService;
import jo.jhr.web.rest.errors.BadRequestAlertException;
import jo.jhr.service.dto.RewardPointsDTO;

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
 * REST controller for managing {@link jo.jhr.domain.RewardPoints}.
 */
@RestController
@RequestMapping("/api")
public class RewardPointsResource {

    private final Logger log = LoggerFactory.getLogger(RewardPointsResource.class);

    private static final String ENTITY_NAME = "rewardPoints";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RewardPointsService rewardPointsService;

    public RewardPointsResource(RewardPointsService rewardPointsService) {
        this.rewardPointsService = rewardPointsService;
    }

    /**
     * {@code POST  /reward-points} : Create a new rewardPoints.
     *
     * @param rewardPointsDTO the rewardPointsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rewardPointsDTO, or with status {@code 400 (Bad Request)} if the rewardPoints has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reward-points")
    public ResponseEntity<RewardPointsDTO> createRewardPoints(@Valid @RequestBody RewardPointsDTO rewardPointsDTO) throws URISyntaxException {
        log.debug("REST request to save RewardPoints : {}", rewardPointsDTO);
        if (rewardPointsDTO.getId() != null) {
            throw new BadRequestAlertException("A new rewardPoints cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RewardPointsDTO result = rewardPointsService.save(rewardPointsDTO);
        return ResponseEntity.created(new URI("/api/reward-points/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reward-points} : Updates an existing rewardPoints.
     *
     * @param rewardPointsDTO the rewardPointsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rewardPointsDTO,
     * or with status {@code 400 (Bad Request)} if the rewardPointsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rewardPointsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reward-points")
    public ResponseEntity<RewardPointsDTO> updateRewardPoints(@Valid @RequestBody RewardPointsDTO rewardPointsDTO) throws URISyntaxException {
        log.debug("REST request to update RewardPoints : {}", rewardPointsDTO);
        if (rewardPointsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RewardPointsDTO result = rewardPointsService.save(rewardPointsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rewardPointsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /reward-points} : get all the rewardPoints.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rewardPoints in body.
     */
    @GetMapping("/reward-points")
    public ResponseEntity<List<RewardPointsDTO>> getAllRewardPoints(Pageable pageable) {
        log.debug("REST request to get a page of RewardPoints");
        Page<RewardPointsDTO> page = rewardPointsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /reward-points/:id} : get the "id" rewardPoints.
     *
     * @param id the id of the rewardPointsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rewardPointsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reward-points/{id}")
    public ResponseEntity<RewardPointsDTO> getRewardPoints(@PathVariable Long id) {
        log.debug("REST request to get RewardPoints : {}", id);
        Optional<RewardPointsDTO> rewardPointsDTO = rewardPointsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rewardPointsDTO);
    }

    /**
     * {@code DELETE  /reward-points/:id} : delete the "id" rewardPoints.
     *
     * @param id the id of the rewardPointsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reward-points/{id}")
    public ResponseEntity<Void> deleteRewardPoints(@PathVariable Long id) {
        log.debug("REST request to delete RewardPoints : {}", id);
        rewardPointsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
