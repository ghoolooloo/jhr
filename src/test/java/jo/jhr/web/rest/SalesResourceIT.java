package jo.jhr.web.rest;

import jo.jhr.RedisTestContainerExtension;
import jo.jhr.JhrApp;
import jo.jhr.domain.Sales;
import jo.jhr.repository.SalesRepository;
import jo.jhr.service.SalesService;
import jo.jhr.service.dto.SalesDTO;
import jo.jhr.service.mapper.SalesMapper;

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
 * Integration tests for the {@link SalesResource} REST controller.
 */
@SpringBootTest(classes = JhrApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class SalesResourceIT {

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

    private static final Integer DEFAULT_FOOD_ORIGINAL_PRICE = 0;
    private static final Integer UPDATED_FOOD_ORIGINAL_PRICE = 1;

    private static final Integer DEFAULT_INIT_QUANTITY = 0;
    private static final Integer UPDATED_INIT_QUANTITY = 1;

    private static final Integer DEFAULT_SALES_QUANTITY = 0;
    private static final Integer UPDATED_SALES_QUANTITY = 1;

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private SalesMapper salesMapper;

    @Autowired
    private SalesService salesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSalesMockMvc;

    private Sales sales;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sales createEntity(EntityManager em) {
        Sales sales = new Sales()
            .day(DEFAULT_DAY)
            .shopSN(DEFAULT_SHOP_SN)
            .shopName(DEFAULT_SHOP_NAME)
            .categorySN(DEFAULT_CATEGORY_SN)
            .categoryName(DEFAULT_CATEGORY_NAME)
            .foodSN(DEFAULT_FOOD_SN)
            .foodName(DEFAULT_FOOD_NAME)
            .foodOriginalPrice(DEFAULT_FOOD_ORIGINAL_PRICE)
            .initQuantity(DEFAULT_INIT_QUANTITY)
            .salesQuantity(DEFAULT_SALES_QUANTITY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return sales;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sales createUpdatedEntity(EntityManager em) {
        Sales sales = new Sales()
            .day(UPDATED_DAY)
            .shopSN(UPDATED_SHOP_SN)
            .shopName(UPDATED_SHOP_NAME)
            .categorySN(UPDATED_CATEGORY_SN)
            .categoryName(UPDATED_CATEGORY_NAME)
            .foodSN(UPDATED_FOOD_SN)
            .foodName(UPDATED_FOOD_NAME)
            .foodOriginalPrice(UPDATED_FOOD_ORIGINAL_PRICE)
            .initQuantity(UPDATED_INIT_QUANTITY)
            .salesQuantity(UPDATED_SALES_QUANTITY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return sales;
    }

    @BeforeEach
    public void initTest() {
        sales = createEntity(em);
    }

    @Test
    @Transactional
    public void createSales() throws Exception {
        int databaseSizeBeforeCreate = salesRepository.findAll().size();

        // Create the Sales
        SalesDTO salesDTO = salesMapper.toDto(sales);
        restSalesMockMvc.perform(post("/api/sales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesDTO)))
            .andExpect(status().isCreated());

        // Validate the Sales in the database
        List<Sales> salesList = salesRepository.findAll();
        assertThat(salesList).hasSize(databaseSizeBeforeCreate + 1);
        Sales testSales = salesList.get(salesList.size() - 1);
        assertThat(testSales.getDay()).isEqualTo(DEFAULT_DAY);
        assertThat(testSales.getShopSN()).isEqualTo(DEFAULT_SHOP_SN);
        assertThat(testSales.getShopName()).isEqualTo(DEFAULT_SHOP_NAME);
        assertThat(testSales.getCategorySN()).isEqualTo(DEFAULT_CATEGORY_SN);
        assertThat(testSales.getCategoryName()).isEqualTo(DEFAULT_CATEGORY_NAME);
        assertThat(testSales.getFoodSN()).isEqualTo(DEFAULT_FOOD_SN);
        assertThat(testSales.getFoodName()).isEqualTo(DEFAULT_FOOD_NAME);
        assertThat(testSales.getFoodOriginalPrice()).isEqualTo(DEFAULT_FOOD_ORIGINAL_PRICE);
        assertThat(testSales.getInitQuantity()).isEqualTo(DEFAULT_INIT_QUANTITY);
        assertThat(testSales.getSalesQuantity()).isEqualTo(DEFAULT_SALES_QUANTITY);
        assertThat(testSales.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createSalesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = salesRepository.findAll().size();

        // Create the Sales with an existing ID
        sales.setId(1L);
        SalesDTO salesDTO = salesMapper.toDto(sales);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesMockMvc.perform(post("/api/sales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sales in the database
        List<Sales> salesList = salesRepository.findAll();
        assertThat(salesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDayIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesRepository.findAll().size();
        // set the field null
        sales.setDay(null);

        // Create the Sales, which fails.
        SalesDTO salesDTO = salesMapper.toDto(sales);

        restSalesMockMvc.perform(post("/api/sales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesDTO)))
            .andExpect(status().isBadRequest());

        List<Sales> salesList = salesRepository.findAll();
        assertThat(salesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShopSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesRepository.findAll().size();
        // set the field null
        sales.setShopSN(null);

        // Create the Sales, which fails.
        SalesDTO salesDTO = salesMapper.toDto(sales);

        restSalesMockMvc.perform(post("/api/sales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesDTO)))
            .andExpect(status().isBadRequest());

        List<Sales> salesList = salesRepository.findAll();
        assertThat(salesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShopNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesRepository.findAll().size();
        // set the field null
        sales.setShopName(null);

        // Create the Sales, which fails.
        SalesDTO salesDTO = salesMapper.toDto(sales);

        restSalesMockMvc.perform(post("/api/sales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesDTO)))
            .andExpect(status().isBadRequest());

        List<Sales> salesList = salesRepository.findAll();
        assertThat(salesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategorySNIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesRepository.findAll().size();
        // set the field null
        sales.setCategorySN(null);

        // Create the Sales, which fails.
        SalesDTO salesDTO = salesMapper.toDto(sales);

        restSalesMockMvc.perform(post("/api/sales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesDTO)))
            .andExpect(status().isBadRequest());

        List<Sales> salesList = salesRepository.findAll();
        assertThat(salesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategoryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesRepository.findAll().size();
        // set the field null
        sales.setCategoryName(null);

        // Create the Sales, which fails.
        SalesDTO salesDTO = salesMapper.toDto(sales);

        restSalesMockMvc.perform(post("/api/sales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesDTO)))
            .andExpect(status().isBadRequest());

        List<Sales> salesList = salesRepository.findAll();
        assertThat(salesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFoodSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesRepository.findAll().size();
        // set the field null
        sales.setFoodSN(null);

        // Create the Sales, which fails.
        SalesDTO salesDTO = salesMapper.toDto(sales);

        restSalesMockMvc.perform(post("/api/sales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesDTO)))
            .andExpect(status().isBadRequest());

        List<Sales> salesList = salesRepository.findAll();
        assertThat(salesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFoodNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesRepository.findAll().size();
        // set the field null
        sales.setFoodName(null);

        // Create the Sales, which fails.
        SalesDTO salesDTO = salesMapper.toDto(sales);

        restSalesMockMvc.perform(post("/api/sales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesDTO)))
            .andExpect(status().isBadRequest());

        List<Sales> salesList = salesRepository.findAll();
        assertThat(salesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInitQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesRepository.findAll().size();
        // set the field null
        sales.setInitQuantity(null);

        // Create the Sales, which fails.
        SalesDTO salesDTO = salesMapper.toDto(sales);

        restSalesMockMvc.perform(post("/api/sales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesDTO)))
            .andExpect(status().isBadRequest());

        List<Sales> salesList = salesRepository.findAll();
        assertThat(salesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSalesQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesRepository.findAll().size();
        // set the field null
        sales.setSalesQuantity(null);

        // Create the Sales, which fails.
        SalesDTO salesDTO = salesMapper.toDto(sales);

        restSalesMockMvc.perform(post("/api/sales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesDTO)))
            .andExpect(status().isBadRequest());

        List<Sales> salesList = salesRepository.findAll();
        assertThat(salesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastModifiedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesRepository.findAll().size();
        // set the field null
        sales.setLastModifiedDate(null);

        // Create the Sales, which fails.
        SalesDTO salesDTO = salesMapper.toDto(sales);

        restSalesMockMvc.perform(post("/api/sales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesDTO)))
            .andExpect(status().isBadRequest());

        List<Sales> salesList = salesRepository.findAll();
        assertThat(salesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSales() throws Exception {
        // Initialize the database
        salesRepository.saveAndFlush(sales);

        // Get all the salesList
        restSalesMockMvc.perform(get("/api/sales?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sales.getId().intValue())))
            .andExpect(jsonPath("$.[*].day").value(hasItem(DEFAULT_DAY.toString())))
            .andExpect(jsonPath("$.[*].shopSN").value(hasItem(DEFAULT_SHOP_SN)))
            .andExpect(jsonPath("$.[*].shopName").value(hasItem(DEFAULT_SHOP_NAME)))
            .andExpect(jsonPath("$.[*].categorySN").value(hasItem(DEFAULT_CATEGORY_SN)))
            .andExpect(jsonPath("$.[*].categoryName").value(hasItem(DEFAULT_CATEGORY_NAME)))
            .andExpect(jsonPath("$.[*].foodSN").value(hasItem(DEFAULT_FOOD_SN)))
            .andExpect(jsonPath("$.[*].foodName").value(hasItem(DEFAULT_FOOD_NAME)))
            .andExpect(jsonPath("$.[*].foodOriginalPrice").value(hasItem(DEFAULT_FOOD_ORIGINAL_PRICE)))
            .andExpect(jsonPath("$.[*].initQuantity").value(hasItem(DEFAULT_INIT_QUANTITY)))
            .andExpect(jsonPath("$.[*].salesQuantity").value(hasItem(DEFAULT_SALES_QUANTITY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getSales() throws Exception {
        // Initialize the database
        salesRepository.saveAndFlush(sales);

        // Get the sales
        restSalesMockMvc.perform(get("/api/sales/{id}", sales.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sales.getId().intValue()))
            .andExpect(jsonPath("$.day").value(DEFAULT_DAY.toString()))
            .andExpect(jsonPath("$.shopSN").value(DEFAULT_SHOP_SN))
            .andExpect(jsonPath("$.shopName").value(DEFAULT_SHOP_NAME))
            .andExpect(jsonPath("$.categorySN").value(DEFAULT_CATEGORY_SN))
            .andExpect(jsonPath("$.categoryName").value(DEFAULT_CATEGORY_NAME))
            .andExpect(jsonPath("$.foodSN").value(DEFAULT_FOOD_SN))
            .andExpect(jsonPath("$.foodName").value(DEFAULT_FOOD_NAME))
            .andExpect(jsonPath("$.foodOriginalPrice").value(DEFAULT_FOOD_ORIGINAL_PRICE))
            .andExpect(jsonPath("$.initQuantity").value(DEFAULT_INIT_QUANTITY))
            .andExpect(jsonPath("$.salesQuantity").value(DEFAULT_SALES_QUANTITY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSales() throws Exception {
        // Get the sales
        restSalesMockMvc.perform(get("/api/sales/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSales() throws Exception {
        // Initialize the database
        salesRepository.saveAndFlush(sales);

        int databaseSizeBeforeUpdate = salesRepository.findAll().size();

        // Update the sales
        Sales updatedSales = salesRepository.findById(sales.getId()).get();
        // Disconnect from session so that the updates on updatedSales are not directly saved in db
        em.detach(updatedSales);
        updatedSales
            .day(UPDATED_DAY)
            .shopSN(UPDATED_SHOP_SN)
            .shopName(UPDATED_SHOP_NAME)
            .categorySN(UPDATED_CATEGORY_SN)
            .categoryName(UPDATED_CATEGORY_NAME)
            .foodSN(UPDATED_FOOD_SN)
            .foodName(UPDATED_FOOD_NAME)
            .foodOriginalPrice(UPDATED_FOOD_ORIGINAL_PRICE)
            .initQuantity(UPDATED_INIT_QUANTITY)
            .salesQuantity(UPDATED_SALES_QUANTITY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        SalesDTO salesDTO = salesMapper.toDto(updatedSales);

        restSalesMockMvc.perform(put("/api/sales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesDTO)))
            .andExpect(status().isOk());

        // Validate the Sales in the database
        List<Sales> salesList = salesRepository.findAll();
        assertThat(salesList).hasSize(databaseSizeBeforeUpdate);
        Sales testSales = salesList.get(salesList.size() - 1);
        assertThat(testSales.getDay()).isEqualTo(UPDATED_DAY);
        assertThat(testSales.getShopSN()).isEqualTo(UPDATED_SHOP_SN);
        assertThat(testSales.getShopName()).isEqualTo(UPDATED_SHOP_NAME);
        assertThat(testSales.getCategorySN()).isEqualTo(UPDATED_CATEGORY_SN);
        assertThat(testSales.getCategoryName()).isEqualTo(UPDATED_CATEGORY_NAME);
        assertThat(testSales.getFoodSN()).isEqualTo(UPDATED_FOOD_SN);
        assertThat(testSales.getFoodName()).isEqualTo(UPDATED_FOOD_NAME);
        assertThat(testSales.getFoodOriginalPrice()).isEqualTo(UPDATED_FOOD_ORIGINAL_PRICE);
        assertThat(testSales.getInitQuantity()).isEqualTo(UPDATED_INIT_QUANTITY);
        assertThat(testSales.getSalesQuantity()).isEqualTo(UPDATED_SALES_QUANTITY);
        assertThat(testSales.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingSales() throws Exception {
        int databaseSizeBeforeUpdate = salesRepository.findAll().size();

        // Create the Sales
        SalesDTO salesDTO = salesMapper.toDto(sales);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesMockMvc.perform(put("/api/sales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sales in the database
        List<Sales> salesList = salesRepository.findAll();
        assertThat(salesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSales() throws Exception {
        // Initialize the database
        salesRepository.saveAndFlush(sales);

        int databaseSizeBeforeDelete = salesRepository.findAll().size();

        // Delete the sales
        restSalesMockMvc.perform(delete("/api/sales/{id}", sales.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Sales> salesList = salesRepository.findAll();
        assertThat(salesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
