package jo.jhr.web.rest;

import jo.jhr.RedisTestContainerExtension;
import jo.jhr.JhrApp;
import jo.jhr.domain.CardHolder;
import jo.jhr.repository.CardHolderRepository;
import jo.jhr.service.CardHolderService;
import jo.jhr.service.dto.CardHolderDTO;
import jo.jhr.service.mapper.CardHolderMapper;

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
/**
 * Integration tests for the {@link CardHolderResource} REST controller.
 */
@SpringBootTest(classes = JhrApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class CardHolderResourceIT {

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

    private static final String DEFAULT_HELD = "AAAAAAAAAA";
    private static final String UPDATED_HELD = "BBBBBBBBBB";

    @Autowired
    private CardHolderRepository cardHolderRepository;

    @Autowired
    private CardHolderMapper cardHolderMapper;

    @Autowired
    private CardHolderService cardHolderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCardHolderMockMvc;

    private CardHolder cardHolder;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CardHolder createEntity(EntityManager em) {
        CardHolder cardHolder = new CardHolder()
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
            .held(DEFAULT_HELD);
        return cardHolder;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CardHolder createUpdatedEntity(EntityManager em) {
        CardHolder cardHolder = new CardHolder()
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
            .held(UPDATED_HELD);
        return cardHolder;
    }

    @BeforeEach
    public void initTest() {
        cardHolder = createEntity(em);
    }

    @Test
    @Transactional
    public void createCardHolder() throws Exception {
        int databaseSizeBeforeCreate = cardHolderRepository.findAll().size();

        // Create the CardHolder
        CardHolderDTO cardHolderDTO = cardHolderMapper.toDto(cardHolder);
        restCardHolderMockMvc.perform(post("/api/card-holders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardHolderDTO)))
            .andExpect(status().isCreated());

        // Validate the CardHolder in the database
        List<CardHolder> cardHolderList = cardHolderRepository.findAll();
        assertThat(cardHolderList).hasSize(databaseSizeBeforeCreate + 1);
        CardHolder testCardHolder = cardHolderList.get(cardHolderList.size() - 1);
        assertThat(testCardHolder.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCardHolder.getCardSN()).isEqualTo(DEFAULT_CARD_SN);
        assertThat(testCardHolder.getCardType()).isEqualTo(DEFAULT_CARD_TYPE);
        assertThat(testCardHolder.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testCardHolder.getThumbnail()).isEqualTo(DEFAULT_THUMBNAIL);
        assertThat(testCardHolder.getDetails()).isEqualTo(DEFAULT_DETAILS);
        assertThat(testCardHolder.getValidPeriodBeginAt()).isEqualTo(DEFAULT_VALID_PERIOD_BEGIN_AT);
        assertThat(testCardHolder.getValidPeriodEndAt()).isEqualTo(DEFAULT_VALID_PERIOD_END_AT);
        assertThat(testCardHolder.isCanUseWithOtherCard()).isEqualTo(DEFAULT_CAN_USE_WITH_OTHER_CARD);
        assertThat(testCardHolder.getAcceptCategories()).isEqualTo(DEFAULT_ACCEPT_CATEGORIES);
        assertThat(testCardHolder.getAcceptShops()).isEqualTo(DEFAULT_ACCEPT_SHOPS);
        assertThat(testCardHolder.getLeastCost()).isEqualTo(DEFAULT_LEAST_COST);
        assertThat(testCardHolder.getReduceCost()).isEqualTo(DEFAULT_REDUCE_COST);
        assertThat(testCardHolder.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testCardHolder.getGift()).isEqualTo(DEFAULT_GIFT);
        assertThat(testCardHolder.getGiftQuantity()).isEqualTo(DEFAULT_GIFT_QUANTITY);
        assertThat(testCardHolder.getReceivedBy()).isEqualTo(DEFAULT_RECEIVED_BY);
        assertThat(testCardHolder.getReceivedDate()).isEqualTo(DEFAULT_RECEIVED_DATE);
        assertThat(testCardHolder.getHeld()).isEqualTo(DEFAULT_HELD);
    }

    @Test
    @Transactional
    public void createCardHolderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cardHolderRepository.findAll().size();

        // Create the CardHolder with an existing ID
        cardHolder.setId(1L);
        CardHolderDTO cardHolderDTO = cardHolderMapper.toDto(cardHolder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCardHolderMockMvc.perform(post("/api/card-holders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardHolderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CardHolder in the database
        List<CardHolder> cardHolderList = cardHolderRepository.findAll();
        assertThat(cardHolderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardHolderRepository.findAll().size();
        // set the field null
        cardHolder.setCode(null);

        // Create the CardHolder, which fails.
        CardHolderDTO cardHolderDTO = cardHolderMapper.toDto(cardHolder);

        restCardHolderMockMvc.perform(post("/api/card-holders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardHolderDTO)))
            .andExpect(status().isBadRequest());

        List<CardHolder> cardHolderList = cardHolderRepository.findAll();
        assertThat(cardHolderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCardSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardHolderRepository.findAll().size();
        // set the field null
        cardHolder.setCardSN(null);

        // Create the CardHolder, which fails.
        CardHolderDTO cardHolderDTO = cardHolderMapper.toDto(cardHolder);

        restCardHolderMockMvc.perform(post("/api/card-holders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardHolderDTO)))
            .andExpect(status().isBadRequest());

        List<CardHolder> cardHolderList = cardHolderRepository.findAll();
        assertThat(cardHolderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCardTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardHolderRepository.findAll().size();
        // set the field null
        cardHolder.setCardType(null);

        // Create the CardHolder, which fails.
        CardHolderDTO cardHolderDTO = cardHolderMapper.toDto(cardHolder);

        restCardHolderMockMvc.perform(post("/api/card-holders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardHolderDTO)))
            .andExpect(status().isBadRequest());

        List<CardHolder> cardHolderList = cardHolderRepository.findAll();
        assertThat(cardHolderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardHolderRepository.findAll().size();
        // set the field null
        cardHolder.setTitle(null);

        // Create the CardHolder, which fails.
        CardHolderDTO cardHolderDTO = cardHolderMapper.toDto(cardHolder);

        restCardHolderMockMvc.perform(post("/api/card-holders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardHolderDTO)))
            .andExpect(status().isBadRequest());

        List<CardHolder> cardHolderList = cardHolderRepository.findAll();
        assertThat(cardHolderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCanUseWithOtherCardIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardHolderRepository.findAll().size();
        // set the field null
        cardHolder.setCanUseWithOtherCard(null);

        // Create the CardHolder, which fails.
        CardHolderDTO cardHolderDTO = cardHolderMapper.toDto(cardHolder);

        restCardHolderMockMvc.perform(post("/api/card-holders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardHolderDTO)))
            .andExpect(status().isBadRequest());

        List<CardHolder> cardHolderList = cardHolderRepository.findAll();
        assertThat(cardHolderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReceivedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardHolderRepository.findAll().size();
        // set the field null
        cardHolder.setReceivedBy(null);

        // Create the CardHolder, which fails.
        CardHolderDTO cardHolderDTO = cardHolderMapper.toDto(cardHolder);

        restCardHolderMockMvc.perform(post("/api/card-holders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardHolderDTO)))
            .andExpect(status().isBadRequest());

        List<CardHolder> cardHolderList = cardHolderRepository.findAll();
        assertThat(cardHolderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReceivedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardHolderRepository.findAll().size();
        // set the field null
        cardHolder.setReceivedDate(null);

        // Create the CardHolder, which fails.
        CardHolderDTO cardHolderDTO = cardHolderMapper.toDto(cardHolder);

        restCardHolderMockMvc.perform(post("/api/card-holders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardHolderDTO)))
            .andExpect(status().isBadRequest());

        List<CardHolder> cardHolderList = cardHolderRepository.findAll();
        assertThat(cardHolderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCardHolders() throws Exception {
        // Initialize the database
        cardHolderRepository.saveAndFlush(cardHolder);

        // Get all the cardHolderList
        restCardHolderMockMvc.perform(get("/api/card-holders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cardHolder.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].held").value(hasItem(DEFAULT_HELD)));
    }
    
    @Test
    @Transactional
    public void getCardHolder() throws Exception {
        // Initialize the database
        cardHolderRepository.saveAndFlush(cardHolder);

        // Get the cardHolder
        restCardHolderMockMvc.perform(get("/api/card-holders/{id}", cardHolder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cardHolder.getId().intValue()))
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
            .andExpect(jsonPath("$.held").value(DEFAULT_HELD));
    }

    @Test
    @Transactional
    public void getNonExistingCardHolder() throws Exception {
        // Get the cardHolder
        restCardHolderMockMvc.perform(get("/api/card-holders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCardHolder() throws Exception {
        // Initialize the database
        cardHolderRepository.saveAndFlush(cardHolder);

        int databaseSizeBeforeUpdate = cardHolderRepository.findAll().size();

        // Update the cardHolder
        CardHolder updatedCardHolder = cardHolderRepository.findById(cardHolder.getId()).get();
        // Disconnect from session so that the updates on updatedCardHolder are not directly saved in db
        em.detach(updatedCardHolder);
        updatedCardHolder
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
            .held(UPDATED_HELD);
        CardHolderDTO cardHolderDTO = cardHolderMapper.toDto(updatedCardHolder);

        restCardHolderMockMvc.perform(put("/api/card-holders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardHolderDTO)))
            .andExpect(status().isOk());

        // Validate the CardHolder in the database
        List<CardHolder> cardHolderList = cardHolderRepository.findAll();
        assertThat(cardHolderList).hasSize(databaseSizeBeforeUpdate);
        CardHolder testCardHolder = cardHolderList.get(cardHolderList.size() - 1);
        assertThat(testCardHolder.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCardHolder.getCardSN()).isEqualTo(UPDATED_CARD_SN);
        assertThat(testCardHolder.getCardType()).isEqualTo(UPDATED_CARD_TYPE);
        assertThat(testCardHolder.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testCardHolder.getThumbnail()).isEqualTo(UPDATED_THUMBNAIL);
        assertThat(testCardHolder.getDetails()).isEqualTo(UPDATED_DETAILS);
        assertThat(testCardHolder.getValidPeriodBeginAt()).isEqualTo(UPDATED_VALID_PERIOD_BEGIN_AT);
        assertThat(testCardHolder.getValidPeriodEndAt()).isEqualTo(UPDATED_VALID_PERIOD_END_AT);
        assertThat(testCardHolder.isCanUseWithOtherCard()).isEqualTo(UPDATED_CAN_USE_WITH_OTHER_CARD);
        assertThat(testCardHolder.getAcceptCategories()).isEqualTo(UPDATED_ACCEPT_CATEGORIES);
        assertThat(testCardHolder.getAcceptShops()).isEqualTo(UPDATED_ACCEPT_SHOPS);
        assertThat(testCardHolder.getLeastCost()).isEqualTo(UPDATED_LEAST_COST);
        assertThat(testCardHolder.getReduceCost()).isEqualTo(UPDATED_REDUCE_COST);
        assertThat(testCardHolder.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testCardHolder.getGift()).isEqualTo(UPDATED_GIFT);
        assertThat(testCardHolder.getGiftQuantity()).isEqualTo(UPDATED_GIFT_QUANTITY);
        assertThat(testCardHolder.getReceivedBy()).isEqualTo(UPDATED_RECEIVED_BY);
        assertThat(testCardHolder.getReceivedDate()).isEqualTo(UPDATED_RECEIVED_DATE);
        assertThat(testCardHolder.getHeld()).isEqualTo(UPDATED_HELD);
    }

    @Test
    @Transactional
    public void updateNonExistingCardHolder() throws Exception {
        int databaseSizeBeforeUpdate = cardHolderRepository.findAll().size();

        // Create the CardHolder
        CardHolderDTO cardHolderDTO = cardHolderMapper.toDto(cardHolder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCardHolderMockMvc.perform(put("/api/card-holders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cardHolderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CardHolder in the database
        List<CardHolder> cardHolderList = cardHolderRepository.findAll();
        assertThat(cardHolderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCardHolder() throws Exception {
        // Initialize the database
        cardHolderRepository.saveAndFlush(cardHolder);

        int databaseSizeBeforeDelete = cardHolderRepository.findAll().size();

        // Delete the cardHolder
        restCardHolderMockMvc.perform(delete("/api/card-holders/{id}", cardHolder.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CardHolder> cardHolderList = cardHolderRepository.findAll();
        assertThat(cardHolderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
