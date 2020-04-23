package jo.jhr.service;

import jo.jhr.domain.RefundOrder;
import jo.jhr.repository.RefundOrderRepository;
import jo.jhr.service.dto.RefundOrderDTO;
import jo.jhr.service.mapper.RefundOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RefundOrder}.
 */
@Service
@Transactional
public class RefundOrderService {

    private final Logger log = LoggerFactory.getLogger(RefundOrderService.class);

    private final RefundOrderRepository refundOrderRepository;

    private final RefundOrderMapper refundOrderMapper;

    public RefundOrderService(RefundOrderRepository refundOrderRepository, RefundOrderMapper refundOrderMapper) {
        this.refundOrderRepository = refundOrderRepository;
        this.refundOrderMapper = refundOrderMapper;
    }

    /**
     * Save a refundOrder.
     *
     * @param refundOrderDTO the entity to save.
     * @return the persisted entity.
     */
    public RefundOrderDTO save(RefundOrderDTO refundOrderDTO) {
        log.debug("Request to save RefundOrder : {}", refundOrderDTO);
        RefundOrder refundOrder = refundOrderMapper.toEntity(refundOrderDTO);
        refundOrder = refundOrderRepository.save(refundOrder);
        return refundOrderMapper.toDto(refundOrder);
    }

    /**
     * Get all the refundOrders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RefundOrderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RefundOrders");
        return refundOrderRepository.findAll(pageable)
            .map(refundOrderMapper::toDto);
    }

    /**
     * Get one refundOrder by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RefundOrderDTO> findOne(Long id) {
        log.debug("Request to get RefundOrder : {}", id);
        return refundOrderRepository.findById(id)
            .map(refundOrderMapper::toDto);
    }

    /**
     * Delete the refundOrder by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RefundOrder : {}", id);
        refundOrderRepository.deleteById(id);
    }
}
