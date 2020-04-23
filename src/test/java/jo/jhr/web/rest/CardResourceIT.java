package jo.jhr.web.rest;

import jo.jhr.RedisTestContainerExtension;
import jo.jhr.JhrApp;
import jo.jhr.domain.Card;
import jo.jhr.repository.CardRepository;
import jo.jhr.service.CardService;
import jo.jhr.service.dto.CardDTO;
import jo.jhr.service.mapper.CardMapper;

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
import jo.jhr.domain.enumeration.CardStatus;
/**
 * Integration tests for the {@link CardResource} REST controller.
 */
@SpringBootTest(classes = JhrApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class CardResourceIT {

    private static final String DEFAULT_SN = "AAAAAAAAAA";
    private static final String UPDATED_SN = "BBBBBBBBBB";

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

    private static final Integer DEFAULT_QUANTITY = 0;
    private static final Integer UPDATED_QUANTITY = 1;

    private static final Integer DEFAULT_RECEIVED_QUANTITY = 0;
    private static final Integer UPDATED_RECEIVED_QUANTITY = 1;

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

    private static final CardStatus DEFAULT_STATUS = CardStatus.NEW;
    private static final CardStatus UPDATED_STATUS = CardStatus.PUT;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DELIVERIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_DELIVERIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DELIVERIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private CardService cardService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCardMockMvc;

    private Card card;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Card createEntity(EntityManager em) {
        Card card = new Card()
            .sn(DEFAULT_SN)
            .cardType(DEFAULT_CARD_TYPE)
            .title(DEFAULT_TITLE)
            .thumbnail(DEFAULT_THUMBNAIL)
            .details(DEFAULT_DETAILS)
            .validPeriodBeginAt(DEFAULT_VALID_PERIOD_BEGIN_AT)
            .validPeriodEndAt(DEFAULT_VALID_PERIOD_END_AT)
            .quantity(DEFAULT_QUANTITY)
            .receivedQuantity(DEFAULT_RECEIVED_QUANTITY)
            .canUseWithOtherCard(DEFAULT_CAN_USE_WITH_OTHER_CARD)
            .acceptCategories(DEFAULT_ACCEPT_CATEGORIES)
            .acceptShops(DEFAULT_ACCEPT_SHOPS)
            .leastCost(DEFAULT_LEAST_COST)
            .reduceCost(DEFAULT_REDUCE_COST)
            .discount(DEFAULT_DISCOUNT)
            .gift(DEFAULT_GIFT)
            .giftQuantity(DEFAULT_GIFT_QUANTITY)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .deliveriedBy(DEFAULT_DELIVERIED_BY)
            .deliveriedDate(DEFAULT_DELIVERIED_DATE)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
        return card;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Card createUpdatedEntity(EntityManager em) {
        Card card = new Card()
            .sn(UPDATED_SN)
            .cardType(UPDATED_CARD_TYPE)
            .title(UPDATED_TITLE)
            .thumbnail(UPDATED_THUMBNAIL)
            .details(UPDATED_DETAILS)
            .validPeriodBeginAt(UPDATED_VALID_PERIOD_BEGIN_AT)
            .validPeriodEndAt(UPDATED_VALID_PERIOD_END_AT)
            .quantity(UPDATED_QUANTITY)
            .receivedQuantity(UPDATED_RECEIVED_QUANTITY)
            .canUseWithOtherCard(UPDATED_CAN_USE_WITH_OTHER_CARD)
            .acceptCategories(UPDATED_ACCEPT_CATEGORIES)
            .acceptShops(UPDATED_ACCEPT_SHOPS)
            .leastCost(UPDATED_LEAST_COST)
            .reduceCost(UPDATED_REDUCE_COST)
            .discount(UPDATED_DISCOUNT)
            .gift(UPDATED_GIFT)
            .giftQuantity(UPDATED_GIFT_QUANTITY)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .deliveriedBy(UPDATED_DELIVERIED_BY)
            .deliveriedDate(UPDATED_DELIVERIED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        return card;
    }

    @BeforeEach
    public void initTest() {
        card = createEntity(em);
    }

    @Test
    @Transactional
    public void createCard() throws Exception {
        int databaseSizeBeforeCreate = cardRepository.findAll().size();

        // Create the Card
        CardDTO cardDTO = cardMapper.toDto(card);
        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isCreated());

        // Validate the Card in the database
        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeCreate + 1);
        Card testCard = cardList.get(cardList.size() - 1);
        assertThat(testCard.getSn()).isEqualTo(DEFAULT_SN);
        assertThat(testCard.getCardType()).isEqualTo(DEFAULT_CARD_TYPE);
        assertThat(testCard.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testCard.getThumbnail()).isEqualTo(DEFAULT_THUMBNAIL);
        assertThat(testCard.getDetails()).isEqualTo(DEFAULT_DETAILS);
        assertThat(testCard.getValidPeriodBeginAt()).isEqualTo(DEFAULT_VALID_PERIOD_BEGIN_AT);
        assertThat(testCard.getValidPeriodEndAt()).isEqualTo(DEFAULT_VALID_PERIOD_END_AT);
        assertThat(testCard.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testCard.getReceivedQuantity()).isEqualTo(DEFAULT_RECEIVED_QUANTITY);
        assertThat(testCard.isCanUseWithOtherCard()).isEqualTo(DEFAULT_CAN_USE_WITH_OTHER_CARD);
        assertThat(testCard.getAcceptCategories()).isEqualTo(DEFAULT_ACCEPT_CATEGORIES);
        assertThat(testCard.getAcceptShops()).isEqualTo(DEFAULT_ACCEPT_SHOPS);
        assertThat(testCard.getLeastCost()).isEqualTo(DEFAULT_LEAST_COST);
        assertThat(testCard.getReduceCost()).isEqualTo(DEFAULT_REDUCE_COST);
        assertThat(testCard.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testCard.getGift()).isEqualTo(DEFAULT_GIFT);
        assertThat(testCard.getGiftQuantity()).isEqualTo(DEFAULT_GIFT_QUANTITY);
        assertThat(testCard.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCard.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCard.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCard.getDeliveriedBy()).isEqualTo(DEFAULT_DELIVERIED_BY);
        assertThat(testCard.getDeliveriedDate()).isEqualTo(DEFAULT_DELIVERIED_DATE);
        assertThat(testCard.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testCard.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void createCardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cardRepository.findAll().size();

        // Create the Card with an existing ID
        card.setId(1L);
        CardDTO cardDTO = cardMapper.toDto(card);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Card in the database
        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSnIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardRepository.findAll().size();
        // set the field null
        card.setSn(null);

        // Create the Card, which fails.
        CardDTO cardDTO = cardMapper.toDto(card);

        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCardTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardRepository.findAll().size();
        // set the field null
        card.setCardType(null);

        // Create the Card, which fails.
        CardDTO cardDTO = cardMapper.toDto(card);

        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardRepository.findAll().size();
        // set the field null
        card.setTitle(null);

        // Create the Card, which fails.
        CardDTO cardDTO = cardMapper.toDto(card);

        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCanUseWithOtherCardIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardRepository.findAll().size();
        // set the field null
        card.setCanUseWithOtherCard(null);

        // Create the Card, which fails.
        CardDTO cardDTO = cardMapper.toDto(card);

        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardRepository.findAll().size();
        // set the field null
        card.setStatus(null);

        // Create the Card, which fails.
        CardDTO cardDTO = cardMapper.toDto(card);

        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardRepository.findAll().size();
        // set the field null
        card.setCreatedBy(null);

        // Create the Card, which fails.
        CardDTO cardDTO = cardMapper.toDto(card);

        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardRepository.findAll().size();
        // set the field null
        card.setCreatedDate(null);

        // Create the Card, which fails.
        CardDTO cardDTO = cardMapper.toDto(card);

        restCardMockMvc.perform(post("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCards() throws Exception {
        // Initialize the database
        cardRepository.saveAndFlush(card);

        // Get all the cardList
        restCardMockMvc.perform(get("/api/cards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(card.getId().intValue())))
            .andExpect(jsonPath("$.[*].sn").value(hasItem(DEFAULT_SN)))
            .andExpect(jsonPath("$.[*].cardType").value(hasItem(DEFAULT_CARD_TYPE.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].thumbnail").value(hasItem(DEFAULT_THUMBNAIL)))
            .andExpect(jsonPath("$.[*].details").value(hasItem(DEFAULT_DETAILS)))
            .andExpect(jsonPath("$.[*].validPeriodBeginAt").value(hasItem(DEFAULT_VALID_PERIOD_BEGIN_AT.toString())))
            .andExpect(jsonPath("$.[*].validPeriodEndAt").value(hasItem(DEFAULT_VALID_PERIOD_END_AT.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].receivedQuantity").value(hasItem(DEFAULT_RECEIVED_QUANTITY)))
            .andExpect(jsonPath("$.[*].canUseWithOtherCard").value(hasItem(DEFAULT_CAN_USE_WITH_OTHER_CARD.booleanValue())))
            .andExpect(jsonPath("$.[*].acceptCategories").value(hasItem(DEFAULT_ACCEPT_CATEGORIES)))
            .andExpect(jsonPath("$.[*].acceptShops").value(hasItem(DEFAULT_ACCEPT_SHOPS)))
            .andExpect(jsonPath("$.[*].leastCost").value(hasItem(DEFAULT_LEAST_COST)))
            .andExpect(jsonPath("$.[*].reduceCost").value(hasItem(DEFAULT_REDUCE_COST)))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT)))
            .andExpect(jsonPath("$.[*].gift").value(hasItem(DEFAULT_GIFT)))
            .andExpect(jsonPath("$.[*].giftQuantity").value(hasItem(DEFAULT_GIFT_QUANTITY)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].deliveriedBy").value(hasItem(DEFAULT_DELIVERIED_BY)))
            .andExpect(jsonPath("$.[*].deliveriedDate").value(hasItem(DEFAULT_DELIVERIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));
    }
    
    @Test
    @Transactional
    public void getCard() throws Exception {
        // Initialize the database
        cardRepository.saveAndFlush(card);

        // Get the card
        restCardMockMvc.perform(get("/api/cards/{id}", card.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(card.getId().intValue()))
            .andExpect(jsonPath("$.sn").value(DEFAULT_SN))
            .andExpect(jsonPath("$.cardType").value(DEFAULT_CARD_TYPE.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.thumbnail").value(DEFAULT_THUMBNAIL))
            .andExpect(jsonPath("$.details").value(DEFAULT_DETAILS))
            .andExpect(jsonPath("$.validPeriodBeginAt").value(DEFAULT_VALID_PERIOD_BEGIN_AT.toString()))
            .andExpect(jsonPath("$.validPeriodEndAt").value(DEFAULT_VALID_PERIOD_END_AT.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.receivedQuantity").value(DEFAULT_RECEIVED_QUANTITY))
            .andExpect(jsonPath("$.canUseWithOtherCard").value(DEFAULT_CAN_USE_WITH_OTHER_CARD.booleanValue()))
            .andExpect(jsonPath("$.acceptCategories").value(DEFAULT_ACCEPT_CATEGORIES))
            .andExpect(jsonPath("$.acceptShops").value(DEFAULT_ACCEPT_SHOPS))
            .andExpect(jsonPath("$.leastCost").value(DEFAULT_LEAST_COST))
            .andExpect(jsonPath("$.reduceCost").value(DEFAULT_REDUCE_COST))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT))
            .andExpect(jsonPath("$.gift").value(DEFAULT_GIFT))
            .andExpect(jsonPath("$.giftQuantity").value(DEFAULT_GIFT_QUANTITY))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.deliveriedBy").value(DEFAULT_DELIVERIED_BY))
            .andExpect(jsonPath("$.deliveriedDate").value(DEFAULT_DELIVERIED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY));
    }

    @Test
    @Transactional
    public void getNonExistingCard() throws Exception {
        // Get the card
        restCardMockMvc.perform(get("/api/cards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCard() throws Exception {
        // Initialize the database
        cardRepository.saveAndFlush(card);

        int databaseSizeBeforeUpdate = cardRepository.findAll().size();

        // Update the card
        Card updatedCard = cardRepository.findById(card.getId()).get();
        // Disconnect from session so that the updates on updatedCard are not directly saved in db
        em.detach(updatedCard);
        updatedCard
            .sn(UPDATED_SN)
            .cardType(UPDATED_CARD_TYPE)
            .title(UPDATED_TITLE)
            .thumbnail(UPDATED_THUMBNAIL)
            .details(UPDATED_DETAILS)
            .validPeriodBeginAt(UPDATED_VALID_PERIOD_BEGIN_AT)
            .validPeriodEndAt(UPDATED_VALID_PERIOD_END_AT)
            .quantity(UPDATED_QUANTITY)
            .receivedQuantity(UPDATED_RECEIVED_QUANTITY)
            .canUseWithOtherCard(UPDATED_CAN_USE_WITH_OTHER_CARD)
            .acceptCategories(UPDATED_ACCEPT_CATEGORIES)
            .acceptShops(UPDATED_ACCEPT_SHOPS)
            .leastCost(UPDATED_LEAST_COST)
            .reduceCost(UPDATED_REDUCE_COST)
            .discount(UPDATED_DISCOUNT)
            .gift(UPDATED_GIFT)
            .giftQuantity(UPDATED_GIFT_QUANTITY)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .deliveriedBy(UPDATED_DELIVERIED_BY)
            .deliveriedDate(UPDATED_DELIVERIED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        CardDTO cardDTO = cardMapper.toDto(updatedCard);

        restCardMockMvc.perform(put("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isOk());

        // Validate the Card in the database
        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeUpdate);
        Card testCard = cardList.get(cardList.size() - 1);
        assertThat(testCard.getSn()).isEqualTo(UPDATED_SN);
        assertThat(testCard.getCardType()).isEqualTo(UPDATED_CARD_TYPE);
        assertThat(testCard.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testCard.getThumbnail()).isEqualTo(UPDATED_THUMBNAIL);
        assertThat(testCard.getDetails()).isEqualTo(UPDATED_DETAILS);
        assertThat(testCard.getValidPeriodBeginAt()).isEqualTo(UPDATED_VALID_PERIOD_BEGIN_AT);
        assertThat(testCard.getValidPeriodEndAt()).isEqualTo(UPDATED_VALID_PERIOD_END_AT);
        assertThat(testCard.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testCard.getReceivedQuantity()).isEqualTo(UPDATED_RECEIVED_QUANTITY);
        assertThat(testCard.isCanUseWithOtherCard()).isEqualTo(UPDATED_CAN_USE_WITH_OTHER_CARD);
        assertThat(testCard.getAcceptCategories()).isEqualTo(UPDATED_ACCEPT_CATEGORIES);
        assertThat(testCard.getAcceptShops()).isEqualTo(UPDATED_ACCEPT_SHOPS);
        assertThat(testCard.getLeastCost()).isEqualTo(UPDATED_LEAST_COST);
        assertThat(testCard.getReduceCost()).isEqualTo(UPDATED_REDUCE_COST);
        assertThat(testCard.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testCard.getGift()).isEqualTo(UPDATED_GIFT);
        assertThat(testCard.getGiftQuantity()).isEqualTo(UPDATED_GIFT_QUANTITY);
        assertThat(testCard.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCard.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCard.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCard.getDeliveriedBy()).isEqualTo(UPDATED_DELIVERIED_BY);
        assertThat(testCard.getDeliveriedDate()).isEqualTo(UPDATED_DELIVERIED_DATE);
        assertThat(testCard.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testCard.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingCard() throws Exception {
        int databaseSizeBeforeUpdate = cardRepository.findAll().size();

        // Create the Card
        CardDTO cardDTO = cardMapper.toDto(card);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCardMockMvc.perform(put("/api/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Card in the database
        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCard() throws Exception {
        // Initialize the database
        cardRepository.saveAndFlush(card);

        int databaseSizeBeforeDelete = cardRepository.findAll().size();

        // Delete the card
        restCardMockMvc.perform(delete("/api/cards/{id}", card.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
