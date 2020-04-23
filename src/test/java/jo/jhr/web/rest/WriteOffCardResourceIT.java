package jo.jhr.web.rest;

import jo.jhr.RedisTestContainerExtension;
import jo.jhr.JhrApp;
import jo.jhr.domain.WriteOffCard;
import jo.jhr.repository.WriteOffCardRepository;
import jo.jhr.service.WriteOffCardService;
import jo.jhr.service.dto.WriteOffCardDTO;
import jo.jhr.service.mapper.WriteOffCardMapper;

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

import jo.jhr.domain.enumeration.CardType;
import jo.jhr.domain.enumeration.WriteOffStatus;
/**
 * Integration tests for the {@link WriteOffCardResource} REST controller.
 */
@SpringBootTest(classes = JhrApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class WriteOffCardResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CARD_SN = "AAAAAAAAAA";
    private static final String UPDATED_CARD_SN = "BBBBBBBBBB";

    private static final CardType DEFAULT_CARD_TYPE = CardType.CASH;
    private static final CardType UPDATED_CARD_TYPE = CardType.DISCOUNT;

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBNAIL = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL = "BBBBBBBBBB";

    private static final String DEFAULT_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_DETAILS = "BBBBBBBBBB";

    private static final Instant DEFAULT_VALID_PERIOD_BEGIN_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VALID_PERIOD_BEGIN_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_VALID_PERIOD_END_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VALID_PERIOD_END_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_CAN_USE_WITH_OTHER_CARD = false;
    private static final Boolean UPDATED_CAN_USE_WITH_OTHER_CARD = true;

    private static final String DEFAULT_ACCEPT_CATEGORIES = "AAAAAAAAAA";
    private static final String UPDATED_ACCEPT_CATEGORIES = "BBBBBBBBBB";

    private static final String DEFAULT_ACCEPT_SHOPS = "AAAAAAAAAA";
    private static final String UPDATED_ACCEPT_SHOPS = "BBBBBBBBBB";

    private static final Integer DEFAULT_LEAST_COST = 0;
    private static final Integer UPDATED_LEAST_COST = 1;

    private static final Integer DEFAULT_REDUCE_COST = 0;
    private static final Integer UPDATED_REDUCE_COST = 1;

    private static final Integer DEFAULT_DISCOUNT = 0;
    private static final Integer UPDATED_DISCOUNT = 1;

    private static final String DEFAULT_GIFT = "AAAAAAAAAA";
    private static final String UPDATED_GIFT = "BBBBBBBBBB";

    private static final Integer DEFAULT_GIFT_QUANTITY = 0;
    private static final Integer UPDATED_GIFT_QUANTITY = 1;

    private static final String DEFAULT_RECEIVED_BY = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_RECEIVED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RECEIVED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ORDER_SN = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_SN = "BBBBBBBBBB";

    private static final WriteOffStatus DEFAULT_STATUS = WriteOffStatus.CONSUMED;
    private static final WriteOffStatus UPDATED_STATUS = WriteOffStatus.EXPIRED;

    private static final Instant DEFAULT_WRITE_OFF_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_WRITE_OFF_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private WriteOffCardRepository writeOffCardRepository;

    @Autowired
    private WriteOffCardMapper writeOffCardMapper;

    @Autowired
    private WriteOffCardService writeOffCardService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWriteOffCardMockMvc;

    private WriteOffCard writeOffCard;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WriteOffCard createEntity(EntityManager em) {
        WriteOffCard writeOffCard = new WriteOffCard()
            .code(DEFAULT_CODE)
            .cardSN(DEFAULT_CARD_SN)
            .cardType(DEFAULT_CARD_TYPE)
            .title(DEFAULT_TITLE)
            .thumbnail(DEFAULT_THUMBNAIL)
            .details(DEFAULT_DETAILS)
            .validPeriodBeginAt(DEFAULT_VALID_PERIOD_BEGIN_AT)
            .validPeriodEndAt(DEFAULT_VALID_PERIOD_END_AT)
            .canUseWithOtherCard(DEFAULT_CAN_USE_WITH_OTHER_CARD)
            .acceptCategories(DEFAULT_ACCEPT_CATEGORIES)
            .acceptShops(DEFAULT_ACCEPT_SHOPS)
            .leastCost(DEFAULT_LEAST_COST)
            .reduceCost(DEFAULT_REDUCE_COST)
            .discount(DEFAULT_DISCOUNT)
            .gift(DEFAULT_GIFT)
            .giftQuantity(DEFAULT_GIFT_QUANTITY)
            .receivedBy(DEFAULT_RECEIVED_BY)
            .receivedDate(DEFAULT_RECEIVED_DATE)
            .orderSN(DEFAULT_ORDER_SN)
            .status(DEFAULT_STATUS)
            .writeOffDate(DEFAULT_WRITE_OFF_DATE);
        return writeOffCard;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WriteOffCard createUpdatedEntity(EntityManager em) {
        WriteOffCard writeOffCard = new WriteOffCard()
            .code(UPDATED_CODE)
            .cardSN(UPDATED_CARD_SN)
            .cardType(UPDATED_CARD_TYPE)
            .title(UPDATED_TITLE)
            .thumbnail(UPDATED_THUMBNAIL)
            .details(UPDATED_DETAILS)
            .validPeriodBeginAt(UPDATED_VALID_PERIOD_BEGIN_AT)
            .validPeriodEndAt(UPDATED_VALID_PERIOD_END_AT)
            .canUseWithOtherCard(UPDATED_CAN_USE_WITH_OTHER_CARD)
            .acceptCategories(UPDATED_ACCEPT_CATEGORIES)
            .acceptShops(UPDATED_ACCEPT_SHOPS)
            .leastCost(UPDATED_LEAST_COST)
            .reduceCost(UPDATED_REDUCE_COST)
            .discount(UPDATED_DISCOUNT)
            .gift(UPDATED_GIFT)
            .giftQuantity(UPDATED_GIFT_QUANTITY)
            .receivedBy(UPDATED_RECEIVED_BY)
            .receivedDate(UPDATED_RECEIVED_DATE)
            .orderSN(UPDATED_ORDER_SN)
            .status(UPDATED_STATUS)
            .writeOffDate(UPDATED_WRITE_OFF_DATE);
        return writeOffCard;
    }

    @BeforeEach
    public void initTest() {
        writeOffCard = createEntity(em);
    }

    @Test
    @Transactional
    public void createWriteOffCard() throws Exception {
        int databaseSizeBeforeCreate = writeOffCardRepository.findAll().size();

        // Create the WriteOffCard
        WriteOffCardDTO writeOffCardDTO = writeOffCardMapper.toDto(writeOffCard);
        restWriteOffCardMockMvc.perform(post("/api/write-off-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(writeOffCardDTO)))
            .andExpect(status().isCreated());

        // Validate the WriteOffCard in the database
        List<WriteOffCard> writeOffCardList = writeOffCardRepository.findAll();
        assertThat(writeOffCardList).hasSize(databaseSizeBeforeCreate + 1);
        WriteOffCard testWriteOffCard = writeOffCardList.get(writeOffCardList.size() - 1);
        assertThat(testWriteOffCard.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testWriteOffCard.getCardSN()).isEqualTo(DEFAULT_CARD_SN);
        assertThat(testWriteOffCard.getCardType()).isEqualTo(DEFAULT_CARD_TYPE);
        assertThat(testWriteOffCard.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testWriteOffCard.getThumbnail()).isEqualTo(DEFAULT_THUMBNAIL);
        assertThat(testWriteOffCard.getDetails()).isEqualTo(DEFAULT_DETAILS);
        assertThat(testWriteOffCard.getValidPeriodBeginAt()).isEqualTo(DEFAULT_VALID_PERIOD_BEGIN_AT);
        assertThat(testWriteOffCard.getValidPeriodEndAt()).isEqualTo(DEFAULT_VALID_PERIOD_END_AT);
        assertThat(testWriteOffCard.isCanUseWithOtherCard()).isEqualTo(DEFAULT_CAN_USE_WITH_OTHER_CARD);
        assertThat(testWriteOffCard.getAcceptCategories()).isEqualTo(DEFAULT_ACCEPT_CATEGORIES);
        assertThat(testWriteOffCard.getAcceptShops()).isEqualTo(DEFAULT_ACCEPT_SHOPS);
        assertThat(testWriteOffCard.getLeastCost()).isEqualTo(DEFAULT_LEAST_COST);
        assertThat(testWriteOffCard.getReduceCost()).isEqualTo(DEFAULT_REDUCE_COST);
        assertThat(testWriteOffCard.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testWriteOffCard.getGift()).isEqualTo(DEFAULT_GIFT);
        assertThat(testWriteOffCard.getGiftQuantity()).isEqualTo(DEFAULT_GIFT_QUANTITY);
        assertThat(testWriteOffCard.getReceivedBy()).isEqualTo(DEFAULT_RECEIVED_BY);
        assertThat(testWriteOffCard.getReceivedDate()).isEqualTo(DEFAULT_RECEIVED_DATE);
        assertThat(testWriteOffCard.getOrderSN()).isEqualTo(DEFAULT_ORDER_SN);
        assertThat(testWriteOffCard.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testWriteOffCard.getWriteOffDate()).isEqualTo(DEFAULT_WRITE_OFF_DATE);
    }

    @Test
    @Transactional
    public void createWriteOffCardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = writeOffCardRepository.findAll().size();

        // Create the WriteOffCard with an existing ID
        writeOffCard.setId(1L);
        WriteOffCardDTO writeOffCardDTO = writeOffCardMapper.toDto(writeOffCard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWriteOffCardMockMvc.perform(post("/api/write-off-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(writeOffCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WriteOffCard in the database
        List<WriteOffCard> writeOffCardList = writeOffCardRepository.findAll();
        assertThat(writeOffCardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = writeOffCardRepository.findAll().size();
        // set the field null
        writeOffCard.setCode(null);

        // Create the WriteOffCard, which fails.
        WriteOffCardDTO writeOffCardDTO = writeOffCardMapper.toDto(writeOffCard);

        restWriteOffCardMockMvc.perform(post("/api/write-off-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(writeOffCardDTO)))
            .andExpect(status().isBadRequest());

        List<WriteOffCard> writeOffCardList = writeOffCardRepository.findAll();
        assertThat(writeOffCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCardSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = writeOffCardRepository.findAll().size();
        // set the field null
        writeOffCard.setCardSN(null);

        // Create the WriteOffCard, which fails.
        WriteOffCardDTO writeOffCardDTO = writeOffCardMapper.toDto(writeOffCard);

        restWriteOffCardMockMvc.perform(post("/api/write-off-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(writeOffCardDTO)))
            .andExpect(status().isBadRequest());

        List<WriteOffCard> writeOffCardList = writeOffCardRepository.findAll();
        assertThat(writeOffCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCardTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = writeOffCardRepository.findAll().size();
        // set the field null
        writeOffCard.setCardType(null);

        // Create the WriteOffCard, which fails.
        WriteOffCardDTO writeOffCardDTO = writeOffCardMapper.toDto(writeOffCard);

        restWriteOffCardMockMvc.perform(post("/api/write-off-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(writeOffCardDTO)))
            .andExpect(status().isBadRequest());

        List<WriteOffCard> writeOffCardList = writeOffCardRepository.findAll();
        assertThat(writeOffCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = writeOffCardRepository.findAll().size();
        // set the field null
        writeOffCard.setTitle(null);

        // Create the WriteOffCard, which fails.
        WriteOffCardDTO writeOffCardDTO = writeOffCardMapper.toDto(writeOffCard);

        restWriteOffCardMockMvc.perform(post("/api/write-off-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(writeOffCardDTO)))
            .andExpect(status().isBadRequest());

        List<WriteOffCard> writeOffCardList = writeOffCardRepository.findAll();
        assertThat(writeOffCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCanUseWithOtherCardIsRequired() throws Exception {
        int databaseSizeBeforeTest = writeOffCardRepository.findAll().size();
        // set the field null
        writeOffCard.setCanUseWithOtherCard(null);

        // Create the WriteOffCard, which fails.
        WriteOffCardDTO writeOffCardDTO = writeOffCardMapper.toDto(writeOffCard);

        restWriteOffCardMockMvc.perform(post("/api/write-off-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(writeOffCardDTO)))
            .andExpect(status().isBadRequest());

        List<WriteOffCard> writeOffCardList = writeOffCardRepository.findAll();
        assertThat(writeOffCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReceivedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = writeOffCardRepository.findAll().size();
        // set the field null
        writeOffCard.setReceivedBy(null);

        // Create the WriteOffCard, which fails.
        WriteOffCardDTO writeOffCardDTO = writeOffCardMapper.toDto(writeOffCard);

        restWriteOffCardMockMvc.perform(post("/api/write-off-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(writeOffCardDTO)))
            .andExpect(status().isBadRequest());

        List<WriteOffCard> writeOffCardList = writeOffCardRepository.findAll();
        assertThat(writeOffCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReceivedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = writeOffCardRepository.findAll().size();
        // set the field null
        writeOffCard.setReceivedDate(null);

        // Create the WriteOffCard, which fails.
        WriteOffCardDTO writeOffCardDTO = writeOffCardMapper.toDto(writeOffCard);

        restWriteOffCardMockMvc.perform(post("/api/write-off-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(writeOffCardDTO)))
            .andExpect(status().isBadRequest());

        List<WriteOffCard> writeOffCardList = writeOffCardRepository.findAll();
        assertThat(writeOffCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = writeOffCardRepository.findAll().size();
        // set the field null
        writeOffCard.setStatus(null);

        // Create the WriteOffCard, which fails.
        WriteOffCardDTO writeOffCardDTO = writeOffCardMapper.toDto(writeOffCard);

        restWriteOffCardMockMvc.perform(post("/api/write-off-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(writeOffCardDTO)))
            .andExpect(status().isBadRequest());

        List<WriteOffCard> writeOffCardList = writeOffCardRepository.findAll();
        assertThat(writeOffCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWriteOffDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = writeOffCardRepository.findAll().size();
        // set the field null
        writeOffCard.setWriteOffDate(null);

        // Create the WriteOffCard, which fails.
        WriteOffCardDTO writeOffCardDTO = writeOffCardMapper.toDto(writeOffCard);

        restWriteOffCardMockMvc.perform(post("/api/write-off-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(writeOffCardDTO)))
            .andExpect(status().isBadRequest());

        List<WriteOffCard> writeOffCardList = writeOffCardRepository.findAll();
        assertThat(writeOffCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWriteOffCards() throws Exception {
        // Initialize the database
        writeOffCardRepository.saveAndFlush(writeOffCard);

        // Get all the writeOffCardList
        restWriteOffCardMockMvc.perform(get("/api/write-off-cards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(writeOffCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].cardSN").value(hasItem(DEFAULT_CARD_SN)))
            .andExpect(jsonPath("$.[*].cardType").value(hasItem(DEFAULT_CARD_TYPE.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].thumbnail").value(hasItem(DEFAULT_THUMBNAIL)))
            .andExpect(jsonPath("$.[*].details").value(hasItem(DEFAULT_DETAILS)))
            .andExpect(jsonPath("$.[*].validPeriodBeginAt").value(hasItem(DEFAULT_VALID_PERIOD_BEGIN_AT.toString())))
            .andExpect(jsonPath("$.[*].validPeriodEndAt").value(hasItem(DEFAULT_VALID_PERIOD_END_AT.toString())))
            .andExpect(jsonPath("$.[*].canUseWithOtherCard").value(hasItem(DEFAULT_CAN_USE_WITH_OTHER_CARD.booleanValue())))
            .andExpect(jsonPath("$.[*].acceptCategories").value(hasItem(DEFAULT_ACCEPT_CATEGORIES)))
            .andExpect(jsonPath("$.[*].acceptShops").value(hasItem(DEFAULT_ACCEPT_SHOPS)))
            .andExpect(jsonPath("$.[*].leastCost").value(hasItem(DEFAULT_LEAST_COST)))
            .andExpect(jsonPath("$.[*].reduceCost").value(hasItem(DEFAULT_REDUCE_COST)))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT)))
            .andExpect(jsonPath("$.[*].gift").value(hasItem(DEFAULT_GIFT)))
            .andExpect(jsonPath("$.[*].giftQuantity").value(hasItem(DEFAULT_GIFT_QUANTITY)))
            .andExpect(jsonPath("$.[*].receivedBy").value(hasItem(DEFAULT_RECEIVED_BY)))
            .andExpect(jsonPath("$.[*].receivedDate").value(hasItem(DEFAULT_RECEIVED_DATE.toString())))
            .andExpect(jsonPath("$.[*].orderSN").value(hasItem(DEFAULT_ORDER_SN)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].writeOffDate").value(hasItem(DEFAULT_WRITE_OFF_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getWriteOffCard() throws Exception {
        // Initialize the database
        writeOffCardRepository.saveAndFlush(writeOffCard);

        // Get the writeOffCard
        restWriteOffCardMockMvc.perform(get("/api/write-off-cards/{id}", writeOffCard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(writeOffCard.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.cardSN").value(DEFAULT_CARD_SN))
            .andExpect(jsonPath("$.cardType").value(DEFAULT_CARD_TYPE.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.thumbnail").value(DEFAULT_THUMBNAIL))
            .andExpect(jsonPath("$.details").value(DEFAULT_DETAILS))
            .andExpect(jsonPath("$.validPeriodBeginAt").value(DEFAULT_VALID_PERIOD_BEGIN_AT.toString()))
            .andExpect(jsonPath("$.validPeriodEndAt").value(DEFAULT_VALID_PERIOD_END_AT.toString()))
            .andExpect(jsonPath("$.canUseWithOtherCard").value(DEFAULT_CAN_USE_WITH_OTHER_CARD.booleanValue()))
            .andExpect(jsonPath("$.acceptCategories").value(DEFAULT_ACCEPT_CATEGORIES))
            .andExpect(jsonPath("$.acceptShops").value(DEFAULT_ACCEPT_SHOPS))
            .andExpect(jsonPath("$.leastCost").value(DEFAULT_LEAST_COST))
            .andExpect(jsonPath("$.reduceCost").value(DEFAULT_REDUCE_COST))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT))
            .andExpect(jsonPath("$.gift").value(DEFAULT_GIFT))
            .andExpect(jsonPath("$.giftQuantity").value(DEFAULT_GIFT_QUANTITY))
            .andExpect(jsonPath("$.receivedBy").value(DEFAULT_RECEIVED_BY))
            .andExpect(jsonPath("$.receivedDate").value(DEFAULT_RECEIVED_DATE.toString()))
            .andExpect(jsonPath("$.orderSN").value(DEFAULT_ORDER_SN))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.writeOffDate").value(DEFAULT_WRITE_OFF_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWriteOffCard() throws Exception {
        // Get the writeOffCard
        restWriteOffCardMockMvc.perform(get("/api/write-off-cards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWriteOffCard() throws Exception {
        // Initialize the database
        writeOffCardRepository.saveAndFlush(writeOffCard);

        int databaseSizeBeforeUpdate = writeOffCardRepository.findAll().size();

        // Update the writeOffCard
        WriteOffCard updatedWriteOffCard = writeOffCardRepository.findById(writeOffCard.getId()).get();
        // Disconnect from session so that the updates on updatedWriteOffCard are not directly saved in db
        em.detach(updatedWriteOffCard);
        updatedWriteOffCard
            .code(UPDATED_CODE)
            .cardSN(UPDATED_CARD_SN)
            .cardType(UPDATED_CARD_TYPE)
            .title(UPDATED_TITLE)
            .thumbnail(UPDATED_THUMBNAIL)
            .details(UPDATED_DETAILS)
            .validPeriodBeginAt(UPDATED_VALID_PERIOD_BEGIN_AT)
            .validPeriodEndAt(UPDATED_VALID_PERIOD_END_AT)
            .canUseWithOtherCard(UPDATED_CAN_USE_WITH_OTHER_CARD)
            .acceptCategories(UPDATED_ACCEPT_CATEGORIES)
            .acceptShops(UPDATED_ACCEPT_SHOPS)
            .leastCost(UPDATED_LEAST_COST)
            .reduceCost(UPDATED_REDUCE_COST)
            .discount(UPDATED_DISCOUNT)
            .gift(UPDATED_GIFT)
            .giftQuantity(UPDATED_GIFT_QUANTITY)
            .receivedBy(UPDATED_RECEIVED_BY)
            .receivedDate(UPDATED_RECEIVED_DATE)
            .orderSN(UPDATED_ORDER_SN)
            .status(UPDATED_STATUS)
            .writeOffDate(UPDATED_WRITE_OFF_DATE);
        WriteOffCardDTO writeOffCardDTO = writeOffCardMapper.toDto(updatedWriteOffCard);

        restWriteOffCardMockMvc.perform(put("/api/write-off-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(writeOffCardDTO)))
            .andExpect(status().isOk());

        // Validate the WriteOffCard in the database
        List<WriteOffCard> writeOffCardList = writeOffCardRepository.findAll();
        assertThat(writeOffCardList).hasSize(databaseSizeBeforeUpdate);
        WriteOffCard testWriteOffCard = writeOffCardList.get(writeOffCardList.size() - 1);
        assertThat(testWriteOffCard.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testWriteOffCard.getCardSN()).isEqualTo(UPDATED_CARD_SN);
        assertThat(testWriteOffCard.getCardType()).isEqualTo(UPDATED_CARD_TYPE);
        assertThat(testWriteOffCard.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testWriteOffCard.getThumbnail()).isEqualTo(UPDATED_THUMBNAIL);
        assertThat(testWriteOffCard.getDetails()).isEqualTo(UPDATED_DETAILS);
        assertThat(testWriteOffCard.getValidPeriodBeginAt()).isEqualTo(UPDATED_VALID_PERIOD_BEGIN_AT);
        assertThat(testWriteOffCard.getValidPeriodEndAt()).isEqualTo(UPDATED_VALID_PERIOD_END_AT);
        assertThat(testWriteOffCard.isCanUseWithOtherCard()).isEqualTo(UPDATED_CAN_USE_WITH_OTHER_CARD);
        assertThat(testWriteOffCard.getAcceptCategories()).isEqualTo(UPDATED_ACCEPT_CATEGORIES);
        assertThat(testWriteOffCard.getAcceptShops()).isEqualTo(UPDATED_ACCEPT_SHOPS);
        assertThat(testWriteOffCard.getLeastCost()).isEqualTo(UPDATED_LEAST_COST);
        assertThat(testWriteOffCard.getReduceCost()).isEqualTo(UPDATED_REDUCE_COST);
        assertThat(testWriteOffCard.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testWriteOffCard.getGift()).isEqualTo(UPDATED_GIFT);
        assertThat(testWriteOffCard.getGiftQuantity()).isEqualTo(UPDATED_GIFT_QUANTITY);
        assertThat(testWriteOffCard.getReceivedBy()).isEqualTo(UPDATED_RECEIVED_BY);
        assertThat(testWriteOffCard.getReceivedDate()).isEqualTo(UPDATED_RECEIVED_DATE);
        assertThat(testWriteOffCard.getOrderSN()).isEqualTo(UPDATED_ORDER_SN);
        assertThat(testWriteOffCard.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testWriteOffCard.getWriteOffDate()).isEqualTo(UPDATED_WRITE_OFF_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingWriteOffCard() throws Exception {
        int databaseSizeBeforeUpdate = writeOffCardRepository.findAll().size();

        // Create the WriteOffCard
        WriteOffCardDTO writeOffCardDTO = writeOffCardMapper.toDto(writeOffCard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWriteOffCardMockMvc.perform(put("/api/write-off-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(writeOffCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WriteOffCard in the database
        List<WriteOffCard> writeOffCardList = writeOffCardRepository.findAll();
        assertThat(writeOffCardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWriteOffCard() throws Exception {
        // Initialize the database
        writeOffCardRepository.saveAndFlush(writeOffCard);

        int databaseSizeBeforeDelete = writeOffCardRepository.findAll().size();

        // Delete the writeOffCard
        restWriteOffCardMockMvc.perform(delete("/api/write-off-cards/{id}", writeOffCard.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WriteOffCard> writeOffCardList = writeOffCardRepository.findAll();
        assertThat(writeOffCardList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
