package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.AgiosCaseDTO;
import java.util.List;

/**
 * Service Interface for managing AgiosCase.
 */
public interface AgiosCaseService {

    /**
     * Save a agiosCase.
     *
     * @param agiosCaseDTO the entity to save
     * @return the persisted entity
     */
    AgiosCaseDTO save(AgiosCaseDTO agiosCaseDTO);

    /**
     *  Get all the agiosCases.
     *
     *  @return the list of entities
     */
    List<AgiosCaseDTO> findAll();

    /**
     *  Get the "id" agiosCase.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AgiosCaseDTO findOne(Long id);

    /**
     *  Delete the "id" agiosCase.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
