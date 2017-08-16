package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AgiosHipsterApp;

import com.mycompany.myapp.domain.AgiosCase;
import com.mycompany.myapp.repository.AgiosCaseRepository;
import com.mycompany.myapp.service.AgiosCaseService;
import com.mycompany.myapp.repository.search.AgiosCaseSearchRepository;
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

    private static final Long DEFAULT_CASE_NR = 1L;
    private static final Long UPDATED_CASE_NR = 2L;

    private static final String DEFAULT_CASE_NO = "AAAAAAAAAA";
    private static final String UPDATED_CASE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PERSON_NR = "AAAAAAAAAA";
    private static final String UPDATED_PERSON_NR = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NR = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NR = "BBBBBBBBBB";

    private static final String DEFAULT_AGIOS_NODE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_AGIOS_NODE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WORKFLOW_UID = "AAAAAAAAAA";
    private static final String UPDATED_WORKFLOW_UID = "BBBBBBBBBB";

    private static final String DEFAULT_REASONS = "AAAAAAAAAA";
    private static final String UPDATED_REASONS = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_UID = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_UID = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENT_STEP_UID = "AAAAAAAAAA";
    private static final String UPDATED_CURRENT_STEP_UID = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CODE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EVT_OPEN = "AAAAAAAAAA";
    private static final String UPDATED_EVT_OPEN = "BBBBBBBBBB";

    private static final String DEFAULT_DISPLAY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EVTCLOSE = "AAAAAAAAAA";
    private static final String UPDATED_EVTCLOSE = "BBBBBBBBBB";

    private static final String DEFAULT_AGIOS_ENTITY = "AAAAAAAAAA";
    private static final String UPDATED_AGIOS_ENTITY = "BBBBBBBBBB";

    @Autowired
    private AgiosCaseRepository agiosCaseRepository;

    @Autowired
    private AgiosCaseMapper agiosCaseMapper;

    @Autowired
    private AgiosCaseService agiosCaseService;

    @Autowired
    private AgiosCaseSearchRepository agiosCaseSearchRepository;

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
            .caseNo(DEFAULT_CASE_NO)
            .personNr(DEFAULT_PERSON_NR)
            .companyNr(DEFAULT_COMPANY_NR)
            .agiosNodeName(DEFAULT_AGIOS_NODE_NAME)
            .workflowUid(DEFAULT_WORKFLOW_UID)
            .reasons(DEFAULT_REASONS)
            .statusUid(DEFAULT_STATUS_UID)
            .currentStepUid(DEFAULT_CURRENT_STEP_UID)
            .codeName(DEFAULT_CODE_NAME)
            .evtOpen(DEFAULT_EVT_OPEN)
            .displayName(DEFAULT_DISPLAY_NAME)
            .evtclose(DEFAULT_EVTCLOSE)
            .agiosEntity(DEFAULT_AGIOS_ENTITY);
        return agiosCase;
    }

    @Before
    public void initTest() {
        agiosCaseSearchRepository.deleteAll();
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
        assertThat(testAgiosCase.getCaseNo()).isEqualTo(DEFAULT_CASE_NO);
        assertThat(testAgiosCase.getPersonNr()).isEqualTo(DEFAULT_PERSON_NR);
        assertThat(testAgiosCase.getCompanyNr()).isEqualTo(DEFAULT_COMPANY_NR);
        assertThat(testAgiosCase.getAgiosNodeName()).isEqualTo(DEFAULT_AGIOS_NODE_NAME);
        assertThat(testAgiosCase.getWorkflowUid()).isEqualTo(DEFAULT_WORKFLOW_UID);
        assertThat(testAgiosCase.getReasons()).isEqualTo(DEFAULT_REASONS);
        assertThat(testAgiosCase.getStatusUid()).isEqualTo(DEFAULT_STATUS_UID);
        assertThat(testAgiosCase.getCurrentStepUid()).isEqualTo(DEFAULT_CURRENT_STEP_UID);
        assertThat(testAgiosCase.getCodeName()).isEqualTo(DEFAULT_CODE_NAME);
        assertThat(testAgiosCase.getEvtOpen()).isEqualTo(DEFAULT_EVT_OPEN);
        assertThat(testAgiosCase.getDisplayName()).isEqualTo(DEFAULT_DISPLAY_NAME);
        assertThat(testAgiosCase.getEvtclose()).isEqualTo(DEFAULT_EVTCLOSE);
        assertThat(testAgiosCase.getAgiosEntity()).isEqualTo(DEFAULT_AGIOS_ENTITY);

        // Validate the AgiosCase in Elasticsearch
        AgiosCase agiosCaseEs = agiosCaseSearchRepository.findOne(testAgiosCase.getId());
        assertThat(agiosCaseEs).isEqualToComparingFieldByField(testAgiosCase);
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
            .andExpect(jsonPath("$.[*].caseNr").value(hasItem(DEFAULT_CASE_NR.intValue())))
            .andExpect(jsonPath("$.[*].caseNo").value(hasItem(DEFAULT_CASE_NO.toString())))
            .andExpect(jsonPath("$.[*].personNr").value(hasItem(DEFAULT_PERSON_NR.toString())))
            .andExpect(jsonPath("$.[*].companyNr").value(hasItem(DEFAULT_COMPANY_NR.toString())))
            .andExpect(jsonPath("$.[*].agiosNodeName").value(hasItem(DEFAULT_AGIOS_NODE_NAME.toString())))
            .andExpect(jsonPath("$.[*].workflowUid").value(hasItem(DEFAULT_WORKFLOW_UID.toString())))
            .andExpect(jsonPath("$.[*].reasons").value(hasItem(DEFAULT_REASONS.toString())))
            .andExpect(jsonPath("$.[*].statusUid").value(hasItem(DEFAULT_STATUS_UID.toString())))
            .andExpect(jsonPath("$.[*].currentStepUid").value(hasItem(DEFAULT_CURRENT_STEP_UID.toString())))
            .andExpect(jsonPath("$.[*].codeName").value(hasItem(DEFAULT_CODE_NAME.toString())))
            .andExpect(jsonPath("$.[*].evtOpen").value(hasItem(DEFAULT_EVT_OPEN.toString())))
            .andExpect(jsonPath("$.[*].displayName").value(hasItem(DEFAULT_DISPLAY_NAME.toString())))
            .andExpect(jsonPath("$.[*].evtclose").value(hasItem(DEFAULT_EVTCLOSE.toString())))
            .andExpect(jsonPath("$.[*].agiosEntity").value(hasItem(DEFAULT_AGIOS_ENTITY.toString())));
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
            .andExpect(jsonPath("$.caseNr").value(DEFAULT_CASE_NR.intValue()))
            .andExpect(jsonPath("$.caseNo").value(DEFAULT_CASE_NO.toString()))
            .andExpect(jsonPath("$.personNr").value(DEFAULT_PERSON_NR.toString()))
            .andExpect(jsonPath("$.companyNr").value(DEFAULT_COMPANY_NR.toString()))
            .andExpect(jsonPath("$.agiosNodeName").value(DEFAULT_AGIOS_NODE_NAME.toString()))
            .andExpect(jsonPath("$.workflowUid").value(DEFAULT_WORKFLOW_UID.toString()))
            .andExpect(jsonPath("$.reasons").value(DEFAULT_REASONS.toString()))
            .andExpect(jsonPath("$.statusUid").value(DEFAULT_STATUS_UID.toString()))
            .andExpect(jsonPath("$.currentStepUid").value(DEFAULT_CURRENT_STEP_UID.toString()))
            .andExpect(jsonPath("$.codeName").value(DEFAULT_CODE_NAME.toString()))
            .andExpect(jsonPath("$.evtOpen").value(DEFAULT_EVT_OPEN.toString()))
            .andExpect(jsonPath("$.displayName").value(DEFAULT_DISPLAY_NAME.toString()))
            .andExpect(jsonPath("$.evtclose").value(DEFAULT_EVTCLOSE.toString()))
            .andExpect(jsonPath("$.agiosEntity").value(DEFAULT_AGIOS_ENTITY.toString()));
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
        agiosCaseSearchRepository.save(agiosCase);
        int databaseSizeBeforeUpdate = agiosCaseRepository.findAll().size();

        // Update the agiosCase
        AgiosCase updatedAgiosCase = agiosCaseRepository.findOne(agiosCase.getId());
        updatedAgiosCase
            .caseNr(UPDATED_CASE_NR)
            .caseNo(UPDATED_CASE_NO)
            .personNr(UPDATED_PERSON_NR)
            .companyNr(UPDATED_COMPANY_NR)
            .agiosNodeName(UPDATED_AGIOS_NODE_NAME)
            .workflowUid(UPDATED_WORKFLOW_UID)
            .reasons(UPDATED_REASONS)
            .statusUid(UPDATED_STATUS_UID)
            .currentStepUid(UPDATED_CURRENT_STEP_UID)
            .codeName(UPDATED_CODE_NAME)
            .evtOpen(UPDATED_EVT_OPEN)
            .displayName(UPDATED_DISPLAY_NAME)
            .evtclose(UPDATED_EVTCLOSE)
            .agiosEntity(UPDATED_AGIOS_ENTITY);
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
        assertThat(testAgiosCase.getCaseNo()).isEqualTo(UPDATED_CASE_NO);
        assertThat(testAgiosCase.getPersonNr()).isEqualTo(UPDATED_PERSON_NR);
        assertThat(testAgiosCase.getCompanyNr()).isEqualTo(UPDATED_COMPANY_NR);
        assertThat(testAgiosCase.getAgiosNodeName()).isEqualTo(UPDATED_AGIOS_NODE_NAME);
        assertThat(testAgiosCase.getWorkflowUid()).isEqualTo(UPDATED_WORKFLOW_UID);
        assertThat(testAgiosCase.getReasons()).isEqualTo(UPDATED_REASONS);
        assertThat(testAgiosCase.getStatusUid()).isEqualTo(UPDATED_STATUS_UID);
        assertThat(testAgiosCase.getCurrentStepUid()).isEqualTo(UPDATED_CURRENT_STEP_UID);
        assertThat(testAgiosCase.getCodeName()).isEqualTo(UPDATED_CODE_NAME);
        assertThat(testAgiosCase.getEvtOpen()).isEqualTo(UPDATED_EVT_OPEN);
        assertThat(testAgiosCase.getDisplayName()).isEqualTo(UPDATED_DISPLAY_NAME);
        assertThat(testAgiosCase.getEvtclose()).isEqualTo(UPDATED_EVTCLOSE);
        assertThat(testAgiosCase.getAgiosEntity()).isEqualTo(UPDATED_AGIOS_ENTITY);

        // Validate the AgiosCase in Elasticsearch
        AgiosCase agiosCaseEs = agiosCaseSearchRepository.findOne(testAgiosCase.getId());
        assertThat(agiosCaseEs).isEqualToComparingFieldByField(testAgiosCase);
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
        agiosCaseSearchRepository.save(agiosCase);
        int databaseSizeBeforeDelete = agiosCaseRepository.findAll().size();

        // Get the agiosCase
        restAgiosCaseMockMvc.perform(delete("/api/agios-cases/{id}", agiosCase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean agiosCaseExistsInEs = agiosCaseSearchRepository.exists(agiosCase.getId());
        assertThat(agiosCaseExistsInEs).isFalse();

        // Validate the database is empty
        List<AgiosCase> agiosCaseList = agiosCaseRepository.findAll();
        assertThat(agiosCaseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchAgiosCase() throws Exception {
        // Initialize the database
        agiosCaseRepository.saveAndFlush(agiosCase);
        agiosCaseSearchRepository.save(agiosCase);

        // Search the agiosCase
        restAgiosCaseMockMvc.perform(get("/api/_search/agios-cases?query=id:" + agiosCase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agiosCase.getId().intValue())))
            .andExpect(jsonPath("$.[*].caseNr").value(hasItem(DEFAULT_CASE_NR.intValue())))
            .andExpect(jsonPath("$.[*].caseNo").value(hasItem(DEFAULT_CASE_NO.toString())))
            .andExpect(jsonPath("$.[*].personNr").value(hasItem(DEFAULT_PERSON_NR.toString())))
            .andExpect(jsonPath("$.[*].companyNr").value(hasItem(DEFAULT_COMPANY_NR.toString())))
            .andExpect(jsonPath("$.[*].agiosNodeName").value(hasItem(DEFAULT_AGIOS_NODE_NAME.toString())))
            .andExpect(jsonPath("$.[*].workflowUid").value(hasItem(DEFAULT_WORKFLOW_UID.toString())))
            .andExpect(jsonPath("$.[*].reasons").value(hasItem(DEFAULT_REASONS.toString())))
            .andExpect(jsonPath("$.[*].statusUid").value(hasItem(DEFAULT_STATUS_UID.toString())))
            .andExpect(jsonPath("$.[*].currentStepUid").value(hasItem(DEFAULT_CURRENT_STEP_UID.toString())))
            .andExpect(jsonPath("$.[*].codeName").value(hasItem(DEFAULT_CODE_NAME.toString())))
            .andExpect(jsonPath("$.[*].evtOpen").value(hasItem(DEFAULT_EVT_OPEN.toString())))
            .andExpect(jsonPath("$.[*].displayName").value(hasItem(DEFAULT_DISPLAY_NAME.toString())))
            .andExpect(jsonPath("$.[*].evtclose").value(hasItem(DEFAULT_EVTCLOSE.toString())))
            .andExpect(jsonPath("$.[*].agiosEntity").value(hasItem(DEFAULT_AGIOS_ENTITY.toString())));
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
