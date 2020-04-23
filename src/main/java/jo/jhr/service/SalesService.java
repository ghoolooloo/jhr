package jo.jhr.service;

import jo.jhr.domain.Sales;
import jo.jhr.repository.SalesRepository;
import jo.jhr.service.dto.SalesDTO;
import jo.jhr.service.mapper.SalesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Sales}.
 */
@Service
@Transactional
public class SalesService {

    private final Logger log = LoggerFactory.getLogger(SalesService.class);

    private final SalesRepository salesRepository;

    private final SalesMapper salesMapper;

    public SalesService(SalesRepository salesRepository, SalesMapper salesMapper) {
        this.salesRepository = salesRepository;
        this.salesMapper = salesMapper;
    }

    /**
     * Save a sales.
     *
     * @param salesDTO the entity to save.
     * @return the persisted entity.
     */
    public SalesDTO save(SalesDTO salesDTO) {
        log.debug("Request to save Sales : {}", salesDTO);
        Sales sales = salesMapper.toEntity(salesDTO);
        sales = salesRepository.save(sales);
        return salesMapper.toDto(sales);
    }

    /**
     * Get all the sales.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SalesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Sales");
        return salesRepository.findAll(pageable)
            .map(salesMapper::toDto);
    }

    /**
     * Get one sales by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SalesDTO> findOne(Long id) {
        log.debug("Request to get Sales : {}", id);
        return salesRepository.findById(id)
            .map(salesMapper::toDto);
    }

    /**
     * Delete the sales by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Sales : {}", id);
        salesRepository.deleteById(id);
    }
}
