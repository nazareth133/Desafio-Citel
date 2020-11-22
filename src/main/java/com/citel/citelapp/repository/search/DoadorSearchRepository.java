package com.citel.citelapp.repository.search;

import com.citel.citelapp.domain.Doador;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Doador} entity.
 */
public interface DoadorSearchRepository extends ElasticsearchRepository<Doador, Long> {
}
