package jo.jhr.service;

import jo.jhr.domain.DeliveryAddress;
import jo.jhr.repository.DeliveryAddressRepository;
import jo.jhr.service.dto.DeliveryAddressDTO;
import jo.jhr.service.mapper.DeliveryAddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link DeliveryAddress}.
 */
@Service
@Transactional
public class DeliveryAddressService {

    private final Logger log = LoggerFactory.getLogger(DeliveryAddressService.class);

    private final DeliveryAddressRepository deliveryAddressRepository;

    private final DeliveryAddressMapper deliveryAddressMapper;

    public DeliveryAddressService(DeliveryAddressRepository deliveryAddressRepository, DeliveryAddressMapper deliveryAddressMapper) {
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.deliveryAddressMapper = deliveryAddressMapper;
    }

    /**
     * Save a deliveryAddress.
     *
     * @param deliveryAddressDTO the entity to save.
     * @return the persisted entity.
     */
    public DeliveryAddressDTO save(DeliveryAddressDTO deliveryAddressDTO) {
        log.debug("Request to save DeliveryAddress : {}", deliveryAddressDTO);
        DeliveryAddress deliveryAddress = deliveryAddressMapper.toEntity(deliveryAddressDTO);
        deliveryAddress = deliveryAddressRepository.save(deliveryAddress);
        return deliveryAddressMapper.toDto(deliveryAddress);
    }

    /**
     * Get all the deliveryAddresses.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DeliveryAddressDTO> findAll() {
        log.debug("Request to get all DeliveryAddresses");
        return deliveryAddressRepository.findAll().stream()
            .map(deliveryAddressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one deliveryAddress by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DeliveryAddressDTO> findOne(Long id) {
        log.debug("Request to get DeliveryAddress : {}", id);
        return deliveryAddressRepository.findById(id)
            .map(deliveryAddressMapper::toDto);
    }

    /**
     * Delete the deliveryAddress by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DeliveryAddress : {}", id);
        deliveryAddressRepository.deleteById(id);
    }
}
