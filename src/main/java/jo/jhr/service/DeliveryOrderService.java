package jo.jhr.service;

import jo.jhr.domain.DeliveryOrder;
import jo.jhr.repository.DeliveryOrderRepository;
import jo.jhr.service.dto.DeliveryOrderDTO;
import jo.jhr.service.mapper.DeliveryOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DeliveryOrder}.
 */
@Service
@Transactional
public class DeliveryOrderService {

    private final Logger log = LoggerFactory.getLogger(DeliveryOrderService.class);

    private final DeliveryOrderRepository deliveryOrderRepository;

    private final DeliveryOrderMapper deliveryOrderMapper;

    public DeliveryOrderService(DeliveryOrderRepository deliveryOrderRepository, DeliveryOrderMapper deliveryOrderMapper) {
        this.deliveryOrderRepository = deliveryOrderRepository;
        this.deliveryOrderMapper = deliveryOrderMapper;
    }

    /**
     * Save a deliveryOrder.
     *
     * @param deliveryOrderDTO the entity to save.
     * @return the persisted entity.
     */
    public DeliveryOrderDTO save(DeliveryOrderDTO deliveryOrderDTO) {
        log.debug("Request to save DeliveryOrder : {}", deliveryOrderDTO);
        DeliveryOrder deliveryOrder = deliveryOrderMapper.toEntity(deliveryOrderDTO);
        deliveryOrder = deliveryOrderRepository.save(deliveryOrder);
        return deliveryOrderMapper.toDto(deliveryOrder);
    }

    /**
     * Get all the deliveryOrders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DeliveryOrderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveryOrders");
        return deliveryOrderRepository.findAll(pageable)
            .map(deliveryOrderMapper::toDto);
    }

    /**
     * Get one deliveryOrder by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DeliveryOrderDTO> findOne(Long id) {
        log.debug("Request to get DeliveryOrder : {}", id);
        return deliveryOrderRepository.findById(id)
            .map(deliveryOrderMapper::toDto);
    }

    /**
     * Delete the deliveryOrder by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DeliveryOrder : {}", id);
        deliveryOrderRepository.deleteById(id);
    }
}
