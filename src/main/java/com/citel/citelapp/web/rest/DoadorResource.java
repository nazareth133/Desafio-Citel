package com.citel.citelapp.web.rest;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.citel.citelapp.service.DoadorQueryService;
import com.citel.citelapp.service.DoadorService;
import com.citel.citelapp.service.dto.DoadorCriteria;
import com.citel.citelapp.service.dto.DoadorDTO;
import com.google.gson.Gson;

import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.citel.citelapp.domain.Doador}.
 */
@RestController
@RequestMapping("/api")
public class DoadorResource {

    private final Logger log = LoggerFactory.getLogger(DoadorResource.class);

    private final DoadorService doadorService;

    private final DoadorQueryService doadorQueryService;

    public DoadorResource(DoadorService doadorService, DoadorQueryService doadorQueryService) {
        this.doadorService = doadorService;
        this.doadorQueryService = doadorQueryService;
    }

    /**
     * {@code GET  /doadors} : get all the doadors.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of doadors in body.
     */
    @GetMapping("/doadors")
    public ResponseEntity<List<DoadorDTO>> getAllDoadors(DoadorCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Doadors by criteria: {}", criteria);
        Page<DoadorDTO> page = doadorQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    
    
    @RequestMapping(method = RequestMethod.POST, value = "/doadors/upload")
    public ResponseEntity uploadJson(@PathParam("data") String file) {
        log.debug("REST request to upload:{}", file);

        Gson gson = new Gson();
        DoadorDTO arquivo = gson.fromJson(file, DoadorDTO.class);
        return ResponseEntity.ok().body( doadorService.save(arquivo));
        		
    }
    
    /**
     * {@code GET  /doadors/count} : count all the doadors.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/doadors/count")
    public ResponseEntity<Long> countDoadors(DoadorCriteria criteria) {
        log.debug("REST request to count Doadors by criteria: {}", criteria);
        return ResponseEntity.ok().body(doadorQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /doadors/:id} : get the "id" doador.
     *
     * @param id the id of the doadorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the doadorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/doadors/{id}")
    public ResponseEntity<DoadorDTO> getDoador(@PathVariable Long id) {
        log.debug("REST request to get Doador : {}", id);
        Optional<DoadorDTO> doadorDTO = doadorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(doadorDTO);
    }

    /**
     * {@code SEARCH  /_search/doadors?query=:query} : search for the doador corresponding
     * to the query.
     *
     * @param query the query of the doador search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/doadors")
    public ResponseEntity<List<DoadorDTO>> searchDoadors(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Doadors for query {}", query);
        Page<DoadorDTO> page = doadorService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
        }
}
