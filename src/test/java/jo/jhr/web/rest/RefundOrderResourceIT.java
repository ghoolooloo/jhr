package jo.jhr.web.rest;

import jo.jhr.RedisTestContainerExtension;
import jo.jhr.JhrApp;
import jo.jhr.domain.RefundOrder;
import jo.jhr.repository.RefundOrderRepository;
import jo.jhr.service.RefundOrderService;
import jo.jhr.service.dto.RefundOrderDTO;
import jo.jhr.service.mapper.RefundOrderMapper;

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
 * Integration tests for the {@link RefundOrderResource} REST controller.
 */
@SpringBootTest(classes = JhrApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class RefundOrderResourceIT {

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

    private static final Instant DEFAULT_EXCEPTION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EXCEPTION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_HANDLER = "AAAAAAAAAA";
    private static final String UPDATED_HANDLER = "BBBBBBBBBB";

    private static final Instant DEFAULT_HANDLED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_HANDLED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_HANDLED_DESC = "AAAAAAAAAA";
    private static final String UPDATED_HANDLED_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_APPLICANT = "AAAAAAAAAA";
    private static final String UPDATED_APPLICANT = "BBBBBBBBBB";

    private static final Instant DEFAULT_APPLIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPLIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_REFUND_DESC = "AAAAAAAAAA";
    private static final String UPDATED_REFUND_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_REFUNDED_BY = "AAAAAAAAAA";
    private static final String UPDATED_REFUNDED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_REFUNDED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REFUNDED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_REFUND_AMOUNT = 0;
    private static final Integer UPDATED_REFUND_AMOUNT = 1;

    private static final String DEFAULT_REPLY = "AAAAAAAAAA";
    private static final String UPDATED_REPLY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PASSED = false;
    private static final Boolean UPDATED_PASSED = true;

    @Autowired
    private RefundOrderRepository refundOrderRepository;

    @Autowired
    private RefundOrderMapper refundOrderMapper;

    @Autowired
    private RefundOrderService refundOrderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRefundOrderMockMvc;

    private RefundOrder refundOrder;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RefundOrder createEntity(EntityManager em) {
        RefundOrder refundOrder = new RefundOrder()
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
            .exceptionDate(DEFAULT_EXCEPTION_DATE)
            .handler(DEFAULT_HANDLER)
            .handledDate(DEFAULT_HANDLED_DATE)
            .handledDesc(DEFAULT_HANDLED_DESC)
            .applicant(DEFAULT_APPLICANT)
            .appliedDate(DEFAULT_APPLIED_DATE)
            .refundDesc(DEFAULT_REFUND_DESC)
            .refundedBy(DEFAULT_REFUNDED_BY)
            .refundedDate(DEFAULT_REFUNDED_DATE)
            .refundAmount(DEFAULT_REFUND_AMOUNT)
            .reply(DEFAULT_REPLY)
            .passed(DEFAULT_PASSED);
        return refundOrder;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RefundOrder createUpdatedEntity(EntityManager em) {
        RefundOrder refundOrder = new RefundOrder()
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
            .exceptionDate(UPDATED_EXCEPTION_DATE)
            .handler(UPDATED_HANDLER)
            .handledDate(UPDATED_HANDLED_DATE)
            .handledDesc(UPDATED_HANDLED_DESC)
            .applicant(UPDATED_APPLICANT)
            .appliedDate(UPDATED_APPLIED_DATE)
            .refundDesc(UPDATED_REFUND_DESC)
            .refundedBy(UPDATED_REFUNDED_BY)
            .refundedDate(UPDATED_REFUNDED_DATE)
            .refundAmount(UPDATED_REFUND_AMOUNT)
            .reply(UPDATED_REPLY)
            .passed(UPDATED_PASSED);
        return refundOrder;
    }

    @BeforeEach
    public void initTest() {
        refundOrder = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefundOrder() throws Exception {
        int databaseSizeBeforeCreate = refundOrderRepository.findAll().size();

        // Create the RefundOrder
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(refundOrder);
        restRefundOrderMockMvc.perform(post("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isCreated());

        // Validate the RefundOrder in the database
        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeCreate + 1);
        RefundOrder testRefundOrder = refundOrderList.get(refundOrderList.size() - 1);
        assertThat(testRefundOrder.getSn()).isEqualTo(DEFAULT_SN);
        assertThat(testRefundOrder.getMemberName()).isEqualTo(DEFAULT_MEMBER_NAME);
        assertThat(testRefundOrder.getMemberSN()).isEqualTo(DEFAULT_MEMBER_SN);
        assertThat(testRefundOrder.getOrderType()).isEqualTo(DEFAULT_ORDER_TYPE);
        assertThat(testRefundOrder.getShopSN()).isEqualTo(DEFAULT_SHOP_SN);
        assertThat(testRefundOrder.getShopName()).isEqualTo(DEFAULT_SHOP_NAME);
        assertThat(testRefundOrder.getPriceTotal()).isEqualTo(DEFAULT_PRICE_TOTAL);
        assertThat(testRefundOrder.getCardReduce()).isEqualTo(DEFAULT_CARD_REDUCE);
        assertThat(testRefundOrder.getCards()).isEqualTo(DEFAULT_CARDS);
        assertThat(testRefundOrder.getRewardPointsReduce()).isEqualTo(DEFAULT_REWARD_POINTS_REDUCE);
        assertThat(testRefundOrder.getPaymentTotal()).isEqualTo(DEFAULT_PAYMENT_TOTAL);
        assertThat(testRefundOrder.getDistributionPlatform()).isEqualTo(DEFAULT_DISTRIBUTION_PLATFORM);
        assertThat(testRefundOrder.getDeliveryNo()).isEqualTo(DEFAULT_DELIVERY_NO);
        assertThat(testRefundOrder.getDeliveryStatus()).isEqualTo(DEFAULT_DELIVERY_STATUS);
        assertThat(testRefundOrder.getDeliveryDesc()).isEqualTo(DEFAULT_DELIVERY_DESC);
        assertThat(testRefundOrder.getDeliverier()).isEqualTo(DEFAULT_DELIVERIER);
        assertThat(testRefundOrder.getDeliverierMobile()).isEqualTo(DEFAULT_DELIVERIER_MOBILE);
        assertThat(testRefundOrder.getDeliveryDeductFee()).isEqualTo(DEFAULT_DELIVERY_DEDUCT_FEE);
        assertThat(testRefundOrder.getDeliveryFee()).isEqualTo(DEFAULT_DELIVERY_FEE);
        assertThat(testRefundOrder.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testRefundOrder.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testRefundOrder.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testRefundOrder.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testRefundOrder.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testRefundOrder.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testRefundOrder.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testRefundOrder.getOffsetType()).isEqualTo(DEFAULT_OFFSET_TYPE);
        assertThat(testRefundOrder.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testRefundOrder.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testRefundOrder.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testRefundOrder.getPackingFee()).isEqualTo(DEFAULT_PACKING_FEE);
        assertThat(testRefundOrder.getPaymentMode()).isEqualTo(DEFAULT_PAYMENT_MODE);
        assertThat(testRefundOrder.getDiningDate()).isEqualTo(DEFAULT_DINING_DATE);
        assertThat(testRefundOrder.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testRefundOrder.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRefundOrder.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testRefundOrder.getPaidDate()).isEqualTo(DEFAULT_PAID_DATE);
        assertThat(testRefundOrder.getCompletedDate()).isEqualTo(DEFAULT_COMPLETED_DATE);
        assertThat(testRefundOrder.getExceptionDate()).isEqualTo(DEFAULT_EXCEPTION_DATE);
        assertThat(testRefundOrder.getHandler()).isEqualTo(DEFAULT_HANDLER);
        assertThat(testRefundOrder.getHandledDate()).isEqualTo(DEFAULT_HANDLED_DATE);
        assertThat(testRefundOrder.getHandledDesc()).isEqualTo(DEFAULT_HANDLED_DESC);
        assertThat(testRefundOrder.getApplicant()).isEqualTo(DEFAULT_APPLICANT);
        assertThat(testRefundOrder.getAppliedDate()).isEqualTo(DEFAULT_APPLIED_DATE);
        assertThat(testRefundOrder.getRefundDesc()).isEqualTo(DEFAULT_REFUND_DESC);
        assertThat(testRefundOrder.getRefundedBy()).isEqualTo(DEFAULT_REFUNDED_BY);
        assertThat(testRefundOrder.getRefundedDate()).isEqualTo(DEFAULT_REFUNDED_DATE);
        assertThat(testRefundOrder.getRefundAmount()).isEqualTo(DEFAULT_REFUND_AMOUNT);
        assertThat(testRefundOrder.getReply()).isEqualTo(DEFAULT_REPLY);
        assertThat(testRefundOrder.isPassed()).isEqualTo(DEFAULT_PASSED);
    }

    @Test
    @Transactional
    public void createRefundOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refundOrderRepository.findAll().size();

        // Create the RefundOrder with an existing ID
        refundOrder.setId(1L);
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(refundOrder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefundOrderMockMvc.perform(post("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefundOrder in the database
        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSnIsRequired() throws Exception {
        int databaseSizeBeforeTest = refundOrderRepository.findAll().size();
        // set the field null
        refundOrder.setSn(null);

        // Create the RefundOrder, which fails.
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(refundOrder);

        restRefundOrderMockMvc.perform(post("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isBadRequest());

        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMemberNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = refundOrderRepository.findAll().size();
        // set the field null
        refundOrder.setMemberName(null);

        // Create the RefundOrder, which fails.
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(refundOrder);

        restRefundOrderMockMvc.perform(post("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isBadRequest());

        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMemberSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = refundOrderRepository.findAll().size();
        // set the field null
        refundOrder.setMemberSN(null);

        // Create the RefundOrder, which fails.
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(refundOrder);

        restRefundOrderMockMvc.perform(post("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isBadRequest());

        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = refundOrderRepository.findAll().size();
        // set the field null
        refundOrder.setOrderType(null);

        // Create the RefundOrder, which fails.
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(refundOrder);

        restRefundOrderMockMvc.perform(post("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isBadRequest());

        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShopSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = refundOrderRepository.findAll().size();
        // set the field null
        refundOrder.setShopSN(null);

        // Create the RefundOrder, which fails.
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(refundOrder);

        restRefundOrderMockMvc.perform(post("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isBadRequest());

        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShopNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = refundOrderRepository.findAll().size();
        // set the field null
        refundOrder.setShopName(null);

        // Create the RefundOrder, which fails.
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(refundOrder);

        restRefundOrderMockMvc.perform(post("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isBadRequest());

        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = refundOrderRepository.findAll().size();
        // set the field null
        refundOrder.setPriceTotal(null);

        // Create the RefundOrder, which fails.
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(refundOrder);

        restRefundOrderMockMvc.perform(post("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isBadRequest());

        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaymentTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = refundOrderRepository.findAll().size();
        // set the field null
        refundOrder.setPaymentTotal(null);

        // Create the RefundOrder, which fails.
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(refundOrder);

        restRefundOrderMockMvc.perform(post("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isBadRequest());

        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaymentModeIsRequired() throws Exception {
        int databaseSizeBeforeTest = refundOrderRepository.findAll().size();
        // set the field null
        refundOrder.setPaymentMode(null);

        // Create the RefundOrder, which fails.
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(refundOrder);

        restRefundOrderMockMvc.perform(post("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isBadRequest());

        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDiningDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = refundOrderRepository.findAll().size();
        // set the field null
        refundOrder.setDiningDate(null);

        // Create the RefundOrder, which fails.
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(refundOrder);

        restRefundOrderMockMvc.perform(post("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isBadRequest());

        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = refundOrderRepository.findAll().size();
        // set the field null
        refundOrder.setStatus(null);

        // Create the RefundOrder, which fails.
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(refundOrder);

        restRefundOrderMockMvc.perform(post("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isBadRequest());

        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = refundOrderRepository.findAll().size();
        // set the field null
        refundOrder.setCreatedDate(null);

        // Create the RefundOrder, which fails.
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(refundOrder);

        restRefundOrderMockMvc.perform(post("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isBadRequest());

        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHandlerIsRequired() throws Exception {
        int databaseSizeBeforeTest = refundOrderRepository.findAll().size();
        // set the field null
        refundOrder.setHandler(null);

        // Create the RefundOrder, which fails.
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(refundOrder);

        restRefundOrderMockMvc.perform(post("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isBadRequest());

        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApplicantIsRequired() throws Exception {
        int databaseSizeBeforeTest = refundOrderRepository.findAll().size();
        // set the field null
        refundOrder.setApplicant(null);

        // Create the RefundOrder, which fails.
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(refundOrder);

        restRefundOrderMockMvc.perform(post("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isBadRequest());

        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAppliedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = refundOrderRepository.findAll().size();
        // set the field null
        refundOrder.setAppliedDate(null);

        // Create the RefundOrder, which fails.
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(refundOrder);

        restRefundOrderMockMvc.perform(post("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isBadRequest());

        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefundOrders() throws Exception {
        // Initialize the database
        refundOrderRepository.saveAndFlush(refundOrder);

        // Get all the refundOrderList
        restRefundOrderMockMvc.perform(get("/api/refund-orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refundOrder.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].exceptionDate").value(hasItem(DEFAULT_EXCEPTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].handler").value(hasItem(DEFAULT_HANDLER)))
            .andExpect(jsonPath("$.[*].handledDate").value(hasItem(DEFAULT_HANDLED_DATE.toString())))
            .andExpect(jsonPath("$.[*].handledDesc").value(hasItem(DEFAULT_HANDLED_DESC)))
            .andExpect(jsonPath("$.[*].applicant").value(hasItem(DEFAULT_APPLICANT)))
            .andExpect(jsonPath("$.[*].appliedDate").value(hasItem(DEFAULT_APPLIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].refundDesc").value(hasItem(DEFAULT_REFUND_DESC)))
            .andExpect(jsonPath("$.[*].refundedBy").value(hasItem(DEFAULT_REFUNDED_BY)))
            .andExpect(jsonPath("$.[*].refundedDate").value(hasItem(DEFAULT_REFUNDED_DATE.toString())))
            .andExpect(jsonPath("$.[*].refundAmount").value(hasItem(DEFAULT_REFUND_AMOUNT)))
            .andExpect(jsonPath("$.[*].reply").value(hasItem(DEFAULT_REPLY)))
            .andExpect(jsonPath("$.[*].passed").value(hasItem(DEFAULT_PASSED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getRefundOrder() throws Exception {
        // Initialize the database
        refundOrderRepository.saveAndFlush(refundOrder);

        // Get the refundOrder
        restRefundOrderMockMvc.perform(get("/api/refund-orders/{id}", refundOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(refundOrder.getId().intValue()))
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
            .andExpect(jsonPath("$.exceptionDate").value(DEFAULT_EXCEPTION_DATE.toString()))
            .andExpect(jsonPath("$.handler").value(DEFAULT_HANDLER))
            .andExpect(jsonPath("$.handledDate").value(DEFAULT_HANDLED_DATE.toString()))
            .andExpect(jsonPath("$.handledDesc").value(DEFAULT_HANDLED_DESC))
            .andExpect(jsonPath("$.applicant").value(DEFAULT_APPLICANT))
            .andExpect(jsonPath("$.appliedDate").value(DEFAULT_APPLIED_DATE.toString()))
            .andExpect(jsonPath("$.refundDesc").value(DEFAULT_REFUND_DESC))
            .andExpect(jsonPath("$.refundedBy").value(DEFAULT_REFUNDED_BY))
            .andExpect(jsonPath("$.refundedDate").value(DEFAULT_REFUNDED_DATE.toString()))
            .andExpect(jsonPath("$.refundAmount").value(DEFAULT_REFUND_AMOUNT))
            .andExpect(jsonPath("$.reply").value(DEFAULT_REPLY))
            .andExpect(jsonPath("$.passed").value(DEFAULT_PASSED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRefundOrder() throws Exception {
        // Get the refundOrder
        restRefundOrderMockMvc.perform(get("/api/refund-orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefundOrder() throws Exception {
        // Initialize the database
        refundOrderRepository.saveAndFlush(refundOrder);

        int databaseSizeBeforeUpdate = refundOrderRepository.findAll().size();

        // Update the refundOrder
        RefundOrder updatedRefundOrder = refundOrderRepository.findById(refundOrder.getId()).get();
        // Disconnect from session so that the updates on updatedRefundOrder are not directly saved in db
        em.detach(updatedRefundOrder);
        updatedRefundOrder
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
            .exceptionDate(UPDATED_EXCEPTION_DATE)
            .handler(UPDATED_HANDLER)
            .handledDate(UPDATED_HANDLED_DATE)
            .handledDesc(UPDATED_HANDLED_DESC)
            .applicant(UPDATED_APPLICANT)
            .appliedDate(UPDATED_APPLIED_DATE)
            .refundDesc(UPDATED_REFUND_DESC)
            .refundedBy(UPDATED_REFUNDED_BY)
            .refundedDate(UPDATED_REFUNDED_DATE)
            .refundAmount(UPDATED_REFUND_AMOUNT)
            .reply(UPDATED_REPLY)
            .passed(UPDATED_PASSED);
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(updatedRefundOrder);

        restRefundOrderMockMvc.perform(put("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isOk());

        // Validate the RefundOrder in the database
        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeUpdate);
        RefundOrder testRefundOrder = refundOrderList.get(refundOrderList.size() - 1);
        assertThat(testRefundOrder.getSn()).isEqualTo(UPDATED_SN);
        assertThat(testRefundOrder.getMemberName()).isEqualTo(UPDATED_MEMBER_NAME);
        assertThat(testRefundOrder.getMemberSN()).isEqualTo(UPDATED_MEMBER_SN);
        assertThat(testRefundOrder.getOrderType()).isEqualTo(UPDATED_ORDER_TYPE);
        assertThat(testRefundOrder.getShopSN()).isEqualTo(UPDATED_SHOP_SN);
        assertThat(testRefundOrder.getShopName()).isEqualTo(UPDATED_SHOP_NAME);
        assertThat(testRefundOrder.getPriceTotal()).isEqualTo(UPDATED_PRICE_TOTAL);
        assertThat(testRefundOrder.getCardReduce()).isEqualTo(UPDATED_CARD_REDUCE);
        assertThat(testRefundOrder.getCards()).isEqualTo(UPDATED_CARDS);
        assertThat(testRefundOrder.getRewardPointsReduce()).isEqualTo(UPDATED_REWARD_POINTS_REDUCE);
        assertThat(testRefundOrder.getPaymentTotal()).isEqualTo(UPDATED_PAYMENT_TOTAL);
        assertThat(testRefundOrder.getDistributionPlatform()).isEqualTo(UPDATED_DISTRIBUTION_PLATFORM);
        assertThat(testRefundOrder.getDeliveryNo()).isEqualTo(UPDATED_DELIVERY_NO);
        assertThat(testRefundOrder.getDeliveryStatus()).isEqualTo(UPDATED_DELIVERY_STATUS);
        assertThat(testRefundOrder.getDeliveryDesc()).isEqualTo(UPDATED_DELIVERY_DESC);
        assertThat(testRefundOrder.getDeliverier()).isEqualTo(UPDATED_DELIVERIER);
        assertThat(testRefundOrder.getDeliverierMobile()).isEqualTo(UPDATED_DELIVERIER_MOBILE);
        assertThat(testRefundOrder.getDeliveryDeductFee()).isEqualTo(UPDATED_DELIVERY_DEDUCT_FEE);
        assertThat(testRefundOrder.getDeliveryFee()).isEqualTo(UPDATED_DELIVERY_FEE);
        assertThat(testRefundOrder.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testRefundOrder.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testRefundOrder.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testRefundOrder.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testRefundOrder.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testRefundOrder.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testRefundOrder.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testRefundOrder.getOffsetType()).isEqualTo(UPDATED_OFFSET_TYPE);
        assertThat(testRefundOrder.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testRefundOrder.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testRefundOrder.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testRefundOrder.getPackingFee()).isEqualTo(UPDATED_PACKING_FEE);
        assertThat(testRefundOrder.getPaymentMode()).isEqualTo(UPDATED_PAYMENT_MODE);
        assertThat(testRefundOrder.getDiningDate()).isEqualTo(UPDATED_DINING_DATE);
        assertThat(testRefundOrder.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testRefundOrder.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRefundOrder.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testRefundOrder.getPaidDate()).isEqualTo(UPDATED_PAID_DATE);
        assertThat(testRefundOrder.getCompletedDate()).isEqualTo(UPDATED_COMPLETED_DATE);
        assertThat(testRefundOrder.getExceptionDate()).isEqualTo(UPDATED_EXCEPTION_DATE);
        assertThat(testRefundOrder.getHandler()).isEqualTo(UPDATED_HANDLER);
        assertThat(testRefundOrder.getHandledDate()).isEqualTo(UPDATED_HANDLED_DATE);
        assertThat(testRefundOrder.getHandledDesc()).isEqualTo(UPDATED_HANDLED_DESC);
        assertThat(testRefundOrder.getApplicant()).isEqualTo(UPDATED_APPLICANT);
        assertThat(testRefundOrder.getAppliedDate()).isEqualTo(UPDATED_APPLIED_DATE);
        assertThat(testRefundOrder.getRefundDesc()).isEqualTo(UPDATED_REFUND_DESC);
        assertThat(testRefundOrder.getRefundedBy()).isEqualTo(UPDATED_REFUNDED_BY);
        assertThat(testRefundOrder.getRefundedDate()).isEqualTo(UPDATED_REFUNDED_DATE);
        assertThat(testRefundOrder.getRefundAmount()).isEqualTo(UPDATED_REFUND_AMOUNT);
        assertThat(testRefundOrder.getReply()).isEqualTo(UPDATED_REPLY);
        assertThat(testRefundOrder.isPassed()).isEqualTo(UPDATED_PASSED);
    }

    @Test
    @Transactional
    public void updateNonExistingRefundOrder() throws Exception {
        int databaseSizeBeforeUpdate = refundOrderRepository.findAll().size();

        // Create the RefundOrder
        RefundOrderDTO refundOrderDTO = refundOrderMapper.toDto(refundOrder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefundOrderMockMvc.perform(put("/api/refund-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refundOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefundOrder in the database
        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefundOrder() throws Exception {
        // Initialize the database
        refundOrderRepository.saveAndFlush(refundOrder);

        int databaseSizeBeforeDelete = refundOrderRepository.findAll().size();

        // Delete the refundOrder
        restRefundOrderMockMvc.perform(delete("/api/refund-orders/{id}", refundOrder.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RefundOrder> refundOrderList = refundOrderRepository.findAll();
        assertThat(refundOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
