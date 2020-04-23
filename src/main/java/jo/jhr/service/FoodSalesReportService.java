package jo.jhr.service;

import jo.jhr.domain.FoodSalesReport;
import jo.jhr.repository.FoodSalesReportRepository;
import jo.jhr.service.dto.FoodSalesReportDTO;
import jo.jhr.service.mapper.FoodSalesReportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FoodSalesReport}.
 */
@Service
@Transactional
public class FoodSalesReportService {

    private final Logger log = LoggerFactory.getLogger(FoodSalesReportService.class);

    private final FoodSalesReportRepository foodSalesReportRepository;

    private final FoodSalesReportMapper foodSalesReportMapper;

    public FoodSalesReportService(FoodSalesReportRepository foodSalesReportRepository, FoodSalesReportMapper foodSalesReportMapper) {
        this.foodSalesReportRepository = foodSalesReportRepository;
        this.foodSalesReportMapper = foodSalesReportMapper;
    }

    /**
     * Save a foodSalesReport.
     *
     * @param foodSalesReportDTO the entity to save.
     * @return the persisted entity.
     */
    public FoodSalesReportDTO save(FoodSalesReportDTO foodSalesReportDTO) {
        log.debug("Request to save FoodSalesReport : {}", foodSalesReportDTO);
        FoodSalesReport foodSalesReport = foodSalesReportMapper.toEntity(foodSalesReportDTO);
        foodSalesReport = foodSalesReportRepository.save(foodSalesReport);
        return foodSalesReportMapper.toDto(foodSalesReport);
    }

    /**
     * Get all the foodSalesReports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FoodSalesReportDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FoodSalesReports");
        return foodSalesReportRepository.findAll(pageable)
            .map(foodSalesReportMapper::toDto);
    }

    /**
     * Get one foodSalesReport by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FoodSalesReportDTO> findOne(Long id) {
        log.debug("Request to get FoodSalesReport : {}", id);
        return foodSalesReportRepository.findById(id)
            .map(foodSalesReportMapper::toDto);
    }

    /**
     * Delete the foodSalesReport by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FoodSalesReport : {}", id);
        foodSalesReportRepository.deleteById(id);
    }
}
