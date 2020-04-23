package jo.jhr.web.rest;

import jo.jhr.RedisTestContainerExtension;
import jo.jhr.JhrApp;
import jo.jhr.domain.RewardPoints;
import jo.jhr.repository.RewardPointsRepository;
import jo.jhr.service.RewardPointsService;
import jo.jhr.service.dto.RewardPointsDTO;
import jo.jhr.service.mapper.RewardPointsMapper;

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

/**
 * Integration tests for the {@link RewardPointsResource} REST controller.
 */
@SpringBootTest(classes = JhrApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class RewardPointsResourceIT {

    private static final String DEFAULT_MEMBER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MEMBER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MEMBER_SN = "AAAAAAAAAA";
    private static final String UPDATED_MEMBER_SN = "BBBBBBBBBB";

    private static final Integer DEFAULT_BALANCE = 0;
    private static final Integer UPDATED_BALANCE = 1;

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private RewardPointsRepository rewardPointsRepository;

    @Autowired
    private RewardPointsMapper rewardPointsMapper;

    @Autowired
    private RewardPointsService rewardPointsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRewardPointsMockMvc;

    private RewardPoints rewardPoints;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RewardPoints createEntity(EntityManager em) {
        RewardPoints rewardPoints = new RewardPoints()
            .memberName(DEFAULT_MEMBER_NAME)
            .memberSN(DEFAULT_MEMBER_SN)
            .balance(DEFAULT_BALANCE)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return rewardPoints;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RewardPoints createUpdatedEntity(EntityManager em) {
        RewardPoints rewardPoints = new RewardPoints()
            .memberName(UPDATED_MEMBER_NAME)
            .memberSN(UPDATED_MEMBER_SN)
            .balance(UPDATED_BALANCE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return rewardPoints;
    }

    @BeforeEach
    public void initTest() {
        rewardPoints = createEntity(em);
    }

    @Test
    @Transactional
    public void createRewardPoints() throws Exception {
        int databaseSizeBeforeCreate = rewardPointsRepository.findAll().size();

        // Create the RewardPoints
        RewardPointsDTO rewardPointsDTO = rewardPointsMapper.toDto(rewardPoints);
        restRewardPointsMockMvc.perform(post("/api/reward-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rewardPointsDTO)))
            .andExpect(status().isCreated());

        // Validate the RewardPoints in the database
        List<RewardPoints> rewardPointsList = rewardPointsRepository.findAll();
        assertThat(rewardPointsList).hasSize(databaseSizeBeforeCreate + 1);
        RewardPoints testRewardPoints = rewardPointsList.get(rewardPointsList.size() - 1);
        assertThat(testRewardPoints.getMemberName()).isEqualTo(DEFAULT_MEMBER_NAME);
        assertThat(testRewardPoints.getMemberSN()).isEqualTo(DEFAULT_MEMBER_SN);
        assertThat(testRewardPoints.getBalance()).isEqualTo(DEFAULT_BALANCE);
        assertThat(testRewardPoints.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createRewardPointsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rewardPointsRepository.findAll().size();

        // Create the RewardPoints with an existing ID
        rewardPoints.setId(1L);
        RewardPointsDTO rewardPointsDTO = rewardPointsMapper.toDto(rewardPoints);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRewardPointsMockMvc.perform(post("/api/reward-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rewardPointsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RewardPoints in the database
        List<RewardPoints> rewardPointsList = rewardPointsRepository.findAll();
        assertThat(rewardPointsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMemberNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = rewardPointsRepository.findAll().size();
        // set the field null
        rewardPoints.setMemberName(null);

        // Create the RewardPoints, which fails.
        RewardPointsDTO rewardPointsDTO = rewardPointsMapper.toDto(rewardPoints);

        restRewardPointsMockMvc.perform(post("/api/reward-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rewardPointsDTO)))
            .andExpect(status().isBadRequest());

        List<RewardPoints> rewardPointsList = rewardPointsRepository.findAll();
        assertThat(rewardPointsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMemberSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = rewardPointsRepository.findAll().size();
        // set the field null
        rewardPoints.setMemberSN(null);

        // Create the RewardPoints, which fails.
        RewardPointsDTO rewardPointsDTO = rewardPointsMapper.toDto(rewardPoints);

        restRewardPointsMockMvc.perform(post("/api/reward-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rewardPointsDTO)))
            .andExpect(status().isBadRequest());

        List<RewardPoints> rewardPointsList = rewardPointsRepository.findAll();
        assertThat(rewardPointsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBalanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = rewardPointsRepository.findAll().size();
        // set the field null
        rewardPoints.setBalance(null);

        // Create the RewardPoints, which fails.
        RewardPointsDTO rewardPointsDTO = rewardPointsMapper.toDto(rewardPoints);

        restRewardPointsMockMvc.perform(post("/api/reward-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rewardPointsDTO)))
            .andExpect(status().isBadRequest());

        List<RewardPoints> rewardPointsList = rewardPointsRepository.findAll();
        assertThat(rewardPointsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastModifiedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = rewardPointsRepository.findAll().size();
        // set the field null
        rewardPoints.setLastModifiedDate(null);

        // Create the RewardPoints, which fails.
        RewardPointsDTO rewardPointsDTO = rewardPointsMapper.toDto(rewardPoints);

        restRewardPointsMockMvc.perform(post("/api/reward-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rewardPointsDTO)))
            .andExpect(status().isBadRequest());

        List<RewardPoints> rewardPointsList = rewardPointsRepository.findAll();
        assertThat(rewardPointsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRewardPoints() throws Exception {
        // Initialize the database
        rewardPointsRepository.saveAndFlush(rewardPoints);

        // Get all the rewardPointsList
        restRewardPointsMockMvc.perform(get("/api/reward-points?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rewardPoints.getId().intValue())))
            .andExpect(jsonPath("$.[*].memberName").value(hasItem(DEFAULT_MEMBER_NAME)))
            .andExpect(jsonPath("$.[*].memberSN").value(hasItem(DEFAULT_MEMBER_SN)))
            .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getRewardPoints() throws Exception {
        // Initialize the database
        rewardPointsRepository.saveAndFlush(rewardPoints);

        // Get the rewardPoints
        restRewardPointsMockMvc.perform(get("/api/reward-points/{id}", rewardPoints.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rewardPoints.getId().intValue()))
            .andExpect(jsonPath("$.memberName").value(DEFAULT_MEMBER_NAME))
            .andExpect(jsonPath("$.memberSN").value(DEFAULT_MEMBER_SN))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRewardPoints() throws Exception {
        // Get the rewardPoints
        restRewardPointsMockMvc.perform(get("/api/reward-points/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRewardPoints() throws Exception {
        // Initialize the database
        rewardPointsRepository.saveAndFlush(rewardPoints);

        int databaseSizeBeforeUpdate = rewardPointsRepository.findAll().size();

        // Update the rewardPoints
        RewardPoints updatedRewardPoints = rewardPointsRepository.findById(rewardPoints.getId()).get();
        // Disconnect from session so that the updates on updatedRewardPoints are not directly saved in db
        em.detach(updatedRewardPoints);
        updatedRewardPoints
            .memberName(UPDATED_MEMBER_NAME)
            .memberSN(UPDATED_MEMBER_SN)
            .balance(UPDATED_BALANCE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        RewardPointsDTO rewardPointsDTO = rewardPointsMapper.toDto(updatedRewardPoints);

        restRewardPointsMockMvc.perform(put("/api/reward-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rewardPointsDTO)))
            .andExpect(status().isOk());

        // Validate the RewardPoints in the database
        List<RewardPoints> rewardPointsList = rewardPointsRepository.findAll();
        assertThat(rewardPointsList).hasSize(databaseSizeBeforeUpdate);
        RewardPoints testRewardPoints = rewardPointsList.get(rewardPointsList.size() - 1);
        assertThat(testRewardPoints.getMemberName()).isEqualTo(UPDATED_MEMBER_NAME);
        assertThat(testRewardPoints.getMemberSN()).isEqualTo(UPDATED_MEMBER_SN);
        assertThat(testRewardPoints.getBalance()).isEqualTo(UPDATED_BALANCE);
        assertThat(testRewardPoints.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingRewardPoints() throws Exception {
        int databaseSizeBeforeUpdate = rewardPointsRepository.findAll().size();

        // Create the RewardPoints
        RewardPointsDTO rewardPointsDTO = rewardPointsMapper.toDto(rewardPoints);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRewardPointsMockMvc.perform(put("/api/reward-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rewardPointsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RewardPoints in the database
        List<RewardPoints> rewardPointsList = rewardPointsRepository.findAll();
        assertThat(rewardPointsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRewardPoints() throws Exception {
        // Initialize the database
        rewardPointsRepository.saveAndFlush(rewardPoints);

        int databaseSizeBeforeDelete = rewardPointsRepository.findAll().size();

        // Delete the rewardPoints
        restRewardPointsMockMvc.perform(delete("/api/reward-points/{id}", rewardPoints.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RewardPoints> rewardPointsList = rewardPointsRepository.findAll();
        assertThat(rewardPointsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
