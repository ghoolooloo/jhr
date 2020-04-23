package jo.jhr.web.rest;

import jo.jhr.RedisTestContainerExtension;
import jo.jhr.JhrApp;
import jo.jhr.domain.FoodSalesReport;
import jo.jhr.repository.FoodSalesReportRepository;
import jo.jhr.service.FoodSalesReportService;
import jo.jhr.service.dto.FoodSalesReportDTO;
import jo.jhr.service.mapper.FoodSalesReportMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FoodSalesReportResource} REST controller.
 */
@SpringBootTest(classes = JhrApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class FoodSalesReportResourceIT {

    private static final Instant DEFAULT_DAY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DAY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SHOP_SN = "AAAAAAAAAA";
    private static final String UPDATED_SHOP_SN = "BBBBBBBBBB";

    private static final String DEFAULT_SHOP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHOP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY_SN = "AAAAA";
    private static final String UPDATED_CATEGORY_SN = "BBBBB";

    private static final String DEFAULT_CATEGORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FOOD_SN = "AAAAAAAAAA";
    private static final String UPDATED_FOOD_SN = "BBBBBBBBBB";

    private static final String DEFAULT_FOOD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FOOD_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_COST = 1;
    private static final Integer UPDATED_COST = 2;

    private static final Integer DEFAULT_PRICE = 1;
    private static final Integer UPDATED_PRICE = 2;

    private static final Integer DEFAULT_ORIGINAL_PRICE = 1;
    private static final Integer UPDATED_ORIGINAL_PRICE = 2;

    private static final Integer DEFAULT_SALES_QUANTITY = 1;
    private static final Integer UPDATED_SALES_QUANTITY = 2;

    private static final Integer DEFAULT_SALES_AMOUNT = 1;
    private static final Integer UPDATED_SALES_AMOUNT = 2;

    private static final Integer DEFAULT_REFUND_ORDERS = 1;
    private static final Integer UPDATED_REFUND_ORDERS = 2;

    private static final Integer DEFAULT_REFUND_AMOUNT = 1;
    private static final Integer UPDATED_REFUND_AMOUNT = 2;

    private static final Integer DEFAULT_GROSS_PROFIT = 1;
    private static final Integer UPDATED_GROSS_PROFIT = 2;

    private static final Integer DEFAULT_INIT_QUANTITY = 1;
    private static final Integer UPDATED_INIT_QUANTITY = 2;

    private static final Integer DEFAULT_REMAINDER = 1;
    private static final Integer UPDATED_REMAINDER = 2;

    @Autowired
    private FoodSalesReportRepository foodSalesReportRepository;

    @Autowired
    private FoodSalesReportMapper foodSalesReportMapper;

    @Autowired
    private FoodSalesReportService foodSalesReportService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFoodSalesReportMockMvc;

    private FoodSalesReport foodSalesReport;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FoodSalesReport createEntity(EntityManager em) {
        FoodSalesReport foodSalesReport = new FoodSalesReport()
            .day(DEFAULT_DAY)
            .shopSN(DEFAULT_SHOP_SN)
            .shopName(DEFAULT_SHOP_NAME)
            .categorySN(DEFAULT_CATEGORY_SN)
            .categoryName(DEFAULT_CATEGORY_NAME)
            .foodSN(DEFAULT_FOOD_SN)
            .foodName(DEFAULT_FOOD_NAME)
            .cost(DEFAULT_COST)
            .price(DEFAULT_PRICE)
            .originalPrice(DEFAULT_ORIGINAL_PRICE)
            .salesQuantity(DEFAULT_SALES_QUANTITY)
            .salesAmount(DEFAULT_SALES_AMOUNT)
            .refundOrders(DEFAULT_REFUND_ORDERS)
            .refundAmount(DEFAULT_REFUND_AMOUNT)
            .grossProfit(DEFAULT_GROSS_PROFIT)
            .initQuantity(DEFAULT_INIT_QUANTITY)
            .remainder(DEFAULT_REMAINDER);
        return foodSalesReport;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FoodSalesReport createUpdatedEntity(EntityManager em) {
        FoodSalesReport foodSalesReport = new FoodSalesReport()
            .day(UPDATED_DAY)
            .shopSN(UPDATED_SHOP_SN)
            .shopName(UPDATED_SHOP_NAME)
            .categorySN(UPDATED_CATEGORY_SN)
            .categoryName(UPDATED_CATEGORY_NAME)
            .foodSN(UPDATED_FOOD_SN)
            .foodName(UPDATED_FOOD_NAME)
            .cost(UPDATED_COST)
            .price(UPDATED_PRICE)
            .originalPrice(UPDATED_ORIGINAL_PRICE)
            .salesQuantity(UPDATED_SALES_QUANTITY)
            .salesAmount(UPDATED_SALES_AMOUNT)
            .refundOrders(UPDATED_REFUND_ORDERS)
            .refundAmount(UPDATED_REFUND_AMOUNT)
            .grossProfit(UPDATED_GROSS_PROFIT)
            .initQuantity(UPDATED_INIT_QUANTITY)
            .remainder(UPDATED_REMAINDER);
        return foodSalesReport;
    }

    @BeforeEach
    public void initTest() {
        foodSalesReport = createEntity(em);
    }

    @Test
    @Transactional
    public void createFoodSalesReport() throws Exception {
        int databaseSizeBeforeCreate = foodSalesReportRepository.findAll().size();

        // Create the FoodSalesReport
        FoodSalesReportDTO foodSalesReportDTO = foodSalesReportMapper.toDto(foodSalesReport);
        restFoodSalesReportMockMvc.perform(post("/api/food-sales-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodSalesReportDTO)))
            .andExpect(status().isCreated());

        // Validate the FoodSalesReport in the database
        List<FoodSalesReport> foodSalesReportList = foodSalesReportRepository.findAll();
        assertThat(foodSalesReportList).hasSize(databaseSizeBeforeCreate + 1);
        FoodSalesReport testFoodSalesReport = foodSalesReportList.get(foodSalesReportList.size() - 1);
        assertThat(testFoodSalesReport.getDay()).isEqualTo(DEFAULT_DAY);
        assertThat(testFoodSalesReport.getShopSN()).isEqualTo(DEFAULT_SHOP_SN);
        assertThat(testFoodSalesReport.getShopName()).isEqualTo(DEFAULT_SHOP_NAME);
        assertThat(testFoodSalesReport.getCategorySN()).isEqualTo(DEFAULT_CATEGORY_SN);
        assertThat(testFoodSalesReport.getCategoryName()).isEqualTo(DEFAULT_CATEGORY_NAME);
        assertThat(testFoodSalesReport.getFoodSN()).isEqualTo(DEFAULT_FOOD_SN);
        assertThat(testFoodSalesReport.getFoodName()).isEqualTo(DEFAULT_FOOD_NAME);
        assertThat(testFoodSalesReport.getCost()).isEqualTo(DEFAULT_COST);
        assertThat(testFoodSalesReport.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testFoodSalesReport.getOriginalPrice()).isEqualTo(DEFAULT_ORIGINAL_PRICE);
        assertThat(testFoodSalesReport.getSalesQuantity()).isEqualTo(DEFAULT_SALES_QUANTITY);
        assertThat(testFoodSalesReport.getSalesAmount()).isEqualTo(DEFAULT_SALES_AMOUNT);
        assertThat(testFoodSalesReport.getRefundOrders()).isEqualTo(DEFAULT_REFUND_ORDERS);
        assertThat(testFoodSalesReport.getRefundAmount()).isEqualTo(DEFAULT_REFUND_AMOUNT);
        assertThat(testFoodSalesReport.getGrossProfit()).isEqualTo(DEFAULT_GROSS_PROFIT);
        assertThat(testFoodSalesReport.getInitQuantity()).isEqualTo(DEFAULT_INIT_QUANTITY);
        assertThat(testFoodSalesReport.getRemainder()).isEqualTo(DEFAULT_REMAINDER);
    }

    @Test
    @Transactional
    public void createFoodSalesReportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = foodSalesReportRepository.findAll().size();

        // Create the FoodSalesReport with an existing ID
        foodSalesReport.setId(1L);
        FoodSalesReportDTO foodSalesReportDTO = foodSalesReportMapper.toDto(foodSalesReport);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFoodSalesReportMockMvc.perform(post("/api/food-sales-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodSalesReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FoodSalesReport in the database
        List<FoodSalesReport> foodSalesReportList = foodSalesReportRepository.findAll();
        assertThat(foodSalesReportList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDayIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodSalesReportRepository.findAll().size();
        // set the field null
        foodSalesReport.setDay(null);

        // Create the FoodSalesReport, which fails.
        FoodSalesReportDTO foodSalesReportDTO = foodSalesReportMapper.toDto(foodSalesReport);

        restFoodSalesReportMockMvc.perform(post("/api/food-sales-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodSalesReportDTO)))
            .andExpect(status().isBadRequest());

        List<FoodSalesReport> foodSalesReportList = foodSalesReportRepository.findAll();
        assertThat(foodSalesReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShopSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodSalesReportRepository.findAll().size();
        // set the field null
        foodSalesReport.setShopSN(null);

        // Create the FoodSalesReport, which fails.
        FoodSalesReportDTO foodSalesReportDTO = foodSalesReportMapper.toDto(foodSalesReport);

        restFoodSalesReportMockMvc.perform(post("/api/food-sales-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodSalesReportDTO)))
            .andExpect(status().isBadRequest());

        List<FoodSalesReport> foodSalesReportList = foodSalesReportRepository.findAll();
        assertThat(foodSalesReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShopNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodSalesReportRepository.findAll().size();
        // set the field null
        foodSalesReport.setShopName(null);

        // Create the FoodSalesReport, which fails.
        FoodSalesReportDTO foodSalesReportDTO = foodSalesReportMapper.toDto(foodSalesReport);

        restFoodSalesReportMockMvc.perform(post("/api/food-sales-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodSalesReportDTO)))
            .andExpect(status().isBadRequest());

        List<FoodSalesReport> foodSalesReportList = foodSalesReportRepository.findAll();
        assertThat(foodSalesReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategorySNIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodSalesReportRepository.findAll().size();
        // set the field null
        foodSalesReport.setCategorySN(null);

        // Create the FoodSalesReport, which fails.
        FoodSalesReportDTO foodSalesReportDTO = foodSalesReportMapper.toDto(foodSalesReport);

        restFoodSalesReportMockMvc.perform(post("/api/food-sales-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodSalesReportDTO)))
            .andExpect(status().isBadRequest());

        List<FoodSalesReport> foodSalesReportList = foodSalesReportRepository.findAll();
        assertThat(foodSalesReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategoryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodSalesReportRepository.findAll().size();
        // set the field null
        foodSalesReport.setCategoryName(null);

        // Create the FoodSalesReport, which fails.
        FoodSalesReportDTO foodSalesReportDTO = foodSalesReportMapper.toDto(foodSalesReport);

        restFoodSalesReportMockMvc.perform(post("/api/food-sales-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodSalesReportDTO)))
            .andExpect(status().isBadRequest());

        List<FoodSalesReport> foodSalesReportList = foodSalesReportRepository.findAll();
        assertThat(foodSalesReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFoodSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodSalesReportRepository.findAll().size();
        // set the field null
        foodSalesReport.setFoodSN(null);

        // Create the FoodSalesReport, which fails.
        FoodSalesReportDTO foodSalesReportDTO = foodSalesReportMapper.toDto(foodSalesReport);

        restFoodSalesReportMockMvc.perform(post("/api/food-sales-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodSalesReportDTO)))
            .andExpect(status().isBadRequest());

        List<FoodSalesReport> foodSalesReportList = foodSalesReportRepository.findAll();
        assertThat(foodSalesReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFoodNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodSalesReportRepository.findAll().size();
        // set the field null
        foodSalesReport.setFoodName(null);

        // Create the FoodSalesReport, which fails.
        FoodSalesReportDTO foodSalesReportDTO = foodSalesReportMapper.toDto(foodSalesReport);

        restFoodSalesReportMockMvc.perform(post("/api/food-sales-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodSalesReportDTO)))
            .andExpect(status().isBadRequest());

        List<FoodSalesReport> foodSalesReportList = foodSalesReportRepository.findAll();
        assertThat(foodSalesReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCostIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodSalesReportRepository.findAll().size();
        // set the field null
        foodSalesReport.setCost(null);

        // Create the FoodSalesReport, which fails.
        FoodSalesReportDTO foodSalesReportDTO = foodSalesReportMapper.toDto(foodSalesReport);

        restFoodSalesReportMockMvc.perform(post("/api/food-sales-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodSalesReportDTO)))
            .andExpect(status().isBadRequest());

        List<FoodSalesReport> foodSalesReportList = foodSalesReportRepository.findAll();
        assertThat(foodSalesReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodSalesReportRepository.findAll().size();
        // set the field null
        foodSalesReport.setPrice(null);

        // Create the FoodSalesReport, which fails.
        FoodSalesReportDTO foodSalesReportDTO = foodSalesReportMapper.toDto(foodSalesReport);

        restFoodSalesReportMockMvc.perform(post("/api/food-sales-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodSalesReportDTO)))
            .andExpect(status().isBadRequest());

        List<FoodSalesReport> foodSalesReportList = foodSalesReportRepository.findAll();
        assertThat(foodSalesReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFoodSalesReports() throws Exception {
        // Initialize the database
        foodSalesReportRepository.saveAndFlush(foodSalesReport);

        // Get all the foodSalesReportList
        restFoodSalesReportMockMvc.perform(get("/api/food-sales-reports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(foodSalesReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].day").value(hasItem(DEFAULT_DAY.toString())))
            .andExpect(jsonPath("$.[*].shopSN").value(hasItem(DEFAULT_SHOP_SN)))
            .andExpect(jsonPath("$.[*].shopName").value(hasItem(DEFAULT_SHOP_NAME)))
            .andExpect(jsonPath("$.[*].categorySN").value(hasItem(DEFAULT_CATEGORY_SN)))
            .andExpect(jsonPath("$.[*].categoryName").value(hasItem(DEFAULT_CATEGORY_NAME)))
            .andExpect(jsonPath("$.[*].foodSN").value(hasItem(DEFAULT_FOOD_SN)))
            .andExpect(jsonPath("$.[*].foodName").value(hasItem(DEFAULT_FOOD_NAME)))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE)))
            .andExpect(jsonPath("$.[*].originalPrice").value(hasItem(DEFAULT_ORIGINAL_PRICE)))
            .andExpect(jsonPath("$.[*].salesQuantity").value(hasItem(DEFAULT_SALES_QUANTITY)))
            .andExpect(jsonPath("$.[*].salesAmount").value(hasItem(DEFAULT_SALES_AMOUNT)))
            .andExpect(jsonPath("$.[*].refundOrders").value(hasItem(DEFAULT_REFUND_ORDERS)))
            .andExpect(jsonPath("$.[*].refundAmount").value(hasItem(DEFAULT_REFUND_AMOUNT)))
            .andExpect(jsonPath("$.[*].grossProfit").value(hasItem(DEFAULT_GROSS_PROFIT)))
            .andExpect(jsonPath("$.[*].initQuantity").value(hasItem(DEFAULT_INIT_QUANTITY)))
            .andExpect(jsonPath("$.[*].remainder").value(hasItem(DEFAULT_REMAINDER)));
    }
    
    @Test
    @Transactional
    public void getFoodSalesReport() throws Exception {
        // Initialize the database
        foodSalesReportRepository.saveAndFlush(foodSalesReport);

        // Get the foodSalesReport
        restFoodSalesReportMockMvc.perform(get("/api/food-sales-reports/{id}", foodSalesReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(foodSalesReport.getId().intValue()))
            .andExpect(jsonPath("$.day").value(DEFAULT_DAY.toString()))
            .andExpect(jsonPath("$.shopSN").value(DEFAULT_SHOP_SN))
            .andExpect(jsonPath("$.shopName").value(DEFAULT_SHOP_NAME))
            .andExpect(jsonPath("$.categorySN").value(DEFAULT_CATEGORY_SN))
            .andExpect(jsonPath("$.categoryName").value(DEFAULT_CATEGORY_NAME))
            .andExpect(jsonPath("$.foodSN").value(DEFAULT_FOOD_SN))
            .andExpect(jsonPath("$.foodName").value(DEFAULT_FOOD_NAME))
            .andExpect(jsonPath("$.cost").value(DEFAULT_COST))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE))
            .andExpect(jsonPath("$.originalPrice").value(DEFAULT_ORIGINAL_PRICE))
            .andExpect(jsonPath("$.salesQuantity").value(DEFAULT_SALES_QUANTITY))
            .andExpect(jsonPath("$.salesAmount").value(DEFAULT_SALES_AMOUNT))
            .andExpect(jsonPath("$.refundOrders").value(DEFAULT_REFUND_ORDERS))
            .andExpect(jsonPath("$.refundAmount").value(DEFAULT_REFUND_AMOUNT))
            .andExpect(jsonPath("$.grossProfit").value(DEFAULT_GROSS_PROFIT))
            .andExpect(jsonPath("$.initQuantity").value(DEFAULT_INIT_QUANTITY))
            .andExpect(jsonPath("$.remainder").value(DEFAULT_REMAINDER));
    }

    @Test
    @Transactional
    public void getNonExistingFoodSalesReport() throws Exception {
        // Get the foodSalesReport
        restFoodSalesReportMockMvc.perform(get("/api/food-sales-reports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFoodSalesReport() throws Exception {
        // Initialize the database
        foodSalesReportRepository.saveAndFlush(foodSalesReport);

        int databaseSizeBeforeUpdate = foodSalesReportRepository.findAll().size();

        // Update the foodSalesReport
        FoodSalesReport updatedFoodSalesReport = foodSalesReportRepository.findById(foodSalesReport.getId()).get();
        // Disconnect from session so that the updates on updatedFoodSalesReport are not directly saved in db
        em.detach(updatedFoodSalesReport);
        updatedFoodSalesReport
            .day(UPDATED_DAY)
            .shopSN(UPDATED_SHOP_SN)
            .shopName(UPDATED_SHOP_NAME)
            .categorySN(UPDATED_CATEGORY_SN)
            .categoryName(UPDATED_CATEGORY_NAME)
            .foodSN(UPDATED_FOOD_SN)
            .foodName(UPDATED_FOOD_NAME)
            .cost(UPDATED_COST)
            .price(UPDATED_PRICE)
            .originalPrice(UPDATED_ORIGINAL_PRICE)
            .salesQuantity(UPDATED_SALES_QUANTITY)
            .salesAmount(UPDATED_SALES_AMOUNT)
            .refundOrders(UPDATED_REFUND_ORDERS)
            .refundAmount(UPDATED_REFUND_AMOUNT)
            .grossProfit(UPDATED_GROSS_PROFIT)
            .initQuantity(UPDATED_INIT_QUANTITY)
            .remainder(UPDATED_REMAINDER);
        FoodSalesReportDTO foodSalesReportDTO = foodSalesReportMapper.toDto(updatedFoodSalesReport);

        restFoodSalesReportMockMvc.perform(put("/api/food-sales-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodSalesReportDTO)))
            .andExpect(status().isOk());

        // Validate the FoodSalesReport in the database
        List<FoodSalesReport> foodSalesReportList = foodSalesReportRepository.findAll();
        assertThat(foodSalesReportList).hasSize(databaseSizeBeforeUpdate);
        FoodSalesReport testFoodSalesReport = foodSalesReportList.get(foodSalesReportList.size() - 1);
        assertThat(testFoodSalesReport.getDay()).isEqualTo(UPDATED_DAY);
        assertThat(testFoodSalesReport.getShopSN()).isEqualTo(UPDATED_SHOP_SN);
        assertThat(testFoodSalesReport.getShopName()).isEqualTo(UPDATED_SHOP_NAME);
        assertThat(testFoodSalesReport.getCategorySN()).isEqualTo(UPDATED_CATEGORY_SN);
        assertThat(testFoodSalesReport.getCategoryName()).isEqualTo(UPDATED_CATEGORY_NAME);
        assertThat(testFoodSalesReport.getFoodSN()).isEqualTo(UPDATED_FOOD_SN);
        assertThat(testFoodSalesReport.getFoodName()).isEqualTo(UPDATED_FOOD_NAME);
        assertThat(testFoodSalesReport.getCost()).isEqualTo(UPDATED_COST);
        assertThat(testFoodSalesReport.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testFoodSalesReport.getOriginalPrice()).isEqualTo(UPDATED_ORIGINAL_PRICE);
        assertThat(testFoodSalesReport.getSalesQuantity()).isEqualTo(UPDATED_SALES_QUANTITY);
        assertThat(testFoodSalesReport.getSalesAmount()).isEqualTo(UPDATED_SALES_AMOUNT);
        assertThat(testFoodSalesReport.getRefundOrders()).isEqualTo(UPDATED_REFUND_ORDERS);
        assertThat(testFoodSalesReport.getRefundAmount()).isEqualTo(UPDATED_REFUND_AMOUNT);
        assertThat(testFoodSalesReport.getGrossProfit()).isEqualTo(UPDATED_GROSS_PROFIT);
        assertThat(testFoodSalesReport.getInitQuantity()).isEqualTo(UPDATED_INIT_QUANTITY);
        assertThat(testFoodSalesReport.getRemainder()).isEqualTo(UPDATED_REMAINDER);
    }

    @Test
    @Transactional
    public void updateNonExistingFoodSalesReport() throws Exception {
        int databaseSizeBeforeUpdate = foodSalesReportRepository.findAll().size();

        // Create the FoodSalesReport
        FoodSalesReportDTO foodSalesReportDTO = foodSalesReportMapper.toDto(foodSalesReport);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFoodSalesReportMockMvc.perform(put("/api/food-sales-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodSalesReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FoodSalesReport in the database
        List<FoodSalesReport> foodSalesReportList = foodSalesReportRepository.findAll();
        assertThat(foodSalesReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFoodSalesReport() throws Exception {
        // Initialize the database
        foodSalesReportRepository.saveAndFlush(foodSalesReport);

        int databaseSizeBeforeDelete = foodSalesReportRepository.findAll().size();

        // Delete the foodSalesReport
        restFoodSalesReportMockMvc.perform(delete("/api/food-sales-reports/{id}", foodSalesReport.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FoodSalesReport> foodSalesReportList = foodSalesReportRepository.findAll();
        assertThat(foodSalesReportList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
