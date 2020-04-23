package jo.jhr.service;

import jo.jhr.domain.ExceptionOrder;
import jo.jhr.repository.ExceptionOrderRepository;
import jo.jhr.service.dto.ExceptionOrderDTO;
import jo.jhr.service.mapper.ExceptionOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ExceptionOrder}.
 */
@Service
@Transactional
public class ExceptionOrderService {

    private final Logger log = LoggerFactory.getLogger(ExceptionOrderService.class);

    private final ExceptionOrderRepository exceptionOrderRepository;

    private final ExceptionOrderMapper exceptionOrderMapper;

    public ExceptionOrderService(ExceptionOrderRepository exceptionOrderRepository, ExceptionOrderMapper exceptionOrderMapper) {
        this.exceptionOrderRepository = exceptionOrderRepository;
        this.exceptionOrderMapper = exceptionOrderMapper;
    }

    /**
     * Save a exceptionOrder.
     *
     * @param exceptionOrderDTO the entity to save.
     * @return the persisted entity.
     */
    public ExceptionOrderDTO save(ExceptionOrderDTO exceptionOrderDTO) {
        log.debug("Request to save ExceptionOrder : {}", exceptionOrderDTO);
        ExceptionOrder exceptionOrder = exceptionOrderMapper.toEntity(exceptionOrderDTO);
        exceptionOrder = exceptionOrderRepository.save(exceptionOrder);
        return exceptionOrderMapper.toDto(exceptionOrder);
    }

    /**
     * Get all the exceptionOrders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ExceptionOrderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ExceptionOrders");
        return exceptionOrderRepository.findAll(pageable)
            .map(exceptionOrderMapper::toDto);
    }

    /**
     * Get one exceptionOrder by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ExceptionOrderDTO> findOne(Long id) {
        log.debug("Request to get ExceptionOrder : {}", id);
        return exceptionOrderRepository.findById(id)
            .map(exceptionOrderMapper::toDto);
    }

    /**
     * Delete the exceptionOrder by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ExceptionOrder : {}", id);
        exceptionOrderRepository.deleteById(id);
    }
}
