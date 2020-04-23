package jo.jhr.web.rest;

import jo.jhr.RedisTestContainerExtension;
import jo.jhr.JhrApp;
import jo.jhr.domain.DeliveryAddress;
import jo.jhr.repository.DeliveryAddressRepository;
import jo.jhr.service.DeliveryAddressService;
import jo.jhr.service.dto.DeliveryAddressDTO;
import jo.jhr.service.mapper.DeliveryAddressMapper;

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

import jo.jhr.domain.enumeration.Sex;
import jo.jhr.domain.enumeration.OffsetType;
/**
 * Integration tests for the {@link DeliveryAddressResource} REST controller.
 */
@SpringBootTest(classes = JhrApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class DeliveryAddressResourceIT {

    private static final String DEFAULT_MEMBER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MEMBER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MEMBER_SN = "AAAAAAAAAA";
    private static final String UPDATED_MEMBER_SN = "BBBBBBBBBB";

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

    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    private DeliveryAddressMapper deliveryAddressMapper;

    @Autowired
    private DeliveryAddressService deliveryAddressService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDeliveryAddressMockMvc;

    private DeliveryAddress deliveryAddress;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryAddress createEntity(EntityManager em) {
        DeliveryAddress deliveryAddress = new DeliveryAddress()
            .memberName(DEFAULT_MEMBER_NAME)
            .memberSN(DEFAULT_MEMBER_SN)
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
            .address(DEFAULT_ADDRESS);
        return deliveryAddress;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryAddress createUpdatedEntity(EntityManager em) {
        DeliveryAddress deliveryAddress = new DeliveryAddress()
            .memberName(UPDATED_MEMBER_NAME)
            .memberSN(UPDATED_MEMBER_SN)
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
            .address(UPDATED_ADDRESS);
        return deliveryAddress;
    }

    @BeforeEach
    public void initTest() {
        deliveryAddress = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeliveryAddress() throws Exception {
        int databaseSizeBeforeCreate = deliveryAddressRepository.findAll().size();

        // Create the DeliveryAddress
        DeliveryAddressDTO deliveryAddressDTO = deliveryAddressMapper.toDto(deliveryAddress);
        restDeliveryAddressMockMvc.perform(post("/api/delivery-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryAddressDTO)))
            .andExpect(status().isCreated());

        // Validate the DeliveryAddress in the database
        List<DeliveryAddress> deliveryAddressList = deliveryAddressRepository.findAll();
        assertThat(deliveryAddressList).hasSize(databaseSizeBeforeCreate + 1);
        DeliveryAddress testDeliveryAddress = deliveryAddressList.get(deliveryAddressList.size() - 1);
        assertThat(testDeliveryAddress.getMemberName()).isEqualTo(DEFAULT_MEMBER_NAME);
        assertThat(testDeliveryAddress.getMemberSN()).isEqualTo(DEFAULT_MEMBER_SN);
        assertThat(testDeliveryAddress.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testDeliveryAddress.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testDeliveryAddress.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testDeliveryAddress.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testDeliveryAddress.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testDeliveryAddress.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testDeliveryAddress.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testDeliveryAddress.getOffsetType()).isEqualTo(DEFAULT_OFFSET_TYPE);
        assertThat(testDeliveryAddress.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testDeliveryAddress.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testDeliveryAddress.getAddress()).isEqualTo(DEFAULT_ADDRESS);
    }

    @Test
    @Transactional
    public void createDeliveryAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deliveryAddressRepository.findAll().size();

        // Create the DeliveryAddress with an existing ID
        deliveryAddress.setId(1L);
        DeliveryAddressDTO deliveryAddressDTO = deliveryAddressMapper.toDto(deliveryAddress);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliveryAddressMockMvc.perform(post("/api/delivery-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeliveryAddress in the database
        List<DeliveryAddress> deliveryAddressList = deliveryAddressRepository.findAll();
        assertThat(deliveryAddressList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMemberNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryAddressRepository.findAll().size();
        // set the field null
        deliveryAddress.setMemberName(null);

        // Create the DeliveryAddress, which fails.
        DeliveryAddressDTO deliveryAddressDTO = deliveryAddressMapper.toDto(deliveryAddress);

        restDeliveryAddressMockMvc.perform(post("/api/delivery-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryAddressDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryAddress> deliveryAddressList = deliveryAddressRepository.findAll();
        assertThat(deliveryAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMemberSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryAddressRepository.findAll().size();
        // set the field null
        deliveryAddress.setMemberSN(null);

        // Create the DeliveryAddress, which fails.
        DeliveryAddressDTO deliveryAddressDTO = deliveryAddressMapper.toDto(deliveryAddress);

        restDeliveryAddressMockMvc.perform(post("/api/delivery-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryAddressDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryAddress> deliveryAddressList = deliveryAddressRepository.findAll();
        assertThat(deliveryAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryAddressRepository.findAll().size();
        // set the field null
        deliveryAddress.setContact(null);

        // Create the DeliveryAddress, which fails.
        DeliveryAddressDTO deliveryAddressDTO = deliveryAddressMapper.toDto(deliveryAddress);

        restDeliveryAddressMockMvc.perform(post("/api/delivery-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryAddressDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryAddress> deliveryAddressList = deliveryAddressRepository.findAll();
        assertThat(deliveryAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProvinceIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryAddressRepository.findAll().size();
        // set the field null
        deliveryAddress.setProvince(null);

        // Create the DeliveryAddress, which fails.
        DeliveryAddressDTO deliveryAddressDTO = deliveryAddressMapper.toDto(deliveryAddress);

        restDeliveryAddressMockMvc.perform(post("/api/delivery-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryAddressDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryAddress> deliveryAddressList = deliveryAddressRepository.findAll();
        assertThat(deliveryAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryAddressRepository.findAll().size();
        // set the field null
        deliveryAddress.setCity(null);

        // Create the DeliveryAddress, which fails.
        DeliveryAddressDTO deliveryAddressDTO = deliveryAddressMapper.toDto(deliveryAddress);

        restDeliveryAddressMockMvc.perform(post("/api/delivery-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryAddressDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryAddress> deliveryAddressList = deliveryAddressRepository.findAll();
        assertThat(deliveryAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOffsetTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryAddressRepository.findAll().size();
        // set the field null
        deliveryAddress.setOffsetType(null);

        // Create the DeliveryAddress, which fails.
        DeliveryAddressDTO deliveryAddressDTO = deliveryAddressMapper.toDto(deliveryAddress);

        restDeliveryAddressMockMvc.perform(post("/api/delivery-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryAddressDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryAddress> deliveryAddressList = deliveryAddressRepository.findAll();
        assertThat(deliveryAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryAddressRepository.findAll().size();
        // set the field null
        deliveryAddress.setLongitude(null);

        // Create the DeliveryAddress, which fails.
        DeliveryAddressDTO deliveryAddressDTO = deliveryAddressMapper.toDto(deliveryAddress);

        restDeliveryAddressMockMvc.perform(post("/api/delivery-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryAddressDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryAddress> deliveryAddressList = deliveryAddressRepository.findAll();
        assertThat(deliveryAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLatitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryAddressRepository.findAll().size();
        // set the field null
        deliveryAddress.setLatitude(null);

        // Create the DeliveryAddress, which fails.
        DeliveryAddressDTO deliveryAddressDTO = deliveryAddressMapper.toDto(deliveryAddress);

        restDeliveryAddressMockMvc.perform(post("/api/delivery-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryAddressDTO)))
            .andExpect(status().isBadRequest());

        List<DeliveryAddress> deliveryAddressList = deliveryAddressRepository.findAll();
        assertThat(deliveryAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDeliveryAddresses() throws Exception {
        // Initialize the database
        deliveryAddressRepository.saveAndFlush(deliveryAddress);

        // Get all the deliveryAddressList
        restDeliveryAddressMockMvc.perform(get("/api/delivery-addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deliveryAddress.getId().intValue())))
            .andExpect(jsonPath("$.[*].memberName").value(hasItem(DEFAULT_MEMBER_NAME)))
            .andExpect(jsonPath("$.[*].memberSN").value(hasItem(DEFAULT_MEMBER_SN)))
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
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)));
    }
    
    @Test
    @Transactional
    public void getDeliveryAddress() throws Exception {
        // Initialize the database
        deliveryAddressRepository.saveAndFlush(deliveryAddress);

        // Get the deliveryAddress
        restDeliveryAddressMockMvc.perform(get("/api/delivery-addresses/{id}", deliveryAddress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(deliveryAddress.getId().intValue()))
            .andExpect(jsonPath("$.memberName").value(DEFAULT_MEMBER_NAME))
            .andExpect(jsonPath("$.memberSN").value(DEFAULT_MEMBER_SN))
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
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS));
    }

    @Test
    @Transactional
    public void getNonExistingDeliveryAddress() throws Exception {
        // Get the deliveryAddress
        restDeliveryAddressMockMvc.perform(get("/api/delivery-addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeliveryAddress() throws Exception {
        // Initialize the database
        deliveryAddressRepository.saveAndFlush(deliveryAddress);

        int databaseSizeBeforeUpdate = deliveryAddressRepository.findAll().size();

        // Update the deliveryAddress
        DeliveryAddress updatedDeliveryAddress = deliveryAddressRepository.findById(deliveryAddress.getId()).get();
        // Disconnect from session so that the updates on updatedDeliveryAddress are not directly saved in db
        em.detach(updatedDeliveryAddress);
        updatedDeliveryAddress
            .memberName(UPDATED_MEMBER_NAME)
            .memberSN(UPDATED_MEMBER_SN)
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
            .address(UPDATED_ADDRESS);
        DeliveryAddressDTO deliveryAddressDTO = deliveryAddressMapper.toDto(updatedDeliveryAddress);

        restDeliveryAddressMockMvc.perform(put("/api/delivery-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryAddressDTO)))
            .andExpect(status().isOk());

        // Validate the DeliveryAddress in the database
        List<DeliveryAddress> deliveryAddressList = deliveryAddressRepository.findAll();
        assertThat(deliveryAddressList).hasSize(databaseSizeBeforeUpdate);
        DeliveryAddress testDeliveryAddress = deliveryAddressList.get(deliveryAddressList.size() - 1);
        assertThat(testDeliveryAddress.getMemberName()).isEqualTo(UPDATED_MEMBER_NAME);
        assertThat(testDeliveryAddress.getMemberSN()).isEqualTo(UPDATED_MEMBER_SN);
        assertThat(testDeliveryAddress.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testDeliveryAddress.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testDeliveryAddress.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testDeliveryAddress.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testDeliveryAddress.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testDeliveryAddress.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testDeliveryAddress.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testDeliveryAddress.getOffsetType()).isEqualTo(UPDATED_OFFSET_TYPE);
        assertThat(testDeliveryAddress.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testDeliveryAddress.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testDeliveryAddress.getAddress()).isEqualTo(UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void updateNonExistingDeliveryAddress() throws Exception {
        int databaseSizeBeforeUpdate = deliveryAddressRepository.findAll().size();

        // Create the DeliveryAddress
        DeliveryAddressDTO deliveryAddressDTO = deliveryAddressMapper.toDto(deliveryAddress);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryAddressMockMvc.perform(put("/api/delivery-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeliveryAddress in the database
        List<DeliveryAddress> deliveryAddressList = deliveryAddressRepository.findAll();
        assertThat(deliveryAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDeliveryAddress() throws Exception {
        // Initialize the database
        deliveryAddressRepository.saveAndFlush(deliveryAddress);

        int databaseSizeBeforeDelete = deliveryAddressRepository.findAll().size();

        // Delete the deliveryAddress
        restDeliveryAddressMockMvc.perform(delete("/api/delivery-addresses/{id}", deliveryAddress.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DeliveryAddress> deliveryAddressList = deliveryAddressRepository.findAll();
        assertThat(deliveryAddressList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
