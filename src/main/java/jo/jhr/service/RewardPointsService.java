package jo.jhr.service;

import jo.jhr.domain.RewardPoints;
import jo.jhr.repository.RewardPointsRepository;
import jo.jhr.service.dto.RewardPointsDTO;
import jo.jhr.service.mapper.RewardPointsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RewardPoints}.
 */
@Service
@Transactional
public class RewardPointsService {

    private final Logger log = LoggerFactory.getLogger(RewardPointsService.class);

    private final RewardPointsRepository rewardPointsRepository;

    private final RewardPointsMapper rewardPointsMapper;

    public RewardPointsService(RewardPointsRepository rewardPointsRepository, RewardPointsMapper rewardPointsMapper) {
        this.rewardPointsRepository = rewardPointsRepository;
        this.rewardPointsMapper = rewardPointsMapper;
    }

    /**
     * Save a rewardPoints.
     *
     * @param rewardPointsDTO the entity to save.
     * @return the persisted entity.
     */
    public RewardPointsDTO save(RewardPointsDTO rewardPointsDTO) {
        log.debug("Request to save RewardPoints : {}", rewardPointsDTO);
        RewardPoints rewardPoints = rewardPointsMapper.toEntity(rewardPointsDTO);
        rewardPoints = rewardPointsRepository.save(rewardPoints);
        return rewardPointsMapper.toDto(rewardPoints);
    }

    /**
     * Get all the rewardPoints.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RewardPointsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RewardPoints");
        return rewardPointsRepository.findAll(pageable)
            .map(rewardPointsMapper::toDto);
    }

    /**
     * Get one rewardPoints by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RewardPointsDTO> findOne(Long id) {
        log.debug("Request to get RewardPoints : {}", id);
        return rewardPointsRepository.findById(id)
            .map(rewardPointsMapper::toDto);
    }

    /**
     * Delete the rewardPoints by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RewardPoints : {}", id);
        rewardPointsRepository.deleteById(id);
    }
}
