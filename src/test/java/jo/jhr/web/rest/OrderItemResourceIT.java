package jo.jhr.web.rest;

import jo.jhr.RedisTestContainerExtension;
import jo.jhr.JhrApp;
import jo.jhr.domain.OrderItem;
import jo.jhr.repository.OrderItemRepository;
import jo.jhr.service.OrderItemService;
import jo.jhr.service.dto.OrderItemDTO;
import jo.jhr.service.mapper.OrderItemMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OrderItemResource} REST controller.
 */
@SpringBootTest(classes = JhrApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class OrderItemResourceIT {

    private static final String DEFAULT_ORDER_SN = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_SN = "BBBBBBBBBB";

    private static final String DEFAULT_FOOD_CATEGORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FOOD_CATEGORY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FOOD_CATEGORY_SN = "AAAAA";
    private static final String UPDATED_FOOD_CATEGORY_SN = "BBBBB";

    private static final String DEFAULT_FOOD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FOOD_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FOOD_SN = "AAAAAAAAAA";
    private static final String UPDATED_FOOD_SN = "BBBBBBBBBB";

    private static final String DEFAULT_FOOD_THUMBNAIL = "AAAAAAAAAA";
    private static final String UPDATED_FOOD_THUMBNAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FOOD_PICTURE = "AAAAAAAAAA";
    private static final String UPDATED_FOOD_PICTURE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRICE = 0;
    private static final Integer UPDATED_PRICE = 1;

    private static final Integer DEFAULT_ORIGINAL_PRICE = 0;
    private static final Integer UPDATED_ORIGINAL_PRICE = 1;

    private static final Integer DEFAULT_COST = 0;
    private static final Integer UPDATED_COST = 1;

    private static final Integer DEFAULT_PACKING_FEE = 0;
    private static final Integer UPDATED_PACKING_FEE = 1;

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final String DEFAULT_COUPON = "AAAAAAAAAA";
    private static final String UPDATED_COUPON = "BBBBBBBBBB";

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrderItemMockMvc;

    private OrderItem orderItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderItem createEntity(EntityManager em) {
        OrderItem orderItem = new OrderItem()
            .orderSN(DEFAULT_ORDER_SN)
            .foodCategoryName(DEFAULT_FOOD_CATEGORY_NAME)
            .foodCategorySN(DEFAULT_FOOD_CATEGORY_SN)
            .foodName(DEFAULT_FOOD_NAME)
            .foodSN(DEFAULT_FOOD_SN)
            .foodThumbnail(DEFAULT_FOOD_THUMBNAIL)
            .foodPicture(DEFAULT_FOOD_PICTURE)
            .price(DEFAULT_PRICE)
            .originalPrice(DEFAULT_ORIGINAL_PRICE)
            .cost(DEFAULT_COST)
            .packingFee(DEFAULT_PACKING_FEE)
            .desc(DEFAULT_DESC)
            .quantity(DEFAULT_QUANTITY)
            .coupon(DEFAULT_COUPON);
        return orderItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderItem createUpdatedEntity(EntityManager em) {
        OrderItem orderItem = new OrderItem()
            .orderSN(UPDATED_ORDER_SN)
            .foodCategoryName(UPDATED_FOOD_CATEGORY_NAME)
            .foodCategorySN(UPDATED_FOOD_CATEGORY_SN)
            .foodName(UPDATED_FOOD_NAME)
            .foodSN(UPDATED_FOOD_SN)
            .foodThumbnail(UPDATED_FOOD_THUMBNAIL)
            .foodPicture(UPDATED_FOOD_PICTURE)
            .price(UPDATED_PRICE)
            .originalPrice(UPDATED_ORIGINAL_PRICE)
            .cost(UPDATED_COST)
            .packingFee(UPDATED_PACKING_FEE)
            .desc(UPDATED_DESC)
            .quantity(UPDATED_QUANTITY)
            .coupon(UPDATED_COUPON);
        return orderItem;
    }

    @BeforeEach
    public void initTest() {
        orderItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderItem() throws Exception {
        int databaseSizeBeforeCreate = orderItemRepository.findAll().size();

        // Create the OrderItem
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(orderItem);
        restOrderItemMockMvc.perform(post("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderItem in the database
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeCreate + 1);
        OrderItem testOrderItem = orderItemList.get(orderItemList.size() - 1);
        assertThat(testOrderItem.getOrderSN()).isEqualTo(DEFAULT_ORDER_SN);
        assertThat(testOrderItem.getFoodCategoryName()).isEqualTo(DEFAULT_FOOD_CATEGORY_NAME);
        assertThat(testOrderItem.getFoodCategorySN()).isEqualTo(DEFAULT_FOOD_CATEGORY_SN);
        assertThat(testOrderItem.getFoodName()).isEqualTo(DEFAULT_FOOD_NAME);
        assertThat(testOrderItem.getFoodSN()).isEqualTo(DEFAULT_FOOD_SN);
        assertThat(testOrderItem.getFoodThumbnail()).isEqualTo(DEFAULT_FOOD_THUMBNAIL);
        assertThat(testOrderItem.getFoodPicture()).isEqualTo(DEFAULT_FOOD_PICTURE);
        assertThat(testOrderItem.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testOrderItem.getOriginalPrice()).isEqualTo(DEFAULT_ORIGINAL_PRICE);
        assertThat(testOrderItem.getCost()).isEqualTo(DEFAULT_COST);
        assertThat(testOrderItem.getPackingFee()).isEqualTo(DEFAULT_PACKING_FEE);
        assertThat(testOrderItem.getDesc()).isEqualTo(DEFAULT_DESC);
        assertThat(testOrderItem.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testOrderItem.getCoupon()).isEqualTo(DEFAULT_COUPON);
    }

    @Test
    @Transactional
    public void createOrderItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderItemRepository.findAll().size();

        // Create the OrderItem with an existing ID
        orderItem.setId(1L);
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(orderItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderItemMockMvc.perform(post("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderItem in the database
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOrderSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderItemRepository.findAll().size();
        // set the field null
        orderItem.setOrderSN(null);

        // Create the OrderItem, which fails.
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(orderItem);

        restOrderItemMockMvc.perform(post("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isBadRequest());

        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFoodCategoryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderItemRepository.findAll().size();
        // set the field null
        orderItem.setFoodCategoryName(null);

        // Create the OrderItem, which fails.
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(orderItem);

        restOrderItemMockMvc.perform(post("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isBadRequest());

        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFoodCategorySNIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderItemRepository.findAll().size();
        // set the field null
        orderItem.setFoodCategorySN(null);

        // Create the OrderItem, which fails.
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(orderItem);

        restOrderItemMockMvc.perform(post("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isBadRequest());

        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFoodNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderItemRepository.findAll().size();
        // set the field null
        orderItem.setFoodName(null);

        // Create the OrderItem, which fails.
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(orderItem);

        restOrderItemMockMvc.perform(post("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isBadRequest());

        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFoodSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderItemRepository.findAll().size();
        // set the field null
        orderItem.setFoodSN(null);

        // Create the OrderItem, which fails.
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(orderItem);

        restOrderItemMockMvc.perform(post("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isBadRequest());

        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderItemRepository.findAll().size();
        // set the field null
        orderItem.setPrice(null);

        // Create the OrderItem, which fails.
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(orderItem);

        restOrderItemMockMvc.perform(post("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isBadRequest());

        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderItemRepository.findAll().size();
        // set the field null
        orderItem.setQuantity(null);

        // Create the OrderItem, which fails.
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(orderItem);

        restOrderItemMockMvc.perform(post("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isBadRequest());

        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrderItems() throws Exception {
        // Initialize the database
        orderItemRepository.saveAndFlush(orderItem);

        // Get all the orderItemList
        restOrderItemMockMvc.perform(get("/api/order-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderSN").value(hasItem(DEFAULT_ORDER_SN)))
            .andExpect(jsonPath("$.[*].foodCategoryName").value(hasItem(DEFAULT_FOOD_CATEGORY_NAME)))
            .andExpect(jsonPath("$.[*].foodCategorySN").value(hasItem(DEFAULT_FOOD_CATEGORY_SN)))
            .andExpect(jsonPath("$.[*].foodName").value(hasItem(DEFAULT_FOOD_NAME)))
            .andExpect(jsonPath("$.[*].foodSN").value(hasItem(DEFAULT_FOOD_SN)))
            .andExpect(jsonPath("$.[*].foodThumbnail").value(hasItem(DEFAULT_FOOD_THUMBNAIL)))
            .andExpect(jsonPath("$.[*].foodPicture").value(hasItem(DEFAULT_FOOD_PICTURE)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE)))
            .andExpect(jsonPath("$.[*].originalPrice").value(hasItem(DEFAULT_ORIGINAL_PRICE)))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST)))
            .andExpect(jsonPath("$.[*].packingFee").value(hasItem(DEFAULT_PACKING_FEE)))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].coupon").value(hasItem(DEFAULT_COUPON)));
    }
    
    @Test
    @Transactional
    public void getOrderItem() throws Exception {
        // Initialize the database
        orderItemRepository.saveAndFlush(orderItem);

        // Get the orderItem
        restOrderItemMockMvc.perform(get("/api/order-items/{id}", orderItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orderItem.getId().intValue()))
            .andExpect(jsonPath("$.orderSN").value(DEFAULT_ORDER_SN))
            .andExpect(jsonPath("$.foodCategoryName").value(DEFAULT_FOOD_CATEGORY_NAME))
            .andExpect(jsonPath("$.foodCategorySN").value(DEFAULT_FOOD_CATEGORY_SN))
            .andExpect(jsonPath("$.foodName").value(DEFAULT_FOOD_NAME))
            .andExpect(jsonPath("$.foodSN").value(DEFAULT_FOOD_SN))
            .andExpect(jsonPath("$.foodThumbnail").value(DEFAULT_FOOD_THUMBNAIL))
            .andExpect(jsonPath("$.foodPicture").value(DEFAULT_FOOD_PICTURE))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE))
            .andExpect(jsonPath("$.originalPrice").value(DEFAULT_ORIGINAL_PRICE))
            .andExpect(jsonPath("$.cost").value(DEFAULT_COST))
            .andExpect(jsonPath("$.packingFee").value(DEFAULT_PACKING_FEE))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.coupon").value(DEFAULT_COUPON));
    }

    @Test
    @Transactional
    public void getNonExistingOrderItem() throws Exception {
        // Get the orderItem
        restOrderItemMockMvc.perform(get("/api/order-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderItem() throws Exception {
        // Initialize the database
        orderItemRepository.saveAndFlush(orderItem);

        int databaseSizeBeforeUpdate = orderItemRepository.findAll().size();

        // Update the orderItem
        OrderItem updatedOrderItem = orderItemRepository.findById(orderItem.getId()).get();
        // Disconnect from session so that the updates on updatedOrderItem are not directly saved in db
        em.detach(updatedOrderItem);
        updatedOrderItem
            .orderSN(UPDATED_ORDER_SN)
            .foodCategoryName(UPDATED_FOOD_CATEGORY_NAME)
            .foodCategorySN(UPDATED_FOOD_CATEGORY_SN)
            .foodName(UPDATED_FOOD_NAME)
            .foodSN(UPDATED_FOOD_SN)
            .foodThumbnail(UPDATED_FOOD_THUMBNAIL)
            .foodPicture(UPDATED_FOOD_PICTURE)
            .price(UPDATED_PRICE)
            .originalPrice(UPDATED_ORIGINAL_PRICE)
            .cost(UPDATED_COST)
            .packingFee(UPDATED_PACKING_FEE)
            .desc(UPDATED_DESC)
            .quantity(UPDATED_QUANTITY)
            .coupon(UPDATED_COUPON);
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(updatedOrderItem);

        restOrderItemMockMvc.perform(put("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isOk());

        // Validate the OrderItem in the database
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeUpdate);
        OrderItem testOrderItem = orderItemList.get(orderItemList.size() - 1);
        assertThat(testOrderItem.getOrderSN()).isEqualTo(UPDATED_ORDER_SN);
        assertThat(testOrderItem.getFoodCategoryName()).isEqualTo(UPDATED_FOOD_CATEGORY_NAME);
        assertThat(testOrderItem.getFoodCategorySN()).isEqualTo(UPDATED_FOOD_CATEGORY_SN);
        assertThat(testOrderItem.getFoodName()).isEqualTo(UPDATED_FOOD_NAME);
        assertThat(testOrderItem.getFoodSN()).isEqualTo(UPDATED_FOOD_SN);
        assertThat(testOrderItem.getFoodThumbnail()).isEqualTo(UPDATED_FOOD_THUMBNAIL);
        assertThat(testOrderItem.getFoodPicture()).isEqualTo(UPDATED_FOOD_PICTURE);
        assertThat(testOrderItem.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testOrderItem.getOriginalPrice()).isEqualTo(UPDATED_ORIGINAL_PRICE);
        assertThat(testOrderItem.getCost()).isEqualTo(UPDATED_COST);
        assertThat(testOrderItem.getPackingFee()).isEqualTo(UPDATED_PACKING_FEE);
        assertThat(testOrderItem.getDesc()).isEqualTo(UPDATED_DESC);
        assertThat(testOrderItem.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testOrderItem.getCoupon()).isEqualTo(UPDATED_COUPON);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderItem() throws Exception {
        int databaseSizeBeforeUpdate = orderItemRepository.findAll().size();

        // Create the OrderItem
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(orderItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderItemMockMvc.perform(put("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderItem in the database
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderItem() throws Exception {
        // Initialize the database
        orderItemRepository.saveAndFlush(orderItem);

        int databaseSizeBeforeDelete = orderItemRepository.findAll().size();

        // Delete the orderItem
        restOrderItemMockMvc.perform(delete("/api/order-items/{id}", orderItem.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
