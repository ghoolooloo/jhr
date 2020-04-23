package jo.jhr.service;

import jo.jhr.domain.WeekStock;
import jo.jhr.repository.WeekStockRepository;
import jo.jhr.service.dto.WeekStockDTO;
import jo.jhr.service.mapper.WeekStockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link WeekStock}.
 */
@Service
@Transactional
public class WeekStockService {

    private final Logger log = LoggerFactory.getLogger(WeekStockService.class);

    private final WeekStockRepository weekStockRepository;

    private final WeekStockMapper weekStockMapper;

    public WeekStockService(WeekStockRepository weekStockRepository, WeekStockMapper weekStockMapper) {
        this.weekStockRepository = weekStockRepository;
        this.weekStockMapper = weekStockMapper;
    }

    /**
     * Save a weekStock.
     *
     * @param weekStockDTO the entity to save.
     * @return the persisted entity.
     */
    public WeekStockDTO save(WeekStockDTO weekStockDTO) {
        log.debug("Request to save WeekStock : {}", weekStockDTO);
        WeekStock weekStock = weekStockMapper.toEntity(weekStockDTO);
        weekStock = weekStockRepository.save(weekStock);
        return weekStockMapper.toDto(weekStock);
    }

    /**
     * Get all the weekStocks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<WeekStockDTO> findAll() {
        log.debug("Request to get all WeekStocks");
        return weekStockRepository.findAll().stream()
            .map(weekStockMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one weekStock by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WeekStockDTO> findOne(Long id) {
        log.debug("Request to get WeekStock : {}", id);
        return weekStockRepository.findById(id)
            .map(weekStockMapper::toDto);
    }

    /**
     * Delete the weekStock by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete WeekStock : {}", id);
        weekStockRepository.deleteById(id);
    }
}
