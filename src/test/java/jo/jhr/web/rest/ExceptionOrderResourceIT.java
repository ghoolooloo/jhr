package jo.jhr.web.rest;

import jo.jhr.RedisTestContainerExtension;
import jo.jhr.JhrApp;
import jo.jhr.domain.ExceptionOrder;
import jo.jhr.repository.ExceptionOrderRepository;
import jo.jhr.service.ExceptionOrderService;
import jo.jhr.service.dto.ExceptionOrderDTO;
import jo.jhr.service.mapper.ExceptionOrderMapper;

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
 * Integration tests for the {@link ExceptionOrderResource} REST controller.
 */
@SpringBootTest(classes = JhrApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class ExceptionOrderResourceIT {

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

    private static final Instant DEFAULT_EXCEPTION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EXCEPTION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_HANDLER = "AAAAAAAAAA";
    private static final String UPDATED_HANDLER = "BBBBBBBBBB";

    private static final Instant DEFAULT_HANDLED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_HANDLED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_HANDLED_DESC = "AAAAAAAAAA";
    private static final String UPDATED_HANDLED_DESC = "BBBBBBBBBB";

    @Autowired
    private ExceptionOrderRepository exceptionOrderRepository;

    @Autowired
    private ExceptionOrderMapper exceptionOrderMapper;

    @Autowired
    private ExceptionOrderService exceptionOrderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restExceptionOrderMockMvc;

    private ExceptionOrder exceptionOrder;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExceptionOrder createEntity(EntityManager em) {
        ExceptionOrder exceptionOrder = new ExceptionOrder()
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
            .exceptionDate(DEFAULT_EXCEPTION_DATE)
            .handler(DEFAULT_HANDLER)
            .handledDate(DEFAULT_HANDLED_DATE)
            .handledDesc(DEFAULT_HANDLED_DESC);
        return exceptionOrder;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExceptionOrder createUpdatedEntity(EntityManager em) {
        ExceptionOrder exceptionOrder = new ExceptionOrder()
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
            .exceptionDate(UPDATED_EXCEPTION_DATE)
            .handler(UPDATED_HANDLER)
            .handledDate(UPDATED_HANDLED_DATE)
            .handledDesc(UPDATED_HANDLED_DESC);
        return exceptionOrder;
    }

    @BeforeEach
    public void initTest() {
        exceptionOrder = createEntity(em);
    }

    @Test
    @Transactional
    public void createExceptionOrder() throws Exception {
        int databaseSizeBeforeCreate = exceptionOrderRepository.findAll().size();

        // Create the ExceptionOrder
        ExceptionOrderDTO exceptionOrderDTO = exceptionOrderMapper.toDto(exceptionOrder);
        restExceptionOrderMockMvc.perform(post("/api/exception-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exceptionOrderDTO)))
            .andExpect(status().isCreated());

        // Validate the ExceptionOrder in the database
        List<ExceptionOrder> exceptionOrderList = exceptionOrderRepository.findAll();
        assertThat(exceptionOrderList).hasSize(databaseSizeBeforeCreate + 1);
        ExceptionOrder testExceptionOrder = exceptionOrderList.get(exceptionOrderList.size() - 1);
        assertThat(testExceptionOrder.getSn()).isEqualTo(DEFAULT_SN);
        assertThat(testExceptionOrder.getMemberName()).isEqualTo(DEFAULT_MEMBER_NAME);
        assertThat(testExceptionOrder.getMemberSN()).isEqualTo(DEFAULT_MEMBER_SN);
        assertThat(testExceptionOrder.getOrderType()).isEqualTo(DEFAULT_ORDER_TYPE);
        assertThat(testExceptionOrder.getShopSN()).isEqualTo(DEFAULT_SHOP_SN);
        assertThat(testExceptionOrder.getShopName()).isEqualTo(DEFAULT_SHOP_NAME);
        assertThat(testExceptionOrder.getPriceTotal()).isEqualTo(DEFAULT_PRICE_TOTAL);
        assertThat(testExceptionOrder.getCardReduce()).isEqualTo(DEFAULT_CARD_REDUCE);
        assertThat(testExceptionOrder.getCards()).isEqualTo(DEFAULT_CARDS);
        assertThat(testExceptionOrder.getRewardPointsReduce()).isEqualTo(DEFAULT_REWARD_POINTS_REDUCE);
        assertThat(testExceptionOrder.getPaymentTotal()).isEqualTo(DEFAULT_PAYMENT_TOTAL);
        assertThat(testExceptionOrder.getDistributionPlatform()).isEqualTo(DEFAULT_DISTRIBUTION_PLATFORM);
        assertThat(testExceptionOrder.getDeliveryNo()).isEqualTo(DEFAULT_DELIVERY_NO);
        assertThat(testExceptionOrder.getDeliveryStatus()).isEqualTo(DEFAULT_DELIVERY_STATUS);
        assertThat(testExceptionOrder.getDeliveryDesc()).isEqualTo(DEFAULT_DELIVERY_DESC);
        assertThat(testExceptionOrder.getDeliverier()).isEqualTo(DEFAULT_DELIVERIER);
        assertThat(testExceptionOrder.getDeliverierMobile()).isEqualTo(DEFAULT_DELIVERIER_MOBILE);
        assertThat(testExceptionOrder.getDeliveryDeductFee()).isEqualTo(DEFAULT_DELIVERY_DEDUCT_FEE);
        assertThat(testExceptionOrder.getDeliveryFee()).isEqualTo(DEFAULT_DELIVERY_FEE);
        assertThat(testExceptionOrder.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testExceptionOrder.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testExceptionOrder.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testExceptionOrder.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testExceptionOrder.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testExceptionOrder.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testExceptionOrder.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testExceptionOrder.getOffsetType()).isEqualTo(DEFAULT_OFFSET_TYPE);
        assertThat(testExceptionOrder.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testExceptionOrder.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testExceptionOrder.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testExceptionOrder.getPackingFee()).isEqualTo(DEFAULT_PACKING_FEE);
        assertThat(testExceptionOrder.getPaymentMode()).isEqualTo(DEFAULT_PAYMENT_MODE);
        assertThat(testExceptionOrder.getDiningDate()).isEqualTo(DEFAULT_DINING_DATE);
        assertThat(testExceptionOrder.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testExceptionOrder.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testExceptionOrder.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testExceptionOrder.getPaidDate()).isEqualTo(DEFAULT_PAID_DATE);
        assertThat(testExceptionOrder.getExceptionDate()).isEqualTo(DEFAULT_EXCEPTION_DATE);
        assertThat(testExceptionOrder.getHandler()).isEqualTo(DEFAULT_HANDLER);
        assertThat(testExceptionOrder.getHandledDate()).isEqualTo(DEFAULT_HANDLED_DATE);
        assertThat(testExceptionOrder.getHandledDesc()).isEqualTo(DEFAULT_HANDLED_DESC);
    }

    @Test
    @Transactional
    public void createExceptionOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = exceptionOrderRepository.findAll().size();

        // Create the ExceptionOrder with an existing ID
        exceptionOrder.setId(1L);
        ExceptionOrderDTO exceptionOrderDTO = exceptionOrderMapper.toDto(exceptionOrder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExceptionOrderMockMvc.perform(post("/api/exception-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exceptionOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ExceptionOrder in the database
        List<ExceptionOrder> exceptionOrderList = exceptionOrderRepository.findAll();
        assertThat(exceptionOrderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSnIsRequired() throws Exception {
        int databaseSizeBeforeTest = exceptionOrderRepository.findAll().size();
        // set the field null
        exceptionOrder.setSn(null);

        // Create the ExceptionOrder, which fails.
        ExceptionOrderDTO exceptionOrderDTO = exceptionOrderMapper.toDto(exceptionOrder);

        restExceptionOrderMockMvc.perform(post("/api/exception-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exceptionOrderDTO)))
            .andExpect(status().isBadRequest());

        List<ExceptionOrder> exceptionOrderList = exceptionOrderRepository.findAll();
        assertThat(exceptionOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMemberNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = exceptionOrderRepository.findAll().size();
        // set the field null
        exceptionOrder.setMemberName(null);

        // Create the ExceptionOrder, which fails.
        ExceptionOrderDTO exceptionOrderDTO = exceptionOrderMapper.toDto(exceptionOrder);

        restExceptionOrderMockMvc.perform(post("/api/exception-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exceptionOrderDTO)))
            .andExpect(status().isBadRequest());

        List<ExceptionOrder> exceptionOrderList = exceptionOrderRepository.findAll();
        assertThat(exceptionOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMemberSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = exceptionOrderRepository.findAll().size();
        // set the field null
        exceptionOrder.setMemberSN(null);

        // Create the ExceptionOrder, which fails.
        ExceptionOrderDTO exceptionOrderDTO = exceptionOrderMapper.toDto(exceptionOrder);

        restExceptionOrderMockMvc.perform(post("/api/exception-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exceptionOrderDTO)))
            .andExpect(status().isBadRequest());

        List<ExceptionOrder> exceptionOrderList = exceptionOrderRepository.findAll();
        assertThat(exceptionOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = exceptionOrderRepository.findAll().size();
        // set the field null
        exceptionOrder.setOrderType(null);

        // Create the ExceptionOrder, which fails.
        ExceptionOrderDTO exceptionOrderDTO = exceptionOrderMapper.toDto(exceptionOrder);

        restExceptionOrderMockMvc.perform(post("/api/exception-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exceptionOrderDTO)))
            .andExpect(status().isBadRequest());

        List<ExceptionOrder> exceptionOrderList = exceptionOrderRepository.findAll();
        assertThat(exceptionOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShopSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = exceptionOrderRepository.findAll().size();
        // set the field null
        exceptionOrder.setShopSN(null);

        // Create the ExceptionOrder, which fails.
        ExceptionOrderDTO exceptionOrderDTO = exceptionOrderMapper.toDto(exceptionOrder);

        restExceptionOrderMockMvc.perform(post("/api/exception-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exceptionOrderDTO)))
            .andExpect(status().isBadRequest());

        List<ExceptionOrder> exceptionOrderList = exceptionOrderRepository.findAll();
        assertThat(exceptionOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShopNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = exceptionOrderRepository.findAll().size();
        // set the field null
        exceptionOrder.setShopName(null);

        // Create the ExceptionOrder, which fails.
        ExceptionOrderDTO exceptionOrderDTO = exceptionOrderMapper.toDto(exceptionOrder);

        restExceptionOrderMockMvc.perform(post("/api/exception-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exceptionOrderDTO)))
            .andExpect(status().isBadRequest());

        List<ExceptionOrder> exceptionOrderList = exceptionOrderRepository.findAll();
        assertThat(exceptionOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = exceptionOrderRepository.findAll().size();
        // set the field null
        exceptionOrder.setPriceTotal(null);

        // Create the ExceptionOrder, which fails.
        ExceptionOrderDTO exceptionOrderDTO = exceptionOrderMapper.toDto(exceptionOrder);

        restExceptionOrderMockMvc.perform(post("/api/exception-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exceptionOrderDTO)))
            .andExpect(status().isBadRequest());

        List<ExceptionOrder> exceptionOrderList = exceptionOrderRepository.findAll();
        assertThat(exceptionOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaymentTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = exceptionOrderRepository.findAll().size();
        // set the field null
        exceptionOrder.setPaymentTotal(null);

        // Create the ExceptionOrder, which fails.
        ExceptionOrderDTO exceptionOrderDTO = exceptionOrderMapper.toDto(exceptionOrder);

        restExceptionOrderMockMvc.perform(post("/api/exception-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exceptionOrderDTO)))
            .andExpect(status().isBadRequest());

        List<ExceptionOrder> exceptionOrderList = exceptionOrderRepository.findAll();
        assertThat(exceptionOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaymentModeIsRequired() throws Exception {
        int databaseSizeBeforeTest = exceptionOrderRepository.findAll().size();
        // set the field null
        exceptionOrder.setPaymentMode(null);

        // Create the ExceptionOrder, which fails.
        ExceptionOrderDTO exceptionOrderDTO = exceptionOrderMapper.toDto(exceptionOrder);

        restExceptionOrderMockMvc.perform(post("/api/exception-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exceptionOrderDTO)))
            .andExpect(status().isBadRequest());

        List<ExceptionOrder> exceptionOrderList = exceptionOrderRepository.findAll();
        assertThat(exceptionOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDiningDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = exceptionOrderRepository.findAll().size();
        // set the field null
        exceptionOrder.setDiningDate(null);

        // Create the ExceptionOrder, which fails.
        ExceptionOrderDTO exceptionOrderDTO = exceptionOrderMapper.toDto(exceptionOrder);

        restExceptionOrderMockMvc.perform(post("/api/exception-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exceptionOrderDTO)))
            .andExpect(status().isBadRequest());

        List<ExceptionOrder> exceptionOrderList = exceptionOrderRepository.findAll();
        assertThat(exceptionOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = exceptionOrderRepository.findAll().size();
        // set the field null
        exceptionOrder.setStatus(null);

        // Create the ExceptionOrder, which fails.
        ExceptionOrderDTO exceptionOrderDTO = exceptionOrderMapper.toDto(exceptionOrder);

        restExceptionOrderMockMvc.perform(post("/api/exception-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exceptionOrderDTO)))
            .andExpect(status().isBadRequest());

        List<ExceptionOrder> exceptionOrderList = exceptionOrderRepository.findAll();
        assertThat(exceptionOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = exceptionOrderRepository.findAll().size();
        // set the field null
        exceptionOrder.setCreatedDate(null);

        // Create the ExceptionOrder, which fails.
        ExceptionOrderDTO exceptionOrderDTO = exceptionOrderMapper.toDto(exceptionOrder);

        restExceptionOrderMockMvc.perform(post("/api/exception-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exceptionOrderDTO)))
            .andExpect(status().isBadRequest());

        List<ExceptionOrder> exceptionOrderList = exceptionOrderRepository.findAll();
        assertThat(exceptionOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHandlerIsRequired() throws Exception {
        int databaseSizeBeforeTest = exceptionOrderRepository.findAll().size();
        // set the field null
        exceptionOrder.setHandler(null);

        // Create the ExceptionOrder, which fails.
        ExceptionOrderDTO exceptionOrderDTO = exceptionOrderMapper.toDto(exceptionOrder);

        restExceptionOrderMockMvc.perform(post("/api/exception-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exceptionOrderDTO)))
            .andExpect(status().isBadRequest());

        List<ExceptionOrder> exceptionOrderList = exceptionOrderRepository.findAll();
        assertThat(exceptionOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllExceptionOrders() throws Exception {
        // Initialize the database
        exceptionOrderRepository.saveAndFlush(exceptionOrder);

        // Get all the exceptionOrderList
        restExceptionOrderMockMvc.perform(get("/api/exception-orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(exceptionOrder.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].exceptionDate").value(hasItem(DEFAULT_EXCEPTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].handler").value(hasItem(DEFAULT_HANDLER)))
            .andExpect(jsonPath("$.[*].handledDate").value(hasItem(DEFAULT_HANDLED_DATE.toString())))
            .andExpect(jsonPath("$.[*].handledDesc").value(hasItem(DEFAULT_HANDLED_DESC)));
    }
    
    @Test
    @Transactional
    public void getExceptionOrder() throws Exception {
        // Initialize the database
        exceptionOrderRepository.saveAndFlush(exceptionOrder);

        // Get the exceptionOrder
        restExceptionOrderMockMvc.perform(get("/api/exception-orders/{id}", exceptionOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(exceptionOrder.getId().intValue()))
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
            .andExpect(jsonPath("$.exceptionDate").value(DEFAULT_EXCEPTION_DATE.toString()))
            .andExpect(jsonPath("$.handler").value(DEFAULT_HANDLER))
            .andExpect(jsonPath("$.handledDate").value(DEFAULT_HANDLED_DATE.toString()))
            .andExpect(jsonPath("$.handledDesc").value(DEFAULT_HANDLED_DESC));
    }

    @Test
    @Transactional
    public void getNonExistingExceptionOrder() throws Exception {
        // Get the exceptionOrder
        restExceptionOrderMockMvc.perform(get("/api/exception-orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExceptionOrder() throws Exception {
        // Initialize the database
        exceptionOrderRepository.saveAndFlush(exceptionOrder);

        int databaseSizeBeforeUpdate = exceptionOrderRepository.findAll().size();

        // Update the exceptionOrder
        ExceptionOrder updatedExceptionOrder = exceptionOrderRepository.findById(exceptionOrder.getId()).get();
        // Disconnect from session so that the updates on updatedExceptionOrder are not directly saved in db
        em.detach(updatedExceptionOrder);
        updatedExceptionOrder
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
            .exceptionDate(UPDATED_EXCEPTION_DATE)
            .handler(UPDATED_HANDLER)
            .handledDate(UPDATED_HANDLED_DATE)
            .handledDesc(UPDATED_HANDLED_DESC);
        ExceptionOrderDTO exceptionOrderDTO = exceptionOrderMapper.toDto(updatedExceptionOrder);

        restExceptionOrderMockMvc.perform(put("/api/exception-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exceptionOrderDTO)))
            .andExpect(status().isOk());

        // Validate the ExceptionOrder in the database
        List<ExceptionOrder> exceptionOrderList = exceptionOrderRepository.findAll();
        assertThat(exceptionOrderList).hasSize(databaseSizeBeforeUpdate);
        ExceptionOrder testExceptionOrder = exceptionOrderList.get(exceptionOrderList.size() - 1);
        assertThat(testExceptionOrder.getSn()).isEqualTo(UPDATED_SN);
        assertThat(testExceptionOrder.getMemberName()).isEqualTo(UPDATED_MEMBER_NAME);
        assertThat(testExceptionOrder.getMemberSN()).isEqualTo(UPDATED_MEMBER_SN);
        assertThat(testExceptionOrder.getOrderType()).isEqualTo(UPDATED_ORDER_TYPE);
        assertThat(testExceptionOrder.getShopSN()).isEqualTo(UPDATED_SHOP_SN);
        assertThat(testExceptionOrder.getShopName()).isEqualTo(UPDATED_SHOP_NAME);
        assertThat(testExceptionOrder.getPriceTotal()).isEqualTo(UPDATED_PRICE_TOTAL);
        assertThat(testExceptionOrder.getCardReduce()).isEqualTo(UPDATED_CARD_REDUCE);
        assertThat(testExceptionOrder.getCards()).isEqualTo(UPDATED_CARDS);
        assertThat(testExceptionOrder.getRewardPointsReduce()).isEqualTo(UPDATED_REWARD_POINTS_REDUCE);
        assertThat(testExceptionOrder.getPaymentTotal()).isEqualTo(UPDATED_PAYMENT_TOTAL);
        assertThat(testExceptionOrder.getDistributionPlatform()).isEqualTo(UPDATED_DISTRIBUTION_PLATFORM);
        assertThat(testExceptionOrder.getDeliveryNo()).isEqualTo(UPDATED_DELIVERY_NO);
        assertThat(testExceptionOrder.getDeliveryStatus()).isEqualTo(UPDATED_DELIVERY_STATUS);
        assertThat(testExceptionOrder.getDeliveryDesc()).isEqualTo(UPDATED_DELIVERY_DESC);
        assertThat(testExceptionOrder.getDeliverier()).isEqualTo(UPDATED_DELIVERIER);
        assertThat(testExceptionOrder.getDeliverierMobile()).isEqualTo(UPDATED_DELIVERIER_MOBILE);
        assertThat(testExceptionOrder.getDeliveryDeductFee()).isEqualTo(UPDATED_DELIVERY_DEDUCT_FEE);
        assertThat(testExceptionOrder.getDeliveryFee()).isEqualTo(UPDATED_DELIVERY_FEE);
        assertThat(testExceptionOrder.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testExceptionOrder.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testExceptionOrder.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testExceptionOrder.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testExceptionOrder.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testExceptionOrder.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testExceptionOrder.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testExceptionOrder.getOffsetType()).isEqualTo(UPDATED_OFFSET_TYPE);
        assertThat(testExceptionOrder.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testExceptionOrder.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testExceptionOrder.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testExceptionOrder.getPackingFee()).isEqualTo(UPDATED_PACKING_FEE);
        assertThat(testExceptionOrder.getPaymentMode()).isEqualTo(UPDATED_PAYMENT_MODE);
        assertThat(testExceptionOrder.getDiningDate()).isEqualTo(UPDATED_DINING_DATE);
        assertThat(testExceptionOrder.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testExceptionOrder.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testExceptionOrder.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testExceptionOrder.getPaidDate()).isEqualTo(UPDATED_PAID_DATE);
        assertThat(testExceptionOrder.getExceptionDate()).isEqualTo(UPDATED_EXCEPTION_DATE);
        assertThat(testExceptionOrder.getHandler()).isEqualTo(UPDATED_HANDLER);
        assertThat(testExceptionOrder.getHandledDate()).isEqualTo(UPDATED_HANDLED_DATE);
        assertThat(testExceptionOrder.getHandledDesc()).isEqualTo(UPDATED_HANDLED_DESC);
    }

    @Test
    @Transactional
    public void updateNonExistingExceptionOrder() throws Exception {
        int databaseSizeBeforeUpdate = exceptionOrderRepository.findAll().size();

        // Create the ExceptionOrder
        ExceptionOrderDTO exceptionOrderDTO = exceptionOrderMapper.toDto(exceptionOrder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExceptionOrderMockMvc.perform(put("/api/exception-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exceptionOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ExceptionOrder in the database
        List<ExceptionOrder> exceptionOrderList = exceptionOrderRepository.findAll();
        assertThat(exceptionOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExceptionOrder() throws Exception {
        // Initialize the database
        exceptionOrderRepository.saveAndFlush(exceptionOrder);

        int databaseSizeBeforeDelete = exceptionOrderRepository.findAll().size();

        // Delete the exceptionOrder
        restExceptionOrderMockMvc.perform(delete("/api/exception-orders/{id}", exceptionOrder.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ExceptionOrder> exceptionOrderList = exceptionOrderRepository.findAll();
        assertThat(exceptionOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
