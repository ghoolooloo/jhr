package jo.jhr.web.rest;

import jo.jhr.RedisTestContainerExtension;
import jo.jhr.JhrApp;
import jo.jhr.domain.DeliveryOrder;
import jo.jhr.repository.DeliveryOrderRepository;
import jo.jhr.service.DeliveryOrderService;
import jo.jhr.service.dto.DeliveryOrderDTO;
import jo.jhr.service.mapper.DeliveryOrderMapper;

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

import jo.jhr.domain.enumeration.OrderType;
import jo.jhr.domain.enumeration.DistributionPlatform;
import jo.jhr.domain.enumeration.Sex;
import jo.jhr.domain.enumeration.OffsetType;
import jo.jhr.domain.enumeration.PaymentMethod;
import jo.jhr.domain.enumeration.OrderStatus;
/**
 * Integration tests for the {@link DeliveryOrderResource} REST controller.
 */
@SpringBootTest(classes = JhrApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class DeliveryOrderResourceIT {

    private static final String DEFAULT_SN = "AAAAAAAAAA";
    private static final String UPDATED_SN = "BBBBBBBBBB";

    private static final String DEFAULT_MEMBER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MEMBER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MEMBER_SN = "AAAAAAAAAA";
    private static final String UPDATED_MEMBER_SN = "BBBBBBBBBB";

    private static final OrderType DEFAULT_ORDER_TYPE = OrderType.TOGO;
    private static final OrderType UPDATED_ORDER_TYPE = OrderType.PACK;

    private static final String DEFAULT_SHOP_SN = "AAAAAAAAAA";
    private static final String UPDATED_SHOP_SN = "BBBBBBBBBB";

    private static final String DEFAULT_SHOP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHOP_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRICE_TOTAL = 0;
    private static final Integer UPDATED_PRICE_TOTAL = 1;

    private static final Integer DEFAULT_CARD_REDUCE = 1;
    private static final Integer UPDATED_CARD_REDUCE = 2;

    private static final String DEFAULT_CARDS = "AAAAAAAAAA";
    private static final String UPDATED_CARDS = "BBBBBBBBBB";

    private static final Integer DEFAULT_REWARD_POINTS_REDUCE = 1;
    private static final Integer UPDATED_REWARD_POINTS_REDUCE = 2;

    private static final Integer DEFAULT_PAYMENT_TOTAL = 0;
    private static final Integer UPDATED_PAYMENT_TOTAL = 1;

    private static final DistributionPlatform DEFAULT_DISTRIBUTION_PLATFORM = DistributionPlatform.MERCHANT;
    private static final DistributionPlatform UPDATED_DISTRIBUTION_PLATFORM = DistributionPlatform.DADA;

    private static final String DEFAULT_DELIVERY_NO = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_NO = "BBBBBBBBBB";

    private static final Integer DEFAULT_DELIVERY_STATUS = 1;
    private static final Integer UPDATED_DELIVERY_STATUS = 2;

    private static final String DEFAULT_DELIVERY_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERIER = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERIER = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERIER_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERIER_MOBILE = "BBBBBBBBBB";

    private static final Integer DEFAULT_DELIVERY_DEDUCT_FEE = 1;
    private static final Integer UPDATED_DELIVERY_DEDUCT_FEE = 2;

    private static final Integer DEFAULT_DELIVERY_FEE = 1;
    private static final Integer UPDATED_DELIVERY_FEE = 2;

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    private static final Sex DEFAULT_SEX = Sex.UNKNOWN;
    private static final Sex UPDATED_SEX = Sex.MAN;

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

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

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Integer DEFAULT_PACKING_FEE = 1;
    private static final Integer UPDATED_PACKING_FEE = 2;

    private static final PaymentMethod DEFAULT_PAYMENT_MODE = PaymentMethod.WX;
    private static final PaymentMethod UPDATED_PAYMENT_MODE = PaymentMethod.ALIPAY;

    private static final Instant DEFAULT_DINING_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DINING_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final OrderStatus DEFAULT_STATUS = OrderStatus.NEW;
    private static final OrderStatus UPDATED_STATUS = OrderStatus.PAID;

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_PAID_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PAID_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_COMPLETED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_COMPLETED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private DeliveryOrderRepository deliveryOrderRepository;

    @Autowired
    private DeliveryOrderMapper deliveryOrderMapper;

    @Autowired
    private DeliveryOrderService deliveryOrderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDeliveryOrderMockMvc;

    private DeliveryOrder deliveryOrder;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryOrder createEntity(EntityManager em) {
        DeliveryOrder deliveryOrder = new DeliveryOrder()
            .sn(DEFAULT_SN)
            .memberName(DEFAULT_MEMBER_NAME)
            .memberSN(DEFAULT_MEMBER_SN)
            .orderType(DEFAULT_ORDER_TYPE)
            .shopSN(DEFAULT_SHOP_SN)
            .shopName(DEFAULT_SHOP_NAME)
            .priceTotal(DEFAULT_PRICE_TOTAL)
            .cardReduce(DEFAULT_CARD_REDUCE)
            .cards(DEFAULT_CARDS)
            .rewardPointsReduce(DEFAULT_REWARD_POINTS_REDUCE)
            .paymentTotal(DEFAULT_PAYMENT_TOTAL)
            .distributionPlatform(DEFAULT_DISTRIBUTION_PLATFORM)
            .deliveryNo(DEFAULT_DELIVERY_NO)
            .deliveryStatus(DEFAULT_DELIVERY_STATUS)
            .deliveryDesc(DEFAULT_DELIVERY_DESC)
            .deliverier(DEFAULT_DELIVERIER)
            .deliverierMobile(DEFAULT_DELIVERIER_MOBILE)
            .deliveryDeductFee(DEFAULT_DELIVERY_DEDUCT_FEE)
            .deliveryFee(DEFAULT_DELIVERY_FEE)
            .contact(DEFAULT_CONTACT)
            .sex(DEFAULT_SEX)
            .mobile(DEFAULT_MOBILE)
            .country(DEFAULT_COUNTRY)
            .province(DEFAULT_PROVINCE)
            .city(DEFAULT_CITY)
            .district(DEFAULT_DISTRICT)
            .offsetType(DEFAULT_OFFSET_TYPE)
            .longitude(DEFAULT_LONGITUDE)
            .latitude(DEFAULT_LATITUDE)
            .address(DEFAULT_ADDRESS)
            .packingFee(DEFAULT_PACKING_FEE)
            .paymentMode(DEFAULT_PAYMENT_MODE)
            .diningDate(DEFAULT_DINING_DATE)
            .remark(DEFAULT_REMARK)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .paidDate(DEFAULT_PAID_DATE)
            .completedDate(DEFAULT_COMPLETED_DATE)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return deliveryOrder;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryOrder createUpdatedEntity(EntityManager em) {
        DeliveryOrder deliveryOrder = new DeliveryOrder()
            .sn(UPDATED_SN)
            .memberName(UPDATED_MEMBER_NAME)
            .memberSN(UPDATED_MEMBER_SN)
            .orderType(UPDATED_ORDER_TYPE)
            .shopSN(UPDATED_SHOP_SN)
            .shopName(UPDATED_SHOP_NAME)
            .priceTotal(UPDATED_PRICE_TOTAL)
            .cardReduce(UPDATED_CARD_REDUCE)
            .cards(UPDATED_CARDS)
            .rewardPointsReduce(UPDATED_REWARD_POINTS_REDUCE)
            .paymentTotal(UPDATED_PAYMENT_TOTAL)
            .distributionPlatform(UPDATED_DISTRIBUTION_PLATFORM)
            .deliveryNo(UPDATED_DELIVERY_NO)
            .deliveryStatus(UPDATED_DELIVERY_STATUS)
            .deliveryDesc(UPDATED_DELIVERY_DESC)
            .deliverier(UPDATED_DELIVERIER)
            .deliverierMobile(UPDATED_DELIVERIER_MOBILE)
            .deliveryDeductFee(UPDATED_DELIVERY_DEDUCT_FEE)
            .deliveryFee(UPDATED_DELIVERY_FEE)
            .contact(UPDATED_CONTACT)
            .sex(UPDATED_SEX)
            .mobile(UPDATED_MOBILE)
            .country(UPDATED_COUNTRY)
            .province(UPDATED_PROVINCE)
            .city(UPDATED_CITY)
            .district(UPDATED_DISTRICT)
            .offsetType(UPDATED_OFFSET_TYPE)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE)
            .address(UPDATED_ADDRESS)
            .packingFee(UPDATED_PACKING_FEE)
            .paymentMode(UPDATED_PAYMENT_MODE)
            .diningDate(UPDATED_DINING_DATE)
            .remark(UPDATED_REMARK)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .paidDate(UPDATED_PAID_DATE)
            .completedDate(UPDATED_COMPLETED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return deliveryOrder;
    }

    @BeforeEach
    public void initTest() {
        deliveryOrder = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeliveryOrder() throws Exception {
        int databaseSizeBeforeCreate = deliveryOrderRepository.findAll().size();

        // Create the DeliveryOrder
        DeliveryOrderDTO deliveryOrderDTO = deliveryOrderMapper.toDto(deliveryOrder);
        restDeliveryOrderMockMvc.perform(post("/api/delivery-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOrderDTO)))
            .andExpect(status().isCreated());

        // Validate the DeliveryOrder in the database
        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository.findAll();
        assertThat(deliveryOrderList).hasSize(databaseSizeBeforeCreate + 1);
        DeliveryOrder testDeliveryOrder = deliveryOrderList.get(deliveryOrderList.size() - 1);
        assertThat(testDeliveryOrder.getSn()).isEqualTo(DEFAULT_SN);
        assertThat(testDeliveryOrder.getMemberName()).isEqualTo(DEFAULT_MEMBER_NAME);
        assertThat(testDeliveryOrder.getMemberSN()).isEqualTo(DEFAULT_MEMBER_SN);
        assertThat(testDeliveryOrder.getOrderType()).isEqualTo(DEFAULT_ORDER_TYPE);
        assertThat(testDeliveryOrder.getShopSN()).isEqualTo(DEFAULT_SHOP_SN);
        assertThat(testDeliveryOrder.getShopName()).isEqualTo(DEFAULT_SHOP_NAME);
        assertThat(testDeliveryOrder.getPriceTotal()).isEqualTo(DEFAULT_PRICE_TOTAL);
        assertThat(testDeliveryOrder.getCardReduce()).isEqualTo(DEFAULT_CARD_REDUCE);
        assertThat(testDeliveryOrder.getCards()).isEqualTo(DEFAULT_CARDS);
        assertThat(testDeliveryOrder.getRewardPointsReduce()).isEqualTo(DEFAULT_REWARD_POINTS_REDUCE);
        assertThat(testDeliveryOrder.getPaymentTotal()).isEqualTo(DEFAULT_PAYMENT_TOTAL);
        assertThat(testDeliveryOrder.getDistributionPlatform()).isEqualTo(DEFAULT_DISTRIBUTION_PLATFORM);
        assertThat(testDeliveryOrder.getDeliveryNo()).isEqualTo(DEFAULT_DELIVERY_NO);
        assertThat(testDeliveryOrder.getDeliveryStatus()).isEqualTo(DEFAULT_DELIVERY_STATUS);
        assertThat(testDeliveryOrder.getDeliveryDesc()).isEqualTo(DEFAULT_DELIVERY_DESC);
        assertThat(testDeliveryOrder.getDeliverier()).isEqualTo(DEFAULT_DELIVERIER);
        assertThat(testDeliveryOrder.getDeliverierMobile()).isEqualTo(DEFAULT_DELIVERIER_MOBILE);
        assertThat(testDeliveryOrder.getDeliveryDeductFee()).isEqualTo(DEFAULT_DELIVERY_DEDUCT_FEE);
        assertThat(testDeliveryOrder.getDeliveryFee()).isEqualTo(DEFAULT_DELIVERY_FEE);
        assertThat(testDeliveryOrder.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testDeliveryOrder.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testDeliveryOrder.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testDeliveryOrder.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testDeliveryOrder.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testDeliveryOrder.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testDeliveryOrder.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testDeliveryOrder.getOffsetType()).isEqualTo(DEFAULT_OFFSET_TYPE);
        assertThat(testDeliveryOrder.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testDeliveryOrder.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testDeliveryOrder.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testDeliveryOrder.getPackingFee()).isEqualTo(DEFAULT_PACKING_FEE);
        assertThat(testDeliveryOrder.getPaymentMode()).isEqualTo(DEFAULT_PAYMENT_MODE);
        assertThat(testDeliveryOrder.getDiningDate()).isEqualTo(DEFAULT_DINING_DATE);
        assertThat(testDeliveryOrder.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testDeliveryOrder.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDeliveryOrder.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDeliveryOrder.getPaidDate()).isEqualTo(DEFAULT_PAID_DATE);
        assertThat(testDeliveryOrder.getCompletedDate()).isEqualTo(DEFAULT_COMPLETED_DATE);
        assertThat(testDeliveryOrder.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createDeliveryOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deliveryOrderRepository.findAll().size();

        // Create the DeliveryOrder with an existing ID
        deliveryOrder.setId(1L);
        DeliveryOrderDTO deliveryOrderDTO = deliveryOrderMapper.toDto(deliveryOrder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliveryOrderMockMvc.perform(post("/api/delivery-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeliveryOrder in the database
        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository.findAll();
        assertThat(deliveryOrderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSnIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryOrderRepository.findAll().size();
        // set the field null
        deliveryOrder.setSn(null);

        // Create the DeliveryOrder, which fails.
        DeliveryOrderDTO deliveryOrderDTO = deliveryOrderMapper.toDto(deliveryOrder);

        restDeliveryOrderMockMvc.perform(post("/api/delivery-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOrderDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository.findAll();
        assertThat(deliveryOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMemberNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryOrderRepository.findAll().size();
        // set the field null
        deliveryOrder.setMemberName(null);

        // Create the DeliveryOrder, which fails.
        DeliveryOrderDTO deliveryOrderDTO = deliveryOrderMapper.toDto(deliveryOrder);

        restDeliveryOrderMockMvc.perform(post("/api/delivery-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOrderDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository.findAll();
        assertThat(deliveryOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMemberSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryOrderRepository.findAll().size();
        // set the field null
        deliveryOrder.setMemberSN(null);

        // Create the DeliveryOrder, which fails.
        DeliveryOrderDTO deliveryOrderDTO = deliveryOrderMapper.toDto(deliveryOrder);

        restDeliveryOrderMockMvc.perform(post("/api/delivery-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOrderDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository.findAll();
        assertThat(deliveryOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryOrderRepository.findAll().size();
        // set the field null
        deliveryOrder.setOrderType(null);

        // Create the DeliveryOrder, which fails.
        DeliveryOrderDTO deliveryOrderDTO = deliveryOrderMapper.toDto(deliveryOrder);

        restDeliveryOrderMockMvc.perform(post("/api/delivery-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOrderDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository.findAll();
        assertThat(deliveryOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShopSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryOrderRepository.findAll().size();
        // set the field null
        deliveryOrder.setShopSN(null);

        // Create the DeliveryOrder, which fails.
        DeliveryOrderDTO deliveryOrderDTO = deliveryOrderMapper.toDto(deliveryOrder);

        restDeliveryOrderMockMvc.perform(post("/api/delivery-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOrderDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository.findAll();
        assertThat(deliveryOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShopNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryOrderRepository.findAll().size();
        // set the field null
        deliveryOrder.setShopName(null);

        // Create the DeliveryOrder, which fails.
        DeliveryOrderDTO deliveryOrderDTO = deliveryOrderMapper.toDto(deliveryOrder);

        restDeliveryOrderMockMvc.perform(post("/api/delivery-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOrderDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository.findAll();
        assertThat(deliveryOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryOrderRepository.findAll().size();
        // set the field null
        deliveryOrder.setPriceTotal(null);

        // Create the DeliveryOrder, which fails.
        DeliveryOrderDTO deliveryOrderDTO = deliveryOrderMapper.toDto(deliveryOrder);

        restDeliveryOrderMockMvc.perform(post("/api/delivery-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOrderDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository.findAll();
        assertThat(deliveryOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaymentTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryOrderRepository.findAll().size();
        // set the field null
        deliveryOrder.setPaymentTotal(null);

        // Create the DeliveryOrder, which fails.
        DeliveryOrderDTO deliveryOrderDTO = deliveryOrderMapper.toDto(deliveryOrder);

        restDeliveryOrderMockMvc.perform(post("/api/delivery-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOrderDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository.findAll();
        assertThat(deliveryOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaymentModeIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryOrderRepository.findAll().size();
        // set the field null
        deliveryOrder.setPaymentMode(null);

        // Create the DeliveryOrder, which fails.
        DeliveryOrderDTO deliveryOrderDTO = deliveryOrderMapper.toDto(deliveryOrder);

        restDeliveryOrderMockMvc.perform(post("/api/delivery-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOrderDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository.findAll();
        assertThat(deliveryOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDiningDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryOrderRepository.findAll().size();
        // set the field null
        deliveryOrder.setDiningDate(null);

        // Create the DeliveryOrder, which fails.
        DeliveryOrderDTO deliveryOrderDTO = deliveryOrderMapper.toDto(deliveryOrder);

        restDeliveryOrderMockMvc.perform(post("/api/delivery-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOrderDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository.findAll();
        assertThat(deliveryOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryOrderRepository.findAll().size();
        // set the field null
        deliveryOrder.setStatus(null);

        // Create the DeliveryOrder, which fails.
        DeliveryOrderDTO deliveryOrderDTO = deliveryOrderMapper.toDto(deliveryOrder);

        restDeliveryOrderMockMvc.perform(post("/api/delivery-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOrderDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository.findAll();
        assertThat(deliveryOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryOrderRepository.findAll().size();
        // set the field null
        deliveryOrder.setCreatedDate(null);

        // Create the DeliveryOrder, which fails.
        DeliveryOrderDTO deliveryOrderDTO = deliveryOrderMapper.toDto(deliveryOrder);

        restDeliveryOrderMockMvc.perform(post("/api/delivery-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOrderDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository.findAll();
        assertThat(deliveryOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDeliveryOrders() throws Exception {
        // Initialize the database
        deliveryOrderRepository.saveAndFlush(deliveryOrder);

        // Get all the deliveryOrderList
        restDeliveryOrderMockMvc.perform(get("/api/delivery-orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deliveryOrder.getId().intValue())))
            .andExpect(jsonPath("$.[*].sn").value(hasItem(DEFAULT_SN)))
            .andExpect(jsonPath("$.[*].memberName").value(hasItem(DEFAULT_MEMBER_NAME)))
            .andExpect(jsonPath("$.[*].memberSN").value(hasItem(DEFAULT_MEMBER_SN)))
            .andExpect(jsonPath("$.[*].orderType").value(hasItem(DEFAULT_ORDER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].shopSN").value(hasItem(DEFAULT_SHOP_SN)))
            .andExpect(jsonPath("$.[*].shopName").value(hasItem(DEFAULT_SHOP_NAME)))
            .andExpect(jsonPath("$.[*].priceTotal").value(hasItem(DEFAULT_PRICE_TOTAL)))
            .andExpect(jsonPath("$.[*].cardReduce").value(hasItem(DEFAULT_CARD_REDUCE)))
            .andExpect(jsonPath("$.[*].cards").value(hasItem(DEFAULT_CARDS)))
            .andExpect(jsonPath("$.[*].rewardPointsReduce").value(hasItem(DEFAULT_REWARD_POINTS_REDUCE)))
            .andExpect(jsonPath("$.[*].paymentTotal").value(hasItem(DEFAULT_PAYMENT_TOTAL)))
            .andExpect(jsonPath("$.[*].distributionPlatform").value(hasItem(DEFAULT_DISTRIBUTION_PLATFORM.toString())))
            .andExpect(jsonPath("$.[*].deliveryNo").value(hasItem(DEFAULT_DELIVERY_NO)))
            .andExpect(jsonPath("$.[*].deliveryStatus").value(hasItem(DEFAULT_DELIVERY_STATUS)))
            .andExpect(jsonPath("$.[*].deliveryDesc").value(hasItem(DEFAULT_DELIVERY_DESC)))
            .andExpect(jsonPath("$.[*].deliverier").value(hasItem(DEFAULT_DELIVERIER)))
            .andExpect(jsonPath("$.[*].deliverierMobile").value(hasItem(DEFAULT_DELIVERIER_MOBILE)))
            .andExpect(jsonPath("$.[*].deliveryDeductFee").value(hasItem(DEFAULT_DELIVERY_DEDUCT_FEE)))
            .andExpect(jsonPath("$.[*].deliveryFee").value(hasItem(DEFAULT_DELIVERY_FEE)))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT)))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].offsetType").value(hasItem(DEFAULT_OFFSET_TYPE.toString())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].packingFee").value(hasItem(DEFAULT_PACKING_FEE)))
            .andExpect(jsonPath("$.[*].paymentMode").value(hasItem(DEFAULT_PAYMENT_MODE.toString())))
            .andExpect(jsonPath("$.[*].diningDate").value(hasItem(DEFAULT_DINING_DATE.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].paidDate").value(hasItem(DEFAULT_PAID_DATE.toString())))
            .andExpect(jsonPath("$.[*].completedDate").value(hasItem(DEFAULT_COMPLETED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getDeliveryOrder() throws Exception {
        // Initialize the database
        deliveryOrderRepository.saveAndFlush(deliveryOrder);

        // Get the deliveryOrder
        restDeliveryOrderMockMvc.perform(get("/api/delivery-orders/{id}", deliveryOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(deliveryOrder.getId().intValue()))
            .andExpect(jsonPath("$.sn").value(DEFAULT_SN))
            .andExpect(jsonPath("$.memberName").value(DEFAULT_MEMBER_NAME))
            .andExpect(jsonPath("$.memberSN").value(DEFAULT_MEMBER_SN))
            .andExpect(jsonPath("$.orderType").value(DEFAULT_ORDER_TYPE.toString()))
            .andExpect(jsonPath("$.shopSN").value(DEFAULT_SHOP_SN))
            .andExpect(jsonPath("$.shopName").value(DEFAULT_SHOP_NAME))
            .andExpect(jsonPath("$.priceTotal").value(DEFAULT_PRICE_TOTAL))
            .andExpect(jsonPath("$.cardReduce").value(DEFAULT_CARD_REDUCE))
            .andExpect(jsonPath("$.cards").value(DEFAULT_CARDS))
            .andExpect(jsonPath("$.rewardPointsReduce").value(DEFAULT_REWARD_POINTS_REDUCE))
            .andExpect(jsonPath("$.paymentTotal").value(DEFAULT_PAYMENT_TOTAL))
            .andExpect(jsonPath("$.distributionPlatform").value(DEFAULT_DISTRIBUTION_PLATFORM.toString()))
            .andExpect(jsonPath("$.deliveryNo").value(DEFAULT_DELIVERY_NO))
            .andExpect(jsonPath("$.deliveryStatus").value(DEFAULT_DELIVERY_STATUS))
            .andExpect(jsonPath("$.deliveryDesc").value(DEFAULT_DELIVERY_DESC))
            .andExpect(jsonPath("$.deliverier").value(DEFAULT_DELIVERIER))
            .andExpect(jsonPath("$.deliverierMobile").value(DEFAULT_DELIVERIER_MOBILE))
            .andExpect(jsonPath("$.deliveryDeductFee").value(DEFAULT_DELIVERY_DEDUCT_FEE))
            .andExpect(jsonPath("$.deliveryFee").value(DEFAULT_DELIVERY_FEE))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.province").value(DEFAULT_PROVINCE))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT))
            .andExpect(jsonPath("$.offsetType").value(DEFAULT_OFFSET_TYPE.toString()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.packingFee").value(DEFAULT_PACKING_FEE))
            .andExpect(jsonPath("$.paymentMode").value(DEFAULT_PAYMENT_MODE.toString()))
            .andExpect(jsonPath("$.diningDate").value(DEFAULT_DINING_DATE.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.paidDate").value(DEFAULT_PAID_DATE.toString()))
            .andExpect(jsonPath("$.completedDate").value(DEFAULT_COMPLETED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDeliveryOrder() throws Exception {
        // Get the deliveryOrder
        restDeliveryOrderMockMvc.perform(get("/api/delivery-orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeliveryOrder() throws Exception {
        // Initialize the database
        deliveryOrderRepository.saveAndFlush(deliveryOrder);

        int databaseSizeBeforeUpdate = deliveryOrderRepository.findAll().size();

        // Update the deliveryOrder
        DeliveryOrder updatedDeliveryOrder = deliveryOrderRepository.findById(deliveryOrder.getId()).get();
        // Disconnect from session so that the updates on updatedDeliveryOrder are not directly saved in db
        em.detach(updatedDeliveryOrder);
        updatedDeliveryOrder
            .sn(UPDATED_SN)
            .memberName(UPDATED_MEMBER_NAME)
            .memberSN(UPDATED_MEMBER_SN)
            .orderType(UPDATED_ORDER_TYPE)
            .shopSN(UPDATED_SHOP_SN)
            .shopName(UPDATED_SHOP_NAME)
            .priceTotal(UPDATED_PRICE_TOTAL)
            .cardReduce(UPDATED_CARD_REDUCE)
            .cards(UPDATED_CARDS)
            .rewardPointsReduce(UPDATED_REWARD_POINTS_REDUCE)
            .paymentTotal(UPDATED_PAYMENT_TOTAL)
            .distributionPlatform(UPDATED_DISTRIBUTION_PLATFORM)
            .deliveryNo(UPDATED_DELIVERY_NO)
            .deliveryStatus(UPDATED_DELIVERY_STATUS)
            .deliveryDesc(UPDATED_DELIVERY_DESC)
            .deliverier(UPDATED_DELIVERIER)
            .deliverierMobile(UPDATED_DELIVERIER_MOBILE)
            .deliveryDeductFee(UPDATED_DELIVERY_DEDUCT_FEE)
            .deliveryFee(UPDATED_DELIVERY_FEE)
            .contact(UPDATED_CONTACT)
            .sex(UPDATED_SEX)
            .mobile(UPDATED_MOBILE)
            .country(UPDATED_COUNTRY)
            .province(UPDATED_PROVINCE)
            .city(UPDATED_CITY)
            .district(UPDATED_DISTRICT)
            .offsetType(UPDATED_OFFSET_TYPE)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE)
            .address(UPDATED_ADDRESS)
            .packingFee(UPDATED_PACKING_FEE)
            .paymentMode(UPDATED_PAYMENT_MODE)
            .diningDate(UPDATED_DINING_DATE)
            .remark(UPDATED_REMARK)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .paidDate(UPDATED_PAID_DATE)
            .completedDate(UPDATED_COMPLETED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        DeliveryOrderDTO deliveryOrderDTO = deliveryOrderMapper.toDto(updatedDeliveryOrder);

        restDeliveryOrderMockMvc.perform(put("/api/delivery-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOrderDTO)))
            .andExpect(status().isOk());

        // Validate the DeliveryOrder in the database
        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository.findAll();
        assertThat(deliveryOrderList).hasSize(databaseSizeBeforeUpdate);
        DeliveryOrder testDeliveryOrder = deliveryOrderList.get(deliveryOrderList.size() - 1);
        assertThat(testDeliveryOrder.getSn()).isEqualTo(UPDATED_SN);
        assertThat(testDeliveryOrder.getMemberName()).isEqualTo(UPDATED_MEMBER_NAME);
        assertThat(testDeliveryOrder.getMemberSN()).isEqualTo(UPDATED_MEMBER_SN);
        assertThat(testDeliveryOrder.getOrderType()).isEqualTo(UPDATED_ORDER_TYPE);
        assertThat(testDeliveryOrder.getShopSN()).isEqualTo(UPDATED_SHOP_SN);
        assertThat(testDeliveryOrder.getShopName()).isEqualTo(UPDATED_SHOP_NAME);
        assertThat(testDeliveryOrder.getPriceTotal()).isEqualTo(UPDATED_PRICE_TOTAL);
        assertThat(testDeliveryOrder.getCardReduce()).isEqualTo(UPDATED_CARD_REDUCE);
        assertThat(testDeliveryOrder.getCards()).isEqualTo(UPDATED_CARDS);
        assertThat(testDeliveryOrder.getRewardPointsReduce()).isEqualTo(UPDATED_REWARD_POINTS_REDUCE);
        assertThat(testDeliveryOrder.getPaymentTotal()).isEqualTo(UPDATED_PAYMENT_TOTAL);
        assertThat(testDeliveryOrder.getDistributionPlatform()).isEqualTo(UPDATED_DISTRIBUTION_PLATFORM);
        assertThat(testDeliveryOrder.getDeliveryNo()).isEqualTo(UPDATED_DELIVERY_NO);
        assertThat(testDeliveryOrder.getDeliveryStatus()).isEqualTo(UPDATED_DELIVERY_STATUS);
        assertThat(testDeliveryOrder.getDeliveryDesc()).isEqualTo(UPDATED_DELIVERY_DESC);
        assertThat(testDeliveryOrder.getDeliverier()).isEqualTo(UPDATED_DELIVERIER);
        assertThat(testDeliveryOrder.getDeliverierMobile()).isEqualTo(UPDATED_DELIVERIER_MOBILE);
        assertThat(testDeliveryOrder.getDeliveryDeductFee()).isEqualTo(UPDATED_DELIVERY_DEDUCT_FEE);
        assertThat(testDeliveryOrder.getDeliveryFee()).isEqualTo(UPDATED_DELIVERY_FEE);
        assertThat(testDeliveryOrder.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testDeliveryOrder.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testDeliveryOrder.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testDeliveryOrder.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testDeliveryOrder.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testDeliveryOrder.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testDeliveryOrder.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testDeliveryOrder.getOffsetType()).isEqualTo(UPDATED_OFFSET_TYPE);
        assertThat(testDeliveryOrder.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testDeliveryOrder.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testDeliveryOrder.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testDeliveryOrder.getPackingFee()).isEqualTo(UPDATED_PACKING_FEE);
        assertThat(testDeliveryOrder.getPaymentMode()).isEqualTo(UPDATED_PAYMENT_MODE);
        assertThat(testDeliveryOrder.getDiningDate()).isEqualTo(UPDATED_DINING_DATE);
        assertThat(testDeliveryOrder.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testDeliveryOrder.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryOrder.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryOrder.getPaidDate()).isEqualTo(UPDATED_PAID_DATE);
        assertThat(testDeliveryOrder.getCompletedDate()).isEqualTo(UPDATED_COMPLETED_DATE);
        assertThat(testDeliveryOrder.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingDeliveryOrder() throws Exception {
        int databaseSizeBeforeUpdate = deliveryOrderRepository.findAll().size();

        // Create the DeliveryOrder
        DeliveryOrderDTO deliveryOrderDTO = deliveryOrderMapper.toDto(deliveryOrder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryOrderMockMvc.perform(put("/api/delivery-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeliveryOrder in the database
        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository.findAll();
        assertThat(deliveryOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDeliveryOrder() throws Exception {
        // Initialize the database
        deliveryOrderRepository.saveAndFlush(deliveryOrder);

        int databaseSizeBeforeDelete = deliveryOrderRepository.findAll().size();

        // Delete the deliveryOrder
        restDeliveryOrderMockMvc.perform(delete("/api/delivery-orders/{id}", deliveryOrder.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository.findAll();
        assertThat(deliveryOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
