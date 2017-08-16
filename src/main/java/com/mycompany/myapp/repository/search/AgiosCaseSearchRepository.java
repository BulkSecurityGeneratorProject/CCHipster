package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.AgiosCase;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AgiosCase entity.
 */
public interface AgiosCaseSearchRepository extends ElasticsearchRepository<AgiosCase, Long> {
}
