package jo.jhr.web.rest;

import jo.jhr.RedisTestContainerExtension;
import jo.jhr.JhrApp;
import jo.jhr.domain.FinalCard;
import jo.jhr.repository.FinalCardRepository;
import jo.jhr.service.FinalCardService;
import jo.jhr.service.dto.FinalCardDTO;
import jo.jhr.service.mapper.FinalCardMapper;

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
 * Integration tests for the {@link FinalCardResource} REST controller.
 */
@SpringBootTest(classes = JhrApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class FinalCardResourceIT {

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

    private static final String DEFAULT_DISCONTINUE_BY = "AAAAAAAAAA";
    private static final String UPDATED_DISCONTINUE_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_DISCONTINUE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DISCONTINUE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    @Autowired
    private FinalCardRepository finalCardRepository;

    @Autowired
    private FinalCardMapper finalCardMapper;

    @Autowired
    private FinalCardService finalCardService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFinalCardMockMvc;

    private FinalCard finalCard;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FinalCard createEntity(EntityManager em) {
        FinalCard finalCard = new FinalCard()
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
            .discontinueBy(DEFAULT_DISCONTINUE_BY)
            .discontinueDate(DEFAULT_DISCONTINUE_DATE)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
        return finalCard;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FinalCard createUpdatedEntity(EntityManager em) {
        FinalCard finalCard = new FinalCard()
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
            .discontinueBy(UPDATED_DISCONTINUE_BY)
            .discontinueDate(UPDATED_DISCONTINUE_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        return finalCard;
    }

    @BeforeEach
    public void initTest() {
        finalCard = createEntity(em);
    }

    @Test
    @Transactional
    public void createFinalCard() throws Exception {
        int databaseSizeBeforeCreate = finalCardRepository.findAll().size();

        // Create the FinalCard
        FinalCardDTO finalCardDTO = finalCardMapper.toDto(finalCard);
        restFinalCardMockMvc.perform(post("/api/final-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(finalCardDTO)))
            .andExpect(status().isCreated());

        // Validate the FinalCard in the database
        List<FinalCard> finalCardList = finalCardRepository.findAll();
        assertThat(finalCardList).hasSize(databaseSizeBeforeCreate + 1);
        FinalCard testFinalCard = finalCardList.get(finalCardList.size() - 1);
        assertThat(testFinalCard.getSn()).isEqualTo(DEFAULT_SN);
        assertThat(testFinalCard.getCardType()).isEqualTo(DEFAULT_CARD_TYPE);
        assertThat(testFinalCard.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testFinalCard.getThumbnail()).isEqualTo(DEFAULT_THUMBNAIL);
        assertThat(testFinalCard.getDetails()).isEqualTo(DEFAULT_DETAILS);
        assertThat(testFinalCard.getValidPeriodBeginAt()).isEqualTo(DEFAULT_VALID_PERIOD_BEGIN_AT);
        assertThat(testFinalCard.getValidPeriodEndAt()).isEqualTo(DEFAULT_VALID_PERIOD_END_AT);
        assertThat(testFinalCard.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testFinalCard.getReceivedQuantity()).isEqualTo(DEFAULT_RECEIVED_QUANTITY);
        assertThat(testFinalCard.isCanUseWithOtherCard()).isEqualTo(DEFAULT_CAN_USE_WITH_OTHER_CARD);
        assertThat(testFinalCard.getAcceptCategories()).isEqualTo(DEFAULT_ACCEPT_CATEGORIES);
        assertThat(testFinalCard.getAcceptShops()).isEqualTo(DEFAULT_ACCEPT_SHOPS);
        assertThat(testFinalCard.getLeastCost()).isEqualTo(DEFAULT_LEAST_COST);
        assertThat(testFinalCard.getReduceCost()).isEqualTo(DEFAULT_REDUCE_COST);
        assertThat(testFinalCard.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testFinalCard.getGift()).isEqualTo(DEFAULT_GIFT);
        assertThat(testFinalCard.getGiftQuantity()).isEqualTo(DEFAULT_GIFT_QUANTITY);
        assertThat(testFinalCard.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFinalCard.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testFinalCard.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFinalCard.getDeliveriedBy()).isEqualTo(DEFAULT_DELIVERIED_BY);
        assertThat(testFinalCard.getDeliveriedDate()).isEqualTo(DEFAULT_DELIVERIED_DATE);
        assertThat(testFinalCard.getDiscontinueBy()).isEqualTo(DEFAULT_DISCONTINUE_BY);
        assertThat(testFinalCard.getDiscontinueDate()).isEqualTo(DEFAULT_DISCONTINUE_DATE);
        assertThat(testFinalCard.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testFinalCard.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void createFinalCardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = finalCardRepository.findAll().size();

        // Create the FinalCard with an existing ID
        finalCard.setId(1L);
        FinalCardDTO finalCardDTO = finalCardMapper.toDto(finalCard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFinalCardMockMvc.perform(post("/api/final-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(finalCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FinalCard in the database
        List<FinalCard> finalCardList = finalCardRepository.findAll();
        assertThat(finalCardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSnIsRequired() throws Exception {
        int databaseSizeBeforeTest = finalCardRepository.findAll().size();
        // set the field null
        finalCard.setSn(null);

        // Create the FinalCard, which fails.
        FinalCardDTO finalCardDTO = finalCardMapper.toDto(finalCard);

        restFinalCardMockMvc.perform(post("/api/final-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(finalCardDTO)))
            .andExpect(status().isBadRequest());

        List<FinalCard> finalCardList = finalCardRepository.findAll();
        assertThat(finalCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCardTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = finalCardRepository.findAll().size();
        // set the field null
        finalCard.setCardType(null);

        // Create the FinalCard, which fails.
        FinalCardDTO finalCardDTO = finalCardMapper.toDto(finalCard);

        restFinalCardMockMvc.perform(post("/api/final-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(finalCardDTO)))
            .andExpect(status().isBadRequest());

        List<FinalCard> finalCardList = finalCardRepository.findAll();
        assertThat(finalCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = finalCardRepository.findAll().size();
        // set the field null
        finalCard.setTitle(null);

        // Create the FinalCard, which fails.
        FinalCardDTO finalCardDTO = finalCardMapper.toDto(finalCard);

        restFinalCardMockMvc.perform(post("/api/final-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(finalCardDTO)))
            .andExpect(status().isBadRequest());

        List<FinalCard> finalCardList = finalCardRepository.findAll();
        assertThat(finalCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCanUseWithOtherCardIsRequired() throws Exception {
        int databaseSizeBeforeTest = finalCardRepository.findAll().size();
        // set the field null
        finalCard.setCanUseWithOtherCard(null);

        // Create the FinalCard, which fails.
        FinalCardDTO finalCardDTO = finalCardMapper.toDto(finalCard);

        restFinalCardMockMvc.perform(post("/api/final-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(finalCardDTO)))
            .andExpect(status().isBadRequest());

        List<FinalCard> finalCardList = finalCardRepository.findAll();
        assertThat(finalCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = finalCardRepository.findAll().size();
        // set the field null
        finalCard.setStatus(null);

        // Create the FinalCard, which fails.
        FinalCardDTO finalCardDTO = finalCardMapper.toDto(finalCard);

        restFinalCardMockMvc.perform(post("/api/final-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(finalCardDTO)))
            .andExpect(status().isBadRequest());

        List<FinalCard> finalCardList = finalCardRepository.findAll();
        assertThat(finalCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = finalCardRepository.findAll().size();
        // set the field null
        finalCard.setCreatedBy(null);

        // Create the FinalCard, which fails.
        FinalCardDTO finalCardDTO = finalCardMapper.toDto(finalCard);

        restFinalCardMockMvc.perform(post("/api/final-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(finalCardDTO)))
            .andExpect(status().isBadRequest());

        List<FinalCard> finalCardList = finalCardRepository.findAll();
        assertThat(finalCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = finalCardRepository.findAll().size();
        // set the field null
        finalCard.setCreatedDate(null);

        // Create the FinalCard, which fails.
        FinalCardDTO finalCardDTO = finalCardMapper.toDto(finalCard);

        restFinalCardMockMvc.perform(post("/api/final-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(finalCardDTO)))
            .andExpect(status().isBadRequest());

        List<FinalCard> finalCardList = finalCardRepository.findAll();
        assertThat(finalCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFinalCards() throws Exception {
        // Initialize the database
        finalCardRepository.saveAndFlush(finalCard);

        // Get all the finalCardList
        restFinalCardMockMvc.perform(get("/api/final-cards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(finalCard.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].discontinueBy").value(hasItem(DEFAULT_DISCONTINUE_BY)))
            .andExpect(jsonPath("$.[*].discontinueDate").value(hasItem(DEFAULT_DISCONTINUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));
    }
    
    @Test
    @Transactional
    public void getFinalCard() throws Exception {
        // Initialize the database
        finalCardRepository.saveAndFlush(finalCard);

        // Get the finalCard
        restFinalCardMockMvc.perform(get("/api/final-cards/{id}", finalCard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(finalCard.getId().intValue()))
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
            .andExpect(jsonPath("$.discontinueBy").value(DEFAULT_DISCONTINUE_BY))
            .andExpect(jsonPath("$.discontinueDate").value(DEFAULT_DISCONTINUE_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY));
    }

    @Test
    @Transactional
    public void getNonExistingFinalCard() throws Exception {
        // Get the finalCard
        restFinalCardMockMvc.perform(get("/api/final-cards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFinalCard() throws Exception {
        // Initialize the database
        finalCardRepository.saveAndFlush(finalCard);

        int databaseSizeBeforeUpdate = finalCardRepository.findAll().size();

        // Update the finalCard
        FinalCard updatedFinalCard = finalCardRepository.findById(finalCard.getId()).get();
        // Disconnect from session so that the updates on updatedFinalCard are not directly saved in db
        em.detach(updatedFinalCard);
        updatedFinalCard
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
            .discontinueBy(UPDATED_DISCONTINUE_BY)
            .discontinueDate(UPDATED_DISCONTINUE_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        FinalCardDTO finalCardDTO = finalCardMapper.toDto(updatedFinalCard);

        restFinalCardMockMvc.perform(put("/api/final-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(finalCardDTO)))
            .andExpect(status().isOk());

        // Validate the FinalCard in the database
        List<FinalCard> finalCardList = finalCardRepository.findAll();
        assertThat(finalCardList).hasSize(databaseSizeBeforeUpdate);
        FinalCard testFinalCard = finalCardList.get(finalCardList.size() - 1);
        assertThat(testFinalCard.getSn()).isEqualTo(UPDATED_SN);
        assertThat(testFinalCard.getCardType()).isEqualTo(UPDATED_CARD_TYPE);
        assertThat(testFinalCard.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testFinalCard.getThumbnail()).isEqualTo(UPDATED_THUMBNAIL);
        assertThat(testFinalCard.getDetails()).isEqualTo(UPDATED_DETAILS);
        assertThat(testFinalCard.getValidPeriodBeginAt()).isEqualTo(UPDATED_VALID_PERIOD_BEGIN_AT);
        assertThat(testFinalCard.getValidPeriodEndAt()).isEqualTo(UPDATED_VALID_PERIOD_END_AT);
        assertThat(testFinalCard.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testFinalCard.getReceivedQuantity()).isEqualTo(UPDATED_RECEIVED_QUANTITY);
        assertThat(testFinalCard.isCanUseWithOtherCard()).isEqualTo(UPDATED_CAN_USE_WITH_OTHER_CARD);
        assertThat(testFinalCard.getAcceptCategories()).isEqualTo(UPDATED_ACCEPT_CATEGORIES);
        assertThat(testFinalCard.getAcceptShops()).isEqualTo(UPDATED_ACCEPT_SHOPS);
        assertThat(testFinalCard.getLeastCost()).isEqualTo(UPDATED_LEAST_COST);
        assertThat(testFinalCard.getReduceCost()).isEqualTo(UPDATED_REDUCE_COST);
        assertThat(testFinalCard.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testFinalCard.getGift()).isEqualTo(UPDATED_GIFT);
        assertThat(testFinalCard.getGiftQuantity()).isEqualTo(UPDATED_GIFT_QUANTITY);
        assertThat(testFinalCard.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFinalCard.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFinalCard.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFinalCard.getDeliveriedBy()).isEqualTo(UPDATED_DELIVERIED_BY);
        assertThat(testFinalCard.getDeliveriedDate()).isEqualTo(UPDATED_DELIVERIED_DATE);
        assertThat(testFinalCard.getDiscontinueBy()).isEqualTo(UPDATED_DISCONTINUE_BY);
        assertThat(testFinalCard.getDiscontinueDate()).isEqualTo(UPDATED_DISCONTINUE_DATE);
        assertThat(testFinalCard.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testFinalCard.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingFinalCard() throws Exception {
        int databaseSizeBeforeUpdate = finalCardRepository.findAll().size();

        // Create the FinalCard
        FinalCardDTO finalCardDTO = finalCardMapper.toDto(finalCard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFinalCardMockMvc.perform(put("/api/final-cards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(finalCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FinalCard in the database
        List<FinalCard> finalCardList = finalCardRepository.findAll();
        assertThat(finalCardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFinalCard() throws Exception {
        // Initialize the database
        finalCardRepository.saveAndFlush(finalCard);

        int databaseSizeBeforeDelete = finalCardRepository.findAll().size();

        // Delete the finalCard
        restFinalCardMockMvc.perform(delete("/api/final-cards/{id}", finalCard.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FinalCard> finalCardList = finalCardRepository.findAll();
        assertThat(finalCardList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
