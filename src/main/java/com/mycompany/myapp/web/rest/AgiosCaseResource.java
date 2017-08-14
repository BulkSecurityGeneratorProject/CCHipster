package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.service.AgiosCaseService;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.AgiosCaseDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AgiosCase.
 */
@RestController
@RequestMapping("/api")
public class AgiosCaseResource {

    private final Logger log = LoggerFactory.getLogger(AgiosCaseResource.class);

    private static final String ENTITY_NAME = "agiosCase";

    private final AgiosCaseService agiosCaseService;

    public AgiosCaseResource(AgiosCaseService agiosCaseService) {
        this.agiosCaseService = agiosCaseService;
    }

    /**
     * POST  /agios-cases : Create a new agiosCase.
     *
     * @param agiosCaseDTO the agiosCaseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new agiosCaseDTO, or with status 400 (Bad Request) if the agiosCase has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/agios-cases")
    @Timed
    public ResponseEntity<AgiosCaseDTO> createAgiosCase(@RequestBody AgiosCaseDTO agiosCaseDTO) throws URISyntaxException {
        log.debug("REST request to save AgiosCase : {}", agiosCaseDTO);
        if (agiosCaseDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new agiosCase cannot already have an ID")).body(null);
        }
        AgiosCaseDTO result = agiosCaseService.save(agiosCaseDTO);
        return ResponseEntity.created(new URI("/api/agios-cases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /agios-cases : Updates an existing agiosCase.
     *
     * @param agiosCaseDTO the agiosCaseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated agiosCaseDTO,
     * or with status 400 (Bad Request) if the agiosCaseDTO is not valid,
     * or with status 500 (Internal Server Error) if the agiosCaseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/agios-cases")
    @Timed
    public ResponseEntity<AgiosCaseDTO> updateAgiosCase(@RequestBody AgiosCaseDTO agiosCaseDTO) throws URISyntaxException {
        log.debug("REST request to update AgiosCase : {}", agiosCaseDTO);
        if (agiosCaseDTO.getId() == null) {
            return createAgiosCase(agiosCaseDTO);
        }
        AgiosCaseDTO result = agiosCaseService.save(agiosCaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, agiosCaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /agios-cases : get all the agiosCases.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of agiosCases in body
     */
    @GetMapping("/agios-cases")
    @Timed
    public List<AgiosCaseDTO> getAllAgiosCases() {
        log.debug("REST request to get all AgiosCases");
        return agiosCaseService.findAll();
    }

    /**
     * GET  /agios-cases/:id : get the "id" agiosCase.
     *
     * @param id the id of the agiosCaseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the agiosCaseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/agios-cases/{id}")
    @Timed
    public ResponseEntity<AgiosCaseDTO> getAgiosCase(@PathVariable Long id) {
        log.debug("REST request to get AgiosCase : {}", id);
        AgiosCaseDTO agiosCaseDTO = agiosCaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(agiosCaseDTO));
    }

    /**
     * DELETE  /agios-cases/:id : delete the "id" agiosCase.
     *
     * @param id the id of the agiosCaseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/agios-cases/{id}")
    @Timed
    public ResponseEntity<Void> deleteAgiosCase(@PathVariable Long id) {
        log.debug("REST request to delete AgiosCase : {}", id);
        agiosCaseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
