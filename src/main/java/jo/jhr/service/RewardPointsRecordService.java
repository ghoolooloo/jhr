package jo.jhr.service;

import jo.jhr.domain.RewardPointsRecord;
import jo.jhr.repository.RewardPointsRecordRepository;
import jo.jhr.service.dto.RewardPointsRecordDTO;
import jo.jhr.service.mapper.RewardPointsRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RewardPointsRecord}.
 */
@Service
@Transactional
public class RewardPointsRecordService {

    private final Logger log = LoggerFactory.getLogger(RewardPointsRecordService.class);

    private final RewardPointsRecordRepository rewardPointsRecordRepository;

    private final RewardPointsRecordMapper rewardPointsRecordMapper;

    public RewardPointsRecordService(RewardPointsRecordRepository rewardPointsRecordRepository, RewardPointsRecordMapper rewardPointsRecordMapper) {
        this.rewardPointsRecordRepository = rewardPointsRecordRepository;
        this.rewardPointsRecordMapper = rewardPointsRecordMapper;
    }

    /**
     * Save a rewardPointsRecord.
     *
     * @param rewardPointsRecordDTO the entity to save.
     * @return the persisted entity.
     */
    public RewardPointsRecordDTO save(RewardPointsRecordDTO rewardPointsRecordDTO) {
        log.debug("Request to save RewardPointsRecord : {}", rewardPointsRecordDTO);
        RewardPointsRecord rewardPointsRecord = rewardPointsRecordMapper.toEntity(rewardPointsRecordDTO);
        rewardPointsRecord = rewardPointsRecordRepository.save(rewardPointsRecord);
        return rewardPointsRecordMapper.toDto(rewardPointsRecord);
    }

    /**
     * Get all the rewardPointsRecords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RewardPointsRecordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RewardPointsRecords");
        return rewardPointsRecordRepository.findAll(pageable)
            .map(rewardPointsRecordMapper::toDto);
    }

    /**
     * Get one rewardPointsRecord by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RewardPointsRecordDTO> findOne(Long id) {
        log.debug("Request to get RewardPointsRecord : {}", id);
        return rewardPointsRecordRepository.findById(id)
            .map(rewardPointsRecordMapper::toDto);
    }

    /**
     * Delete the rewardPointsRecord by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RewardPointsRecord : {}", id);
        rewardPointsRecordRepository.deleteById(id);
    }
}
