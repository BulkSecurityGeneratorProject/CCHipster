package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.AgiosCase;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AgiosCase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgiosCaseRepository extends JpaRepository<AgiosCase,Long> {
    
}
