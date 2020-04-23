package jo.jhr.service;

import jo.jhr.domain.WriteOffCard;
import jo.jhr.repository.WriteOffCardRepository;
import jo.jhr.service.dto.WriteOffCardDTO;
import jo.jhr.service.mapper.WriteOffCardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link WriteOffCard}.
 */
@Service
@Transactional
public class WriteOffCardService {

    private final Logger log = LoggerFactory.getLogger(WriteOffCardService.class);

    private final WriteOffCardRepository writeOffCardRepository;

    private final WriteOffCardMapper writeOffCardMapper;

    public WriteOffCardService(WriteOffCardRepository writeOffCardRepository, WriteOffCardMapper writeOffCardMapper) {
        this.writeOffCardRepository = writeOffCardRepository;
        this.writeOffCardMapper = writeOffCardMapper;
    }

    /**
     * Save a writeOffCard.
     *
     * @param writeOffCardDTO the entity to save.
     * @return the persisted entity.
     */
    public WriteOffCardDTO save(WriteOffCardDTO writeOffCardDTO) {
        log.debug("Request to save WriteOffCard : {}", writeOffCardDTO);
        WriteOffCard writeOffCard = writeOffCardMapper.toEntity(writeOffCardDTO);
        writeOffCard = writeOffCardRepository.save(writeOffCard);
        return writeOffCardMapper.toDto(writeOffCard);
    }

    /**
     * Get all the writeOffCards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<WriteOffCardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WriteOffCards");
        return writeOffCardRepository.findAll(pageable)
            .map(writeOffCardMapper::toDto);
    }

    /**
     * Get one writeOffCard by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WriteOffCardDTO> findOne(Long id) {
        log.debug("Request to get WriteOffCard : {}", id);
        return writeOffCardRepository.findById(id)
            .map(writeOffCardMapper::toDto);
    }

    /**
     * Delete the writeOffCard by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete WriteOffCard : {}", id);
        writeOffCardRepository.deleteById(id);
    }
}
