package jo.jhr.web.rest;

import jo.jhr.RedisTestContainerExtension;
import jo.jhr.JhrApp;
import jo.jhr.domain.RewardPointsRecord;
import jo.jhr.repository.RewardPointsRecordRepository;
import jo.jhr.service.RewardPointsRecordService;
import jo.jhr.service.dto.RewardPointsRecordDTO;
import jo.jhr.service.mapper.RewardPointsRecordMapper;

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
 * Integration tests for the {@link RewardPointsRecordResource} REST controller.
 */
@SpringBootTest(classes = JhrApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class RewardPointsRecordResourceIT {

    private static final Integer DEFAULT_AMOUNT = 1;
    private static final Integer UPDATED_AMOUNT = 2;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private RewardPointsRecordRepository rewardPointsRecordRepository;

    @Autowired
    private RewardPointsRecordMapper rewardPointsRecordMapper;

    @Autowired
    private RewardPointsRecordService rewardPointsRecordService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRewardPointsRecordMockMvc;

    private RewardPointsRecord rewardPointsRecord;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RewardPointsRecord createEntity(EntityManager em) {
        RewardPointsRecord rewardPointsRecord = new RewardPointsRecord()
            .amount(DEFAULT_AMOUNT)
            .remark(DEFAULT_REMARK)
            .createdDate(DEFAULT_CREATED_DATE);
        return rewardPointsRecord;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RewardPointsRecord createUpdatedEntity(EntityManager em) {
        RewardPointsRecord rewardPointsRecord = new RewardPointsRecord()
            .amount(UPDATED_AMOUNT)
            .remark(UPDATED_REMARK)
            .createdDate(UPDATED_CREATED_DATE);
        return rewardPointsRecord;
    }

    @BeforeEach
    public void initTest() {
        rewardPointsRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createRewardPointsRecord() throws Exception {
        int databaseSizeBeforeCreate = rewardPointsRecordRepository.findAll().size();

        // Create the RewardPointsRecord
        RewardPointsRecordDTO rewardPointsRecordDTO = rewardPointsRecordMapper.toDto(rewardPointsRecord);
        restRewardPointsRecordMockMvc.perform(post("/api/reward-points-records")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rewardPointsRecordDTO)))
            .andExpect(status().isCreated());

        // Validate the RewardPointsRecord in the database
        List<RewardPointsRecord> rewardPointsRecordList = rewardPointsRecordRepository.findAll();
        assertThat(rewardPointsRecordList).hasSize(databaseSizeBeforeCreate + 1);
        RewardPointsRecord testRewardPointsRecord = rewardPointsRecordList.get(rewardPointsRecordList.size() - 1);
        assertThat(testRewardPointsRecord.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testRewardPointsRecord.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testRewardPointsRecord.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
    }

    @Test
    @Transactional
    public void createRewardPointsRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rewardPointsRecordRepository.findAll().size();

        // Create the RewardPointsRecord with an existing ID
        rewardPointsRecord.setId(1L);
        RewardPointsRecordDTO rewardPointsRecordDTO = rewardPointsRecordMapper.toDto(rewardPointsRecord);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRewardPointsRecordMockMvc.perform(post("/api/reward-points-records")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rewardPointsRecordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RewardPointsRecord in the database
        List<RewardPointsRecord> rewardPointsRecordList = rewardPointsRecordRepository.findAll();
        assertThat(rewardPointsRecordList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = rewardPointsRecordRepository.findAll().size();
        // set the field null
        rewardPointsRecord.setAmount(null);

        // Create the RewardPointsRecord, which fails.
        RewardPointsRecordDTO rewardPointsRecordDTO = rewardPointsRecordMapper.toDto(rewardPointsRecord);

        restRewardPointsRecordMockMvc.perform(post("/api/reward-points-records")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rewardPointsRecordDTO)))
            .andExpect(status().isBadRequest());

        List<RewardPointsRecord> rewardPointsRecordList = rewardPointsRecordRepository.findAll();
        assertThat(rewardPointsRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = rewardPointsRecordRepository.findAll().size();
        // set the field null
        rewardPointsRecord.setCreatedDate(null);

        // Create the RewardPointsRecord, which fails.
        RewardPointsRecordDTO rewardPointsRecordDTO = rewardPointsRecordMapper.toDto(rewardPointsRecord);

        restRewardPointsRecordMockMvc.perform(post("/api/reward-points-records")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rewardPointsRecordDTO)))
            .andExpect(status().isBadRequest());

        List<RewardPointsRecord> rewardPointsRecordList = rewardPointsRecordRepository.findAll();
        assertThat(rewardPointsRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRewardPointsRecords() throws Exception {
        // Initialize the database
        rewardPointsRecordRepository.saveAndFlush(rewardPointsRecord);

        // Get all the rewardPointsRecordList
        restRewardPointsRecordMockMvc.perform(get("/api/reward-points-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rewardPointsRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getRewardPointsRecord() throws Exception {
        // Initialize the database
        rewardPointsRecordRepository.saveAndFlush(rewardPointsRecord);

        // Get the rewardPointsRecord
        restRewardPointsRecordMockMvc.perform(get("/api/reward-points-records/{id}", rewardPointsRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rewardPointsRecord.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRewardPointsRecord() throws Exception {
        // Get the rewardPointsRecord
        restRewardPointsRecordMockMvc.perform(get("/api/reward-points-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRewardPointsRecord() throws Exception {
        // Initialize the database
        rewardPointsRecordRepository.saveAndFlush(rewardPointsRecord);

        int databaseSizeBeforeUpdate = rewardPointsRecordRepository.findAll().size();

        // Update the rewardPointsRecord
        RewardPointsRecord updatedRewardPointsRecord = rewardPointsRecordRepository.findById(rewardPointsRecord.getId()).get();
        // Disconnect from session so that the updates on updatedRewardPointsRecord are not directly saved in db
        em.detach(updatedRewardPointsRecord);
        updatedRewardPointsRecord
            .amount(UPDATED_AMOUNT)
            .remark(UPDATED_REMARK)
            .createdDate(UPDATED_CREATED_DATE);
        RewardPointsRecordDTO rewardPointsRecordDTO = rewardPointsRecordMapper.toDto(updatedRewardPointsRecord);

        restRewardPointsRecordMockMvc.perform(put("/api/reward-points-records")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rewardPointsRecordDTO)))
            .andExpect(status().isOk());

        // Validate the RewardPointsRecord in the database
        List<RewardPointsRecord> rewardPointsRecordList = rewardPointsRecordRepository.findAll();
        assertThat(rewardPointsRecordList).hasSize(databaseSizeBeforeUpdate);
        RewardPointsRecord testRewardPointsRecord = rewardPointsRecordList.get(rewardPointsRecordList.size() - 1);
        assertThat(testRewardPointsRecord.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testRewardPointsRecord.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testRewardPointsRecord.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingRewardPointsRecord() throws Exception {
        int databaseSizeBeforeUpdate = rewardPointsRecordRepository.findAll().size();

        // Create the RewardPointsRecord
        RewardPointsRecordDTO rewardPointsRecordDTO = rewardPointsRecordMapper.toDto(rewardPointsRecord);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRewardPointsRecordMockMvc.perform(put("/api/reward-points-records")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rewardPointsRecordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RewardPointsRecord in the database
        List<RewardPointsRecord> rewardPointsRecordList = rewardPointsRecordRepository.findAll();
        assertThat(rewardPointsRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRewardPointsRecord() throws Exception {
        // Initialize the database
        rewardPointsRecordRepository.saveAndFlush(rewardPointsRecord);

        int databaseSizeBeforeDelete = rewardPointsRecordRepository.findAll().size();

        // Delete the rewardPointsRecord
        restRewardPointsRecordMockMvc.perform(delete("/api/reward-points-records/{id}", rewardPointsRecord.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RewardPointsRecord> rewardPointsRecordList = rewardPointsRecordRepository.findAll();
        assertThat(rewardPointsRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
