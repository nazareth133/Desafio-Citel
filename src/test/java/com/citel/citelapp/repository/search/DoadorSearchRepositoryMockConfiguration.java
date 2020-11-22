package com.citel.citelapp.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link DoadorSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class DoadorSearchRepositoryMockConfiguration {

    @MockBean
    private DoadorSearchRepository mockDoadorSearchRepository;

}
