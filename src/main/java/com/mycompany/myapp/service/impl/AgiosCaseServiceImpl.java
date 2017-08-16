package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.AgiosCaseService;
import com.mycompany.myapp.domain.AgiosCase;
import com.mycompany.myapp.repository.AgiosCaseRepository;
import com.mycompany.myapp.repository.search.AgiosCaseSearchRepository;
import com.mycompany.myapp.service.dto.AgiosCaseDTO;
import com.mycompany.myapp.service.mapper.AgiosCaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AgiosCase.
 */
@Service
@Transactional
public class AgiosCaseServiceImpl implements AgiosCaseService{

    private final Logger log = LoggerFactory.getLogger(AgiosCaseServiceImpl.class);

    private final AgiosCaseRepository agiosCaseRepository;

    private final AgiosCaseMapper agiosCaseMapper;

    private final AgiosCaseSearchRepository agiosCaseSearchRepository;

    public AgiosCaseServiceImpl(AgiosCaseRepository agiosCaseRepository, AgiosCaseMapper agiosCaseMapper, AgiosCaseSearchRepository agiosCaseSearchRepository) {
        this.agiosCaseRepository = agiosCaseRepository;
        this.agiosCaseMapper = agiosCaseMapper;
        this.agiosCaseSearchRepository = agiosCaseSearchRepository;
    }

    /**
     * Save a agiosCase.
     *
     * @param agiosCaseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AgiosCaseDTO save(AgiosCaseDTO agiosCaseDTO) {
        log.debug("Request to save AgiosCase : {}", agiosCaseDTO);
        AgiosCase agiosCase = agiosCaseMapper.toEntity(agiosCaseDTO);
        agiosCase = agiosCaseRepository.save(agiosCase);
        AgiosCaseDTO result = agiosCaseMapper.toDto(agiosCase);
        agiosCaseSearchRepository.save(agiosCase);
        return result;
    }

    /**
     *  Get all the agiosCases.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AgiosCaseDTO> findAll() {
        log.debug("Request to get all AgiosCases");
        return agiosCaseRepository.findAll().stream()
            .map(agiosCaseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one agiosCase by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AgiosCaseDTO findOne(Long id) {
        log.debug("Request to get AgiosCase : {}", id);
        AgiosCase agiosCase = agiosCaseRepository.findOne(id);
        return agiosCaseMapper.toDto(agiosCase);
    }

    /**
     *  Delete the  agiosCase by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AgiosCase : {}", id);
        agiosCaseRepository.delete(id);
        agiosCaseSearchRepository.delete(id);
    }

    /**
     * Search for the agiosCase corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AgiosCaseDTO> search(String query) {
        log.debug("Request to search AgiosCases for query {}", query);
        return StreamSupport
            .stream(agiosCaseSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(agiosCaseMapper::toDto)
            .collect(Collectors.toList());
    }
}
