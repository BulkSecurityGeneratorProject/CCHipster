package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AgiosHipsterApp;

import com.mycompany.myapp.domain.AgiosCase;
import com.mycompany.myapp.repository.AgiosCaseRepository;
import com.mycompany.myapp.service.AgiosCaseService;
import com.mycompany.myapp.service.dto.AgiosCaseDTO;
import com.mycompany.myapp.service.mapper.AgiosCaseMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AgiosCaseResource REST controller.
 *
 * @see AgiosCaseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AgiosHipsterApp.class)
public class AgiosCaseResourceIntTest {

    private static final String DEFAULT_CASE_NR = "AAAAAAAAAA";
    private static final String UPDATED_CASE_NR = "BBBBBBBBBB";

    private static final String DEFAULT_PERSON_NR = "AAAAAAAAAA";
    private static final String UPDATED_PERSON_NR = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NR = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NR = "BBBBBBBBBB";

    private static final String DEFAULT_AGIOS_NODE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_AGIOS_NODE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WORKFLOW_UID = "AAAAAAAAAA";
    private static final String UPDATED_WORKFLOW_UID = "BBBBBBBBBB";

    private static final String DEFAULT_CASE_NO = "AAAAAAAAAA";
    private static final String UPDATED_CASE_NO = "BBBBBBBBBB";

    @Autowired
    private AgiosCaseRepository agiosCaseRepository;

    @Autowired
    private AgiosCaseMapper agiosCaseMapper;

    @Autowired
    private AgiosCaseService agiosCaseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAgiosCaseMockMvc;

    private AgiosCase agiosCase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AgiosCaseResource agiosCaseResource = new AgiosCaseResource(agiosCaseService);
        this.restAgiosCaseMockMvc = MockMvcBuilders.standaloneSetup(agiosCaseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AgiosCase createEntity(EntityManager em) {
        AgiosCase agiosCase = new AgiosCase()
            .caseNr(DEFAULT_CASE_NR)
            .personNr(DEFAULT_PERSON_NR)
            .companyNr(DEFAULT_COMPANY_NR)
            .agiosNodeName(DEFAULT_AGIOS_NODE_NAME)
            .workflowUid(DEFAULT_WORKFLOW_UID)
            .caseNo(DEFAULT_CASE_NO);
        return agiosCase;
    }

    @Before
    public void initTest() {
        agiosCase = createEntity(em);
    }

    @Test
    @Transactional
    public void createAgiosCase() throws Exception {
        int databaseSizeBeforeCreate = agiosCaseRepository.findAll().size();

        // Create the AgiosCase
        AgiosCaseDTO agiosCaseDTO = agiosCaseMapper.toDto(agiosCase);
        restAgiosCaseMockMvc.perform(post("/api/agios-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agiosCaseDTO)))
            .andExpect(status().isCreated());

        // Validate the AgiosCase in the database
        List<AgiosCase> agiosCaseList = agiosCaseRepository.findAll();
        assertThat(agiosCaseList).hasSize(databaseSizeBeforeCreate + 1);
        AgiosCase testAgiosCase = agiosCaseList.get(agiosCaseList.size() - 1);
        assertThat(testAgiosCase.getCaseNr()).isEqualTo(DEFAULT_CASE_NR);
        assertThat(testAgiosCase.getPersonNr()).isEqualTo(DEFAULT_PERSON_NR);
        assertThat(testAgiosCase.getCompanyNr()).isEqualTo(DEFAULT_COMPANY_NR);
        assertThat(testAgiosCase.getAgiosNodeName()).isEqualTo(DEFAULT_AGIOS_NODE_NAME);
        assertThat(testAgiosCase.getWorkflowUid()).isEqualTo(DEFAULT_WORKFLOW_UID);
        assertThat(testAgiosCase.getCaseNo()).isEqualTo(DEFAULT_CASE_NO);
    }

    @Test
    @Transactional
    public void createAgiosCaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = agiosCaseRepository.findAll().size();

        // Create the AgiosCase with an existing ID
        agiosCase.setId(1L);
        AgiosCaseDTO agiosCaseDTO = agiosCaseMapper.toDto(agiosCase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAgiosCaseMockMvc.perform(post("/api/agios-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agiosCaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<AgiosCase> agiosCaseList = agiosCaseRepository.findAll();
        assertThat(agiosCaseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAgiosCases() throws Exception {
        // Initialize the database
        agiosCaseRepository.saveAndFlush(agiosCase);

        // Get all the agiosCaseList
        restAgiosCaseMockMvc.perform(get("/api/agios-cases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agiosCase.getId().intValue())))
            .andExpect(jsonPath("$.[*].caseNr").value(hasItem(DEFAULT_CASE_NR.toString())))
            .andExpect(jsonPath("$.[*].personNr").value(hasItem(DEFAULT_PERSON_NR.toString())))
            .andExpect(jsonPath("$.[*].companyNr").value(hasItem(DEFAULT_COMPANY_NR.toString())))
            .andExpect(jsonPath("$.[*].agiosNodeName").value(hasItem(DEFAULT_AGIOS_NODE_NAME.toString())))
            .andExpect(jsonPath("$.[*].workflowUid").value(hasItem(DEFAULT_WORKFLOW_UID.toString())))
            .andExpect(jsonPath("$.[*].caseNo").value(hasItem(DEFAULT_CASE_NO.toString())));
    }

    @Test
    @Transactional
    public void getAgiosCase() throws Exception {
        // Initialize the database
        agiosCaseRepository.saveAndFlush(agiosCase);

        // Get the agiosCase
        restAgiosCaseMockMvc.perform(get("/api/agios-cases/{id}", agiosCase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(agiosCase.getId().intValue()))
            .andExpect(jsonPath("$.caseNr").value(DEFAULT_CASE_NR.toString()))
            .andExpect(jsonPath("$.personNr").value(DEFAULT_PERSON_NR.toString()))
            .andExpect(jsonPath("$.companyNr").value(DEFAULT_COMPANY_NR.toString()))
            .andExpect(jsonPath("$.agiosNodeName").value(DEFAULT_AGIOS_NODE_NAME.toString()))
            .andExpect(jsonPath("$.workflowUid").value(DEFAULT_WORKFLOW_UID.toString()))
            .andExpect(jsonPath("$.caseNo").value(DEFAULT_CASE_NO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAgiosCase() throws Exception {
        // Get the agiosCase
        restAgiosCaseMockMvc.perform(get("/api/agios-cases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAgiosCase() throws Exception {
        // Initialize the database
        agiosCaseRepository.saveAndFlush(agiosCase);
        int databaseSizeBeforeUpdate = agiosCaseRepository.findAll().size();

        // Update the agiosCase
        AgiosCase updatedAgiosCase = agiosCaseRepository.findOne(agiosCase.getId());
        updatedAgiosCase
            .caseNr(UPDATED_CASE_NR)
            .personNr(UPDATED_PERSON_NR)
            .companyNr(UPDATED_COMPANY_NR)
            .agiosNodeName(UPDATED_AGIOS_NODE_NAME)
            .workflowUid(UPDATED_WORKFLOW_UID)
            .caseNo(UPDATED_CASE_NO);
        AgiosCaseDTO agiosCaseDTO = agiosCaseMapper.toDto(updatedAgiosCase);

        restAgiosCaseMockMvc.perform(put("/api/agios-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agiosCaseDTO)))
            .andExpect(status().isOk());

        // Validate the AgiosCase in the database
        List<AgiosCase> agiosCaseList = agiosCaseRepository.findAll();
        assertThat(agiosCaseList).hasSize(databaseSizeBeforeUpdate);
        AgiosCase testAgiosCase = agiosCaseList.get(agiosCaseList.size() - 1);
        assertThat(testAgiosCase.getCaseNr()).isEqualTo(UPDATED_CASE_NR);
        assertThat(testAgiosCase.getPersonNr()).isEqualTo(UPDATED_PERSON_NR);
        assertThat(testAgiosCase.getCompanyNr()).isEqualTo(UPDATED_COMPANY_NR);
        assertThat(testAgiosCase.getAgiosNodeName()).isEqualTo(UPDATED_AGIOS_NODE_NAME);
        assertThat(testAgiosCase.getWorkflowUid()).isEqualTo(UPDATED_WORKFLOW_UID);
        assertThat(testAgiosCase.getCaseNo()).isEqualTo(UPDATED_CASE_NO);
    }

    @Test
    @Transactional
    public void updateNonExistingAgiosCase() throws Exception {
        int databaseSizeBeforeUpdate = agiosCaseRepository.findAll().size();

        // Create the AgiosCase
        AgiosCaseDTO agiosCaseDTO = agiosCaseMapper.toDto(agiosCase);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAgiosCaseMockMvc.perform(put("/api/agios-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agiosCaseDTO)))
            .andExpect(status().isCreated());

        // Validate the AgiosCase in the database
        List<AgiosCase> agiosCaseList = agiosCaseRepository.findAll();
        assertThat(agiosCaseList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAgiosCase() throws Exception {
        // Initialize the database
        agiosCaseRepository.saveAndFlush(agiosCase);
        int databaseSizeBeforeDelete = agiosCaseRepository.findAll().size();

        // Get the agiosCase
        restAgiosCaseMockMvc.perform(delete("/api/agios-cases/{id}", agiosCase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AgiosCase> agiosCaseList = agiosCaseRepository.findAll();
        assertThat(agiosCaseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AgiosCase.class);
        AgiosCase agiosCase1 = new AgiosCase();
        agiosCase1.setId(1L);
        AgiosCase agiosCase2 = new AgiosCase();
        agiosCase2.setId(agiosCase1.getId());
        assertThat(agiosCase1).isEqualTo(agiosCase2);
        agiosCase2.setId(2L);
        assertThat(agiosCase1).isNotEqualTo(agiosCase2);
        agiosCase1.setId(null);
        assertThat(agiosCase1).isNotEqualTo(agiosCase2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AgiosCaseDTO.class);
        AgiosCaseDTO agiosCaseDTO1 = new AgiosCaseDTO();
        agiosCaseDTO1.setId(1L);
        AgiosCaseDTO agiosCaseDTO2 = new AgiosCaseDTO();
        assertThat(agiosCaseDTO1).isNotEqualTo(agiosCaseDTO2);
        agiosCaseDTO2.setId(agiosCaseDTO1.getId());
        assertThat(agiosCaseDTO1).isEqualTo(agiosCaseDTO2);
        agiosCaseDTO2.setId(2L);
        assertThat(agiosCaseDTO1).isNotEqualTo(agiosCaseDTO2);
        agiosCaseDTO1.setId(null);
        assertThat(agiosCaseDTO1).isNotEqualTo(agiosCaseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(agiosCaseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(agiosCaseMapper.fromId(null)).isNull();
    }
}
