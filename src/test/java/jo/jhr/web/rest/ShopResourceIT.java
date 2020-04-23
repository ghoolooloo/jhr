package jo.jhr.web.rest;

import jo.jhr.RedisTestContainerExtension;
import jo.jhr.JhrApp;
import jo.jhr.domain.Shop;
import jo.jhr.repository.ShopRepository;
import jo.jhr.service.ShopService;
import jo.jhr.service.dto.ShopDTO;
import jo.jhr.service.mapper.ShopMapper;

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

import jo.jhr.domain.enumeration.OffsetType;
/**
 * Integration tests for the {@link ShopResource} REST controller.
 */
@SpringBootTest(classes = JhrApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class ShopResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SN = "AAAAAAAAAA";
    private static final String UPDATED_SN = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT = "BBBBBBBBBB";

    private static final OffsetType DEFAULT_OFFSET_TYPE = OffsetType.MARS;
    private static final OffsetType UPDATED_OFFSET_TYPE = OffsetType.SOGOU;

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    private static final Double DEFAULT_LATITUDE = 1D;
    private static final Double UPDATED_LATITUDE = 2D;

    private static final String DEFAULT_SHOP_OPEN = "AAAAAAAA";
    private static final String UPDATED_SHOP_OPEN = "BBBBBBBB";

    private static final String DEFAULT_SHOP_CLOSE = "AAAAAAAA";
    private static final String UPDATED_SHOP_CLOSE = "BBBBBBBB";

    private static final Integer DEFAULT_MAX_DELIVERY_DISTANCE = 0;
    private static final Integer UPDATED_MAX_DELIVERY_DISTANCE = 1;

    private static final Integer DEFAULT_MIN_DELIVERY_AMOUNT = 0;
    private static final Integer UPDATED_MIN_DELIVERY_AMOUNT = 1;

    private static final String DEFAULT_LUNCH_SERVE_STARTING_AT = "AAAAAAAA";
    private static final String UPDATED_LUNCH_SERVE_STARTING_AT = "BBBBBBBB";

    private static final String DEFAULT_LUNCH_SERVE_END_AT = "AAAAAAAA";
    private static final String UPDATED_LUNCH_SERVE_END_AT = "BBBBBBBB";

    private static final String DEFAULT_SUPPER_SERVE_STARTING_AT = "AAAAAAAA";
    private static final String UPDATED_SUPPER_SERVE_STARTING_AT = "BBBBBBBB";

    private static final String DEFAULT_SUPPER_SERVE_END_AT = "AAAAAAAA";
    private static final String UPDATED_SUPPER_SERVE_END_AT = "BBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private ShopService shopService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restShopMockMvc;

    private Shop shop;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Shop createEntity(EntityManager em) {
        Shop shop = new Shop()
            .name(DEFAULT_NAME)
            .sn(DEFAULT_SN)
            .tel(DEFAULT_TEL)
            .address(DEFAULT_ADDRESS)
            .country(DEFAULT_COUNTRY)
            .province(DEFAULT_PROVINCE)
            .city(DEFAULT_CITY)
            .district(DEFAULT_DISTRICT)
            .offsetType(DEFAULT_OFFSET_TYPE)
            .longitude(DEFAULT_LONGITUDE)
            .latitude(DEFAULT_LATITUDE)
            .shopOpen(DEFAULT_SHOP_OPEN)
            .shopClose(DEFAULT_SHOP_CLOSE)
            .maxDeliveryDistance(DEFAULT_MAX_DELIVERY_DISTANCE)
            .minDeliveryAmount(DEFAULT_MIN_DELIVERY_AMOUNT)
            .lunchServeStartingAt(DEFAULT_LUNCH_SERVE_STARTING_AT)
            .lunchServeEndAt(DEFAULT_LUNCH_SERVE_END_AT)
            .supperServeStartingAt(DEFAULT_SUPPER_SERVE_STARTING_AT)
            .supperServeEndAt(DEFAULT_SUPPER_SERVE_END_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
        return shop;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Shop createUpdatedEntity(EntityManager em) {
        Shop shop = new Shop()
            .name(UPDATED_NAME)
            .sn(UPDATED_SN)
            .tel(UPDATED_TEL)
            .address(UPDATED_ADDRESS)
            .country(UPDATED_COUNTRY)
            .province(UPDATED_PROVINCE)
            .city(UPDATED_CITY)
            .district(UPDATED_DISTRICT)
            .offsetType(UPDATED_OFFSET_TYPE)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE)
            .shopOpen(UPDATED_SHOP_OPEN)
            .shopClose(UPDATED_SHOP_CLOSE)
            .maxDeliveryDistance(UPDATED_MAX_DELIVERY_DISTANCE)
            .minDeliveryAmount(UPDATED_MIN_DELIVERY_AMOUNT)
            .lunchServeStartingAt(UPDATED_LUNCH_SERVE_STARTING_AT)
            .lunchServeEndAt(UPDATED_LUNCH_SERVE_END_AT)
            .supperServeStartingAt(UPDATED_SUPPER_SERVE_STARTING_AT)
            .supperServeEndAt(UPDATED_SUPPER_SERVE_END_AT)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        return shop;
    }

    @BeforeEach
    public void initTest() {
        shop = createEntity(em);
    }

    @Test
    @Transactional
    public void createShop() throws Exception {
        int databaseSizeBeforeCreate = shopRepository.findAll().size();

        // Create the Shop
        ShopDTO shopDTO = shopMapper.toDto(shop);
        restShopMockMvc.perform(post("/api/shops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopDTO)))
            .andExpect(status().isCreated());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeCreate + 1);
        Shop testShop = shopList.get(shopList.size() - 1);
        assertThat(testShop.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testShop.getSn()).isEqualTo(DEFAULT_SN);
        assertThat(testShop.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testShop.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testShop.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testShop.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testShop.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testShop.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testShop.getOffsetType()).isEqualTo(DEFAULT_OFFSET_TYPE);
        assertThat(testShop.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testShop.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testShop.getShopOpen()).isEqualTo(DEFAULT_SHOP_OPEN);
        assertThat(testShop.getShopClose()).isEqualTo(DEFAULT_SHOP_CLOSE);
        assertThat(testShop.getMaxDeliveryDistance()).isEqualTo(DEFAULT_MAX_DELIVERY_DISTANCE);
        assertThat(testShop.getMinDeliveryAmount()).isEqualTo(DEFAULT_MIN_DELIVERY_AMOUNT);
        assertThat(testShop.getLunchServeStartingAt()).isEqualTo(DEFAULT_LUNCH_SERVE_STARTING_AT);
        assertThat(testShop.getLunchServeEndAt()).isEqualTo(DEFAULT_LUNCH_SERVE_END_AT);
        assertThat(testShop.getSupperServeStartingAt()).isEqualTo(DEFAULT_SUPPER_SERVE_STARTING_AT);
        assertThat(testShop.getSupperServeEndAt()).isEqualTo(DEFAULT_SUPPER_SERVE_END_AT);
        assertThat(testShop.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testShop.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testShop.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testShop.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void createShopWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shopRepository.findAll().size();

        // Create the Shop with an existing ID
        shop.setId(1L);
        ShopDTO shopDTO = shopMapper.toDto(shop);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShopMockMvc.perform(post("/api/shops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = shopRepository.findAll().size();
        // set the field null
        shop.setName(null);

        // Create the Shop, which fails.
        ShopDTO shopDTO = shopMapper.toDto(shop);

        restShopMockMvc.perform(post("/api/shops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopDTO)))
            .andExpect(status().isBadRequest());

        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSnIsRequired() throws Exception {
        int databaseSizeBeforeTest = shopRepository.findAll().size();
        // set the field null
        shop.setSn(null);

        // Create the Shop, which fails.
        ShopDTO shopDTO = shopMapper.toDto(shop);

        restShopMockMvc.perform(post("/api/shops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopDTO)))
            .andExpect(status().isBadRequest());

        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProvinceIsRequired() throws Exception {
        int databaseSizeBeforeTest = shopRepository.findAll().size();
        // set the field null
        shop.setProvince(null);

        // Create the Shop, which fails.
        ShopDTO shopDTO = shopMapper.toDto(shop);

        restShopMockMvc.perform(post("/api/shops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopDTO)))
            .andExpect(status().isBadRequest());

        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = shopRepository.findAll().size();
        // set the field null
        shop.setCity(null);

        // Create the Shop, which fails.
        ShopDTO shopDTO = shopMapper.toDto(shop);

        restShopMockMvc.perform(post("/api/shops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopDTO)))
            .andExpect(status().isBadRequest());

        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOffsetTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = shopRepository.findAll().size();
        // set the field null
        shop.setOffsetType(null);

        // Create the Shop, which fails.
        ShopDTO shopDTO = shopMapper.toDto(shop);

        restShopMockMvc.perform(post("/api/shops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopDTO)))
            .andExpect(status().isBadRequest());

        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = shopRepository.findAll().size();
        // set the field null
        shop.setLongitude(null);

        // Create the Shop, which fails.
        ShopDTO shopDTO = shopMapper.toDto(shop);

        restShopMockMvc.perform(post("/api/shops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopDTO)))
            .andExpect(status().isBadRequest());

        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLatitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = shopRepository.findAll().size();
        // set the field null
        shop.setLatitude(null);

        // Create the Shop, which fails.
        ShopDTO shopDTO = shopMapper.toDto(shop);

        restShopMockMvc.perform(post("/api/shops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopDTO)))
            .andExpect(status().isBadRequest());

        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = shopRepository.findAll().size();
        // set the field null
        shop.setCreatedBy(null);

        // Create the Shop, which fails.
        ShopDTO shopDTO = shopMapper.toDto(shop);

        restShopMockMvc.perform(post("/api/shops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopDTO)))
            .andExpect(status().isBadRequest());

        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = shopRepository.findAll().size();
        // set the field null
        shop.setCreatedDate(null);

        // Create the Shop, which fails.
        ShopDTO shopDTO = shopMapper.toDto(shop);

        restShopMockMvc.perform(post("/api/shops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopDTO)))
            .andExpect(status().isBadRequest());

        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllShops() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList
        restShopMockMvc.perform(get("/api/shops?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shop.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].sn").value(hasItem(DEFAULT_SN)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].offsetType").value(hasItem(DEFAULT_OFFSET_TYPE.toString())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].shopOpen").value(hasItem(DEFAULT_SHOP_OPEN)))
            .andExpect(jsonPath("$.[*].shopClose").value(hasItem(DEFAULT_SHOP_CLOSE)))
            .andExpect(jsonPath("$.[*].maxDeliveryDistance").value(hasItem(DEFAULT_MAX_DELIVERY_DISTANCE)))
            .andExpect(jsonPath("$.[*].minDeliveryAmount").value(hasItem(DEFAULT_MIN_DELIVERY_AMOUNT)))
            .andExpect(jsonPath("$.[*].lunchServeStartingAt").value(hasItem(DEFAULT_LUNCH_SERVE_STARTING_AT)))
            .andExpect(jsonPath("$.[*].lunchServeEndAt").value(hasItem(DEFAULT_LUNCH_SERVE_END_AT)))
            .andExpect(jsonPath("$.[*].supperServeStartingAt").value(hasItem(DEFAULT_SUPPER_SERVE_STARTING_AT)))
            .andExpect(jsonPath("$.[*].supperServeEndAt").value(hasItem(DEFAULT_SUPPER_SERVE_END_AT)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));
    }
    
    @Test
    @Transactional
    public void getShop() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get the shop
        restShopMockMvc.perform(get("/api/shops/{id}", shop.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(shop.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.sn").value(DEFAULT_SN))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.province").value(DEFAULT_PROVINCE))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT))
            .andExpect(jsonPath("$.offsetType").value(DEFAULT_OFFSET_TYPE.toString()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.shopOpen").value(DEFAULT_SHOP_OPEN))
            .andExpect(jsonPath("$.shopClose").value(DEFAULT_SHOP_CLOSE))
            .andExpect(jsonPath("$.maxDeliveryDistance").value(DEFAULT_MAX_DELIVERY_DISTANCE))
            .andExpect(jsonPath("$.minDeliveryAmount").value(DEFAULT_MIN_DELIVERY_AMOUNT))
            .andExpect(jsonPath("$.lunchServeStartingAt").value(DEFAULT_LUNCH_SERVE_STARTING_AT))
            .andExpect(jsonPath("$.lunchServeEndAt").value(DEFAULT_LUNCH_SERVE_END_AT))
            .andExpect(jsonPath("$.supperServeStartingAt").value(DEFAULT_SUPPER_SERVE_STARTING_AT))
            .andExpect(jsonPath("$.supperServeEndAt").value(DEFAULT_SUPPER_SERVE_END_AT))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY));
    }

    @Test
    @Transactional
    public void getNonExistingShop() throws Exception {
        // Get the shop
        restShopMockMvc.perform(get("/api/shops/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShop() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        int databaseSizeBeforeUpdate = shopRepository.findAll().size();

        // Update the shop
        Shop updatedShop = shopRepository.findById(shop.getId()).get();
        // Disconnect from session so that the updates on updatedShop are not directly saved in db
        em.detach(updatedShop);
        updatedShop
            .name(UPDATED_NAME)
            .sn(UPDATED_SN)
            .tel(UPDATED_TEL)
            .address(UPDATED_ADDRESS)
            .country(UPDATED_COUNTRY)
            .province(UPDATED_PROVINCE)
            .city(UPDATED_CITY)
            .district(UPDATED_DISTRICT)
            .offsetType(UPDATED_OFFSET_TYPE)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE)
            .shopOpen(UPDATED_SHOP_OPEN)
            .shopClose(UPDATED_SHOP_CLOSE)
            .maxDeliveryDistance(UPDATED_MAX_DELIVERY_DISTANCE)
            .minDeliveryAmount(UPDATED_MIN_DELIVERY_AMOUNT)
            .lunchServeStartingAt(UPDATED_LUNCH_SERVE_STARTING_AT)
            .lunchServeEndAt(UPDATED_LUNCH_SERVE_END_AT)
            .supperServeStartingAt(UPDATED_SUPPER_SERVE_STARTING_AT)
            .supperServeEndAt(UPDATED_SUPPER_SERVE_END_AT)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        ShopDTO shopDTO = shopMapper.toDto(updatedShop);

        restShopMockMvc.perform(put("/api/shops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopDTO)))
            .andExpect(status().isOk());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
        Shop testShop = shopList.get(shopList.size() - 1);
        assertThat(testShop.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testShop.getSn()).isEqualTo(UPDATED_SN);
        assertThat(testShop.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testShop.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testShop.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testShop.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testShop.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testShop.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testShop.getOffsetType()).isEqualTo(UPDATED_OFFSET_TYPE);
        assertThat(testShop.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testShop.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testShop.getShopOpen()).isEqualTo(UPDATED_SHOP_OPEN);
        assertThat(testShop.getShopClose()).isEqualTo(UPDATED_SHOP_CLOSE);
        assertThat(testShop.getMaxDeliveryDistance()).isEqualTo(UPDATED_MAX_DELIVERY_DISTANCE);
        assertThat(testShop.getMinDeliveryAmount()).isEqualTo(UPDATED_MIN_DELIVERY_AMOUNT);
        assertThat(testShop.getLunchServeStartingAt()).isEqualTo(UPDATED_LUNCH_SERVE_STARTING_AT);
        assertThat(testShop.getLunchServeEndAt()).isEqualTo(UPDATED_LUNCH_SERVE_END_AT);
        assertThat(testShop.getSupperServeStartingAt()).isEqualTo(UPDATED_SUPPER_SERVE_STARTING_AT);
        assertThat(testShop.getSupperServeEndAt()).isEqualTo(UPDATED_SUPPER_SERVE_END_AT);
        assertThat(testShop.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testShop.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testShop.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testShop.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingShop() throws Exception {
        int databaseSizeBeforeUpdate = shopRepository.findAll().size();

        // Create the Shop
        ShopDTO shopDTO = shopMapper.toDto(shop);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShopMockMvc.perform(put("/api/shops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShop() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        int databaseSizeBeforeDelete = shopRepository.findAll().size();

        // Delete the shop
        restShopMockMvc.perform(delete("/api/shops/{id}", shop.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
