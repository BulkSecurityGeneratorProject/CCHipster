package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.AgiosCaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AgiosCase and its DTO AgiosCaseDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AgiosCaseMapper extends EntityMapper <AgiosCaseDTO, AgiosCase> {
    
    
    default AgiosCase fromId(Long id) {
        if (id == null) {
            return null;
        }
        AgiosCase agiosCase = new AgiosCase();
        agiosCase.setId(id);
        return agiosCase;
    }
}
