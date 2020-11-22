package com.citel.citelapp.service;

import com.citel.citelapp.domain.Doador;
import com.citel.citelapp.repository.DoadorRepository;
import com.citel.citelapp.repository.search.DoadorSearchRepository;
import com.citel.citelapp.service.dto.DoadorDTO;
import com.citel.citelapp.service.mapper.DoadorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Doador}.
 */
@Service
@Transactional
public class DoadorService {

    private final Logger log = LoggerFactory.getLogger(DoadorService.class);

    private final DoadorRepository doadorRepository;

    private final DoadorMapper doadorMapper;

    private final DoadorSearchRepository doadorSearchRepository;

    public DoadorService(DoadorRepository doadorRepository, DoadorMapper doadorMapper, DoadorSearchRepository doadorSearchRepository) {
        this.doadorRepository = doadorRepository;
        this.doadorMapper = doadorMapper;
        this.doadorSearchRepository = doadorSearchRepository;
    }

    /**
     * Save a doador.
     *
     * @param doadorDTO the entity to save.
     * @return the persisted entity.
     */
    public DoadorDTO save(DoadorDTO doadorDTO) {
        log.debug("Request to save Doador : {}", doadorDTO);
        Doador doador = doadorMapper.toEntity(doadorDTO);
        doador = doadorRepository.save(doador);
        DoadorDTO result = doadorMapper.toDto(doador);
        doadorSearchRepository.save(doador);
        return result;
    }

    /**
     * Get all the doadors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DoadorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Doadors");
        return doadorRepository.findAll(pageable)
            .map(doadorMapper::toDto);
    }


    /**
     * Get one doador by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DoadorDTO> findOne(Long id) {
        log.debug("Request to get Doador : {}", id);
        return doadorRepository.findById(id)
            .map(doadorMapper::toDto);
    }

    /**
     * Delete the doador by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Doador : {}", id);
        doadorRepository.deleteById(id);
        doadorSearchRepository.deleteById(id);
    }

    /**
     * Search for the doador corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DoadorDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Doadors for query {}", query);
        return doadorSearchRepository.search(queryStringQuery(query), pageable)
            .map(doadorMapper::toDto);
    }
}
