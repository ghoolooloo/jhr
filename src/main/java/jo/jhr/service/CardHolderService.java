package jo.jhr.service;

import jo.jhr.domain.CardHolder;
import jo.jhr.repository.CardHolderRepository;
import jo.jhr.service.dto.CardHolderDTO;
import jo.jhr.service.mapper.CardHolderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CardHolder}.
 */
@Service
@Transactional
public class CardHolderService {

    private final Logger log = LoggerFactory.getLogger(CardHolderService.class);

    private final CardHolderRepository cardHolderRepository;

    private final CardHolderMapper cardHolderMapper;

    public CardHolderService(CardHolderRepository cardHolderRepository, CardHolderMapper cardHolderMapper) {
        this.cardHolderRepository = cardHolderRepository;
        this.cardHolderMapper = cardHolderMapper;
    }

    /**
     * Save a cardHolder.
     *
     * @param cardHolderDTO the entity to save.
     * @return the persisted entity.
     */
    public CardHolderDTO save(CardHolderDTO cardHolderDTO) {
        log.debug("Request to save CardHolder : {}", cardHolderDTO);
        CardHolder cardHolder = cardHolderMapper.toEntity(cardHolderDTO);
        cardHolder = cardHolderRepository.save(cardHolder);
        return cardHolderMapper.toDto(cardHolder);
    }

    /**
     * Get all the cardHolders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CardHolderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CardHolders");
        return cardHolderRepository.findAll(pageable)
            .map(cardHolderMapper::toDto);
    }

    /**
     * Get one cardHolder by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CardHolderDTO> findOne(Long id) {
        log.debug("Request to get CardHolder : {}", id);
        return cardHolderRepository.findById(id)
            .map(cardHolderMapper::toDto);
    }

    /**
     * Delete the cardHolder by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CardHolder : {}", id);
        cardHolderRepository.deleteById(id);
    }
}
