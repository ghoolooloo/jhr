package jo.jhr.web.rest;

import jo.jhr.RedisTestContainerExtension;
import jo.jhr.JhrApp;
import jo.jhr.domain.Order;
import jo.jhr.repository.OrderRepository;
import jo.jhr.service.OrderService;
import jo.jhr.service.dto.OrderDTO;
import jo.jhr.service.mapper.OrderMapper;

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
 * Integration tests for the {@link OrderResource} REST controller.
 */
@SpringBootTest(classes = JhrApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class OrderResourceIT {

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

    private static final Instant DEFAULT_EXPIRED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EXPIRED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrderMockMvc;

    private Order order;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Order createEntity(EntityManager em) {
        Order order = new Order()
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
            .expiredDate(DEFAULT_EXPIRED_DATE);
        return order;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Order createUpdatedEntity(EntityManager em) {
        Order order = new Order()
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
            .expiredDate(UPDATED_EXPIRED_DATE);
        return order;
    }

    @BeforeEach
    public void initTest() {
        order = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrder() throws Exception {
        int databaseSizeBeforeCreate = orderRepository.findAll().size();

        // Create the Order
        OrderDTO orderDTO = orderMapper.toDto(order);
        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isCreated());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeCreate + 1);
        Order testOrder = orderList.get(orderList.size() - 1);
        assertThat(testOrder.getSn()).isEqualTo(DEFAULT_SN);
        assertThat(testOrder.getMemberName()).isEqualTo(DEFAULT_MEMBER_NAME);
        assertThat(testOrder.getMemberSN()).isEqualTo(DEFAULT_MEMBER_SN);
        assertThat(testOrder.getOrderType()).isEqualTo(DEFAULT_ORDER_TYPE);
        assertThat(testOrder.getShopSN()).isEqualTo(DEFAULT_SHOP_SN);
        assertThat(testOrder.getShopName()).isEqualTo(DEFAULT_SHOP_NAME);
        assertThat(testOrder.getPriceTotal()).isEqualTo(DEFAULT_PRICE_TOTAL);
        assertThat(testOrder.getCardReduce()).isEqualTo(DEFAULT_CARD_REDUCE);
        assertThat(testOrder.getCards()).isEqualTo(DEFAULT_CARDS);
        assertThat(testOrder.getRewardPointsReduce()).isEqualTo(DEFAULT_REWARD_POINTS_REDUCE);
        assertThat(testOrder.getPaymentTotal()).isEqualTo(DEFAULT_PAYMENT_TOTAL);
        assertThat(testOrder.getDistributionPlatform()).isEqualTo(DEFAULT_DISTRIBUTION_PLATFORM);
        assertThat(testOrder.getDeliveryNo()).isEqualTo(DEFAULT_DELIVERY_NO);
        assertThat(testOrder.getDeliveryFee()).isEqualTo(DEFAULT_DELIVERY_FEE);
        assertThat(testOrder.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testOrder.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testOrder.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testOrder.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testOrder.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testOrder.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testOrder.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testOrder.getOffsetType()).isEqualTo(DEFAULT_OFFSET_TYPE);
        assertThat(testOrder.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testOrder.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testOrder.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testOrder.getPackingFee()).isEqualTo(DEFAULT_PACKING_FEE);
        assertThat(testOrder.getPaymentMode()).isEqualTo(DEFAULT_PAYMENT_MODE);
        assertThat(testOrder.getDiningDate()).isEqualTo(DEFAULT_DINING_DATE);
        assertThat(testOrder.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testOrder.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testOrder.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOrder.getPaidDate()).isEqualTo(DEFAULT_PAID_DATE);
        assertThat(testOrder.getExpiredDate()).isEqualTo(DEFAULT_EXPIRED_DATE);
    }

    @Test
    @Transactional
    public void createOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderRepository.findAll().size();

        // Create the Order with an existing ID
        order.setId(1L);
        OrderDTO orderDTO = orderMapper.toDto(order);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSnIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setSn(null);

        // Create the Order, which fails.
        OrderDTO orderDTO = orderMapper.toDto(order);

        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMemberNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setMemberName(null);

        // Create the Order, which fails.
        OrderDTO orderDTO = orderMapper.toDto(order);

        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMemberSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setMemberSN(null);

        // Create the Order, which fails.
        OrderDTO orderDTO = orderMapper.toDto(order);

        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setOrderType(null);

        // Create the Order, which fails.
        OrderDTO orderDTO = orderMapper.toDto(order);

        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShopSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setShopSN(null);

        // Create the Order, which fails.
        OrderDTO orderDTO = orderMapper.toDto(order);

        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShopNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setShopName(null);

        // Create the Order, which fails.
        OrderDTO orderDTO = orderMapper.toDto(order);

        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setPriceTotal(null);

        // Create the Order, which fails.
        OrderDTO orderDTO = orderMapper.toDto(order);

        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaymentTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setPaymentTotal(null);

        // Create the Order, which fails.
        OrderDTO orderDTO = orderMapper.toDto(order);

        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaymentModeIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setPaymentMode(null);

        // Create the Order, which fails.
        OrderDTO orderDTO = orderMapper.toDto(order);

        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDiningDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setDiningDate(null);

        // Create the Order, which fails.
        OrderDTO orderDTO = orderMapper.toDto(order);

        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setStatus(null);

        // Create the Order, which fails.
        OrderDTO orderDTO = orderMapper.toDto(order);

        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setCreatedDate(null);

        // Create the Order, which fails.
        OrderDTO orderDTO = orderMapper.toDto(order);

        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrders() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        // Get all the orderList
        restOrderMockMvc.perform(get("/api/orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(order.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].expiredDate").value(hasItem(DEFAULT_EXPIRED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getOrder() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        // Get the order
        restOrderMockMvc.perform(get("/api/orders/{id}", order.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(order.getId().intValue()))
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
            .andExpect(jsonPath("$.expiredDate").value(DEFAULT_EXPIRED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOrder() throws Exception {
        // Get the order
        restOrderMockMvc.perform(get("/api/orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrder() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        int databaseSizeBeforeUpdate = orderRepository.findAll().size();

        // Update the order
        Order updatedOrder = orderRepository.findById(order.getId()).get();
        // Disconnect from session so that the updates on updatedOrder are not directly saved in db
        em.detach(updatedOrder);
        updatedOrder
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
            .expiredDate(UPDATED_EXPIRED_DATE);
        OrderDTO orderDTO = orderMapper.toDto(updatedOrder);

        restOrderMockMvc.perform(put("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isOk());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
        Order testOrder = orderList.get(orderList.size() - 1);
        assertThat(testOrder.getSn()).isEqualTo(UPDATED_SN);
        assertThat(testOrder.getMemberName()).isEqualTo(UPDATED_MEMBER_NAME);
        assertThat(testOrder.getMemberSN()).isEqualTo(UPDATED_MEMBER_SN);
        assertThat(testOrder.getOrderType()).isEqualTo(UPDATED_ORDER_TYPE);
        assertThat(testOrder.getShopSN()).isEqualTo(UPDATED_SHOP_SN);
        assertThat(testOrder.getShopName()).isEqualTo(UPDATED_SHOP_NAME);
        assertThat(testOrder.getPriceTotal()).isEqualTo(UPDATED_PRICE_TOTAL);
        assertThat(testOrder.getCardReduce()).isEqualTo(UPDATED_CARD_REDUCE);
        assertThat(testOrder.getCards()).isEqualTo(UPDATED_CARDS);
        assertThat(testOrder.getRewardPointsReduce()).isEqualTo(UPDATED_REWARD_POINTS_REDUCE);
        assertThat(testOrder.getPaymentTotal()).isEqualTo(UPDATED_PAYMENT_TOTAL);
        assertThat(testOrder.getDistributionPlatform()).isEqualTo(UPDATED_DISTRIBUTION_PLATFORM);
        assertThat(testOrder.getDeliveryNo()).isEqualTo(UPDATED_DELIVERY_NO);
        assertThat(testOrder.getDeliveryFee()).isEqualTo(UPDATED_DELIVERY_FEE);
        assertThat(testOrder.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testOrder.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testOrder.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testOrder.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testOrder.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testOrder.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testOrder.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testOrder.getOffsetType()).isEqualTo(UPDATED_OFFSET_TYPE);
        assertThat(testOrder.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testOrder.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testOrder.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testOrder.getPackingFee()).isEqualTo(UPDATED_PACKING_FEE);
        assertThat(testOrder.getPaymentMode()).isEqualTo(UPDATED_PAYMENT_MODE);
        assertThat(testOrder.getDiningDate()).isEqualTo(UPDATED_DINING_DATE);
        assertThat(testOrder.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testOrder.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testOrder.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOrder.getPaidDate()).isEqualTo(UPDATED_PAID_DATE);
        assertThat(testOrder.getExpiredDate()).isEqualTo(UPDATED_EXPIRED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingOrder() throws Exception {
        int databaseSizeBeforeUpdate = orderRepository.findAll().size();

        // Create the Order
        OrderDTO orderDTO = orderMapper.toDto(order);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderMockMvc.perform(put("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrder() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        int databaseSizeBeforeDelete = orderRepository.findAll().size();

        // Delete the order
        restOrderMockMvc.perform(delete("/api/orders/{id}", order.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
