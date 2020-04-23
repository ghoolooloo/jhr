package jo.jhr.service;

import jo.jhr.domain.FinalCard;
import jo.jhr.repository.FinalCardRepository;
import jo.jhr.service.dto.FinalCardDTO;
import jo.jhr.service.mapper.FinalCardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FinalCard}.
 */
@Service
@Transactional
public class FinalCardService {

    private final Logger log = LoggerFactory.getLogger(FinalCardService.class);

    private final FinalCardRepository finalCardRepository;

    private final FinalCardMapper finalCardMapper;

    public FinalCardService(FinalCardRepository finalCardRepository, FinalCardMapper finalCardMapper) {
        this.finalCardRepository = finalCardRepository;
        this.finalCardMapper = finalCardMapper;
    }

    /**
     * Save a finalCard.
     *
     * @param finalCardDTO the entity to save.
     * @return the persisted entity.
     */
    public FinalCardDTO save(FinalCardDTO finalCardDTO) {
        log.debug("Request to save FinalCard : {}", finalCardDTO);
        FinalCard finalCard = finalCardMapper.toEntity(finalCardDTO);
        finalCard = finalCardRepository.save(finalCard);
        return finalCardMapper.toDto(finalCard);
    }

    /**
     * Get all the finalCards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FinalCardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FinalCards");
        return finalCardRepository.findAll(pageable)
            .map(finalCardMapper::toDto);
    }

    /**
     * Get one finalCard by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FinalCardDTO> findOne(Long id) {
        log.debug("Request to get FinalCard : {}", id);
        return finalCardRepository.findById(id)
            .map(finalCardMapper::toDto);
    }

    /**
     * Delete the finalCard by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FinalCard : {}", id);
        finalCardRepository.deleteById(id);
    }
}
