package jo.jhr.service;

import jo.jhr.domain.ClosedOrder;
import jo.jhr.repository.ClosedOrderRepository;
import jo.jhr.service.dto.ClosedOrderDTO;
import jo.jhr.service.mapper.ClosedOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ClosedOrder}.
 */
@Service
@Transactional
public class ClosedOrderService {

    private final Logger log = LoggerFactory.getLogger(ClosedOrderService.class);

    private final ClosedOrderRepository closedOrderRepository;

    private final ClosedOrderMapper closedOrderMapper;

    public ClosedOrderService(ClosedOrderRepository closedOrderRepository, ClosedOrderMapper closedOrderMapper) {
        this.closedOrderRepository = closedOrderRepository;
        this.closedOrderMapper = closedOrderMapper;
    }

    /**
     * Save a closedOrder.
     *
     * @param closedOrderDTO the entity to save.
     * @return the persisted entity.
     */
    public ClosedOrderDTO save(ClosedOrderDTO closedOrderDTO) {
        log.debug("Request to save ClosedOrder : {}", closedOrderDTO);
        ClosedOrder closedOrder = closedOrderMapper.toEntity(closedOrderDTO);
        closedOrder = closedOrderRepository.save(closedOrder);
        return closedOrderMapper.toDto(closedOrder);
    }

    /**
     * Get all the closedOrders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ClosedOrderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClosedOrders");
        return closedOrderRepository.findAll(pageable)
            .map(closedOrderMapper::toDto);
    }

    /**
     * Get one closedOrder by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ClosedOrderDTO> findOne(Long id) {
        log.debug("Request to get ClosedOrder : {}", id);
        return closedOrderRepository.findById(id)
            .map(closedOrderMapper::toDto);
    }

    /**
     * Delete the closedOrder by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ClosedOrder : {}", id);
        closedOrderRepository.deleteById(id);
    }
}
