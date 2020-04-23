package jo.jhr.web.rest;

import jo.jhr.RedisTestContainerExtension;
import jo.jhr.JhrApp;
import jo.jhr.domain.Carousel;
import jo.jhr.repository.CarouselRepository;
import jo.jhr.service.CarouselService;
import jo.jhr.service.dto.CarouselDTO;
import jo.jhr.service.mapper.CarouselMapper;

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

import jo.jhr.domain.enumeration.CarouselStatus;
/**
 * Integration tests for the {@link CarouselResource} REST controller.
 */
@SpringBootTest(classes = JhrApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class CarouselResourceIT {

    private static final String DEFAULT_PICTURE = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE = "BBBBBBBBBB";

    private static final Integer DEFAULT_SORT = 0;
    private static final Integer UPDATED_SORT = 1;

    private static final CarouselStatus DEFAULT_STATUS = CarouselStatus.NEW;
    private static final CarouselStatus UPDATED_STATUS = CarouselStatus.PUBLISHED;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    @Autowired
    private CarouselRepository carouselRepository;

    @Autowired
    private CarouselMapper carouselMapper;

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCarouselMockMvc;

    private Carousel carousel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Carousel createEntity(EntityManager em) {
        Carousel carousel = new Carousel()
            .picture(DEFAULT_PICTURE)
            .sort(DEFAULT_SORT)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
        return carousel;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Carousel createUpdatedEntity(EntityManager em) {
        Carousel carousel = new Carousel()
            .picture(UPDATED_PICTURE)
            .sort(UPDATED_SORT)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        return carousel;
    }

    @BeforeEach
    public void initTest() {
        carousel = createEntity(em);
    }

    @Test
    @Transactional
    public void createCarousel() throws Exception {
        int databaseSizeBeforeCreate = carouselRepository.findAll().size();

        // Create the Carousel
        CarouselDTO carouselDTO = carouselMapper.toDto(carousel);
        restCarouselMockMvc.perform(post("/api/carousels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carouselDTO)))
            .andExpect(status().isCreated());

        // Validate the Carousel in the database
        List<Carousel> carouselList = carouselRepository.findAll();
        assertThat(carouselList).hasSize(databaseSizeBeforeCreate + 1);
        Carousel testCarousel = carouselList.get(carouselList.size() - 1);
        assertThat(testCarousel.getPicture()).isEqualTo(DEFAULT_PICTURE);
        assertThat(testCarousel.getSort()).isEqualTo(DEFAULT_SORT);
        assertThat(testCarousel.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCarousel.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCarousel.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCarousel.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testCarousel.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void createCarouselWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = carouselRepository.findAll().size();

        // Create the Carousel with an existing ID
        carousel.setId(1L);
        CarouselDTO carouselDTO = carouselMapper.toDto(carousel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCarouselMockMvc.perform(post("/api/carousels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carouselDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Carousel in the database
        List<Carousel> carouselList = carouselRepository.findAll();
        assertThat(carouselList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPictureIsRequired() throws Exception {
        int databaseSizeBeforeTest = carouselRepository.findAll().size();
        // set the field null
        carousel.setPicture(null);

        // Create the Carousel, which fails.
        CarouselDTO carouselDTO = carouselMapper.toDto(carousel);

        restCarouselMockMvc.perform(post("/api/carousels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carouselDTO)))
            .andExpect(status().isBadRequest());

        List<Carousel> carouselList = carouselRepository.findAll();
        assertThat(carouselList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSortIsRequired() throws Exception {
        int databaseSizeBeforeTest = carouselRepository.findAll().size();
        // set the field null
        carousel.setSort(null);

        // Create the Carousel, which fails.
        CarouselDTO carouselDTO = carouselMapper.toDto(carousel);

        restCarouselMockMvc.perform(post("/api/carousels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carouselDTO)))
            .andExpect(status().isBadRequest());

        List<Carousel> carouselList = carouselRepository.findAll();
        assertThat(carouselList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = carouselRepository.findAll().size();
        // set the field null
        carousel.setStatus(null);

        // Create the Carousel, which fails.
        CarouselDTO carouselDTO = carouselMapper.toDto(carousel);

        restCarouselMockMvc.perform(post("/api/carousels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carouselDTO)))
            .andExpect(status().isBadRequest());

        List<Carousel> carouselList = carouselRepository.findAll();
        assertThat(carouselList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = carouselRepository.findAll().size();
        // set the field null
        carousel.setCreatedBy(null);

        // Create the Carousel, which fails.
        CarouselDTO carouselDTO = carouselMapper.toDto(carousel);

        restCarouselMockMvc.perform(post("/api/carousels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carouselDTO)))
            .andExpect(status().isBadRequest());

        List<Carousel> carouselList = carouselRepository.findAll();
        assertThat(carouselList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = carouselRepository.findAll().size();
        // set the field null
        carousel.setCreatedDate(null);

        // Create the Carousel, which fails.
        CarouselDTO carouselDTO = carouselMapper.toDto(carousel);

        restCarouselMockMvc.perform(post("/api/carousels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carouselDTO)))
            .andExpect(status().isBadRequest());

        List<Carousel> carouselList = carouselRepository.findAll();
        assertThat(carouselList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCarousels() throws Exception {
        // Initialize the database
        carouselRepository.saveAndFlush(carousel);

        // Get all the carouselList
        restCarouselMockMvc.perform(get("/api/carousels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(carousel.getId().intValue())))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(DEFAULT_PICTURE)))
            .andExpect(jsonPath("$.[*].sort").value(hasItem(DEFAULT_SORT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));
    }
    
    @Test
    @Transactional
    public void getCarousel() throws Exception {
        // Initialize the database
        carouselRepository.saveAndFlush(carousel);

        // Get the carousel
        restCarouselMockMvc.perform(get("/api/carousels/{id}", carousel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(carousel.getId().intValue()))
            .andExpect(jsonPath("$.picture").value(DEFAULT_PICTURE))
            .andExpect(jsonPath("$.sort").value(DEFAULT_SORT))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY));
    }

    @Test
    @Transactional
    public void getNonExistingCarousel() throws Exception {
        // Get the carousel
        restCarouselMockMvc.perform(get("/api/carousels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCarousel() throws Exception {
        // Initialize the database
        carouselRepository.saveAndFlush(carousel);

        int databaseSizeBeforeUpdate = carouselRepository.findAll().size();

        // Update the carousel
        Carousel updatedCarousel = carouselRepository.findById(carousel.getId()).get();
        // Disconnect from session so that the updates on updatedCarousel are not directly saved in db
        em.detach(updatedCarousel);
        updatedCarousel
            .picture(UPDATED_PICTURE)
            .sort(UPDATED_SORT)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        CarouselDTO carouselDTO = carouselMapper.toDto(updatedCarousel);

        restCarouselMockMvc.perform(put("/api/carousels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carouselDTO)))
            .andExpect(status().isOk());

        // Validate the Carousel in the database
        List<Carousel> carouselList = carouselRepository.findAll();
        assertThat(carouselList).hasSize(databaseSizeBeforeUpdate);
        Carousel testCarousel = carouselList.get(carouselList.size() - 1);
        assertThat(testCarousel.getPicture()).isEqualTo(UPDATED_PICTURE);
        assertThat(testCarousel.getSort()).isEqualTo(UPDATED_SORT);
        assertThat(testCarousel.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCarousel.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCarousel.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCarousel.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testCarousel.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingCarousel() throws Exception {
        int databaseSizeBeforeUpdate = carouselRepository.findAll().size();

        // Create the Carousel
        CarouselDTO carouselDTO = carouselMapper.toDto(carousel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCarouselMockMvc.perform(put("/api/carousels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carouselDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Carousel in the database
        List<Carousel> carouselList = carouselRepository.findAll();
        assertThat(carouselList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCarousel() throws Exception {
        // Initialize the database
        carouselRepository.saveAndFlush(carousel);

        int databaseSizeBeforeDelete = carouselRepository.findAll().size();

        // Delete the carousel
        restCarouselMockMvc.perform(delete("/api/carousels/{id}", carousel.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Carousel> carouselList = carouselRepository.findAll();
        assertThat(carouselList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
