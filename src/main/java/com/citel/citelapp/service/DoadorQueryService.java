package com.citel.citelapp.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.citel.citelapp.domain.Doador;
import com.citel.citelapp.domain.*; // for static metamodels
import com.citel.citelapp.repository.DoadorRepository;
import com.citel.citelapp.repository.search.DoadorSearchRepository;
import com.citel.citelapp.service.dto.DoadorCriteria;
import com.citel.citelapp.service.dto.DoadorDTO;
import com.citel.citelapp.service.mapper.DoadorMapper;

/**
 * Service for executing complex queries for {@link Doador} entities in the database.
 * The main input is a {@link DoadorCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DoadorDTO} or a {@link Page} of {@link DoadorDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DoadorQueryService extends QueryService<Doador> {

    private final Logger log = LoggerFactory.getLogger(DoadorQueryService.class);

    private final DoadorRepository doadorRepository;

    private final DoadorMapper doadorMapper;

    private final DoadorSearchRepository doadorSearchRepository;

    public DoadorQueryService(DoadorRepository doadorRepository, DoadorMapper doadorMapper, DoadorSearchRepository doadorSearchRepository) {
        this.doadorRepository = doadorRepository;
        this.doadorMapper = doadorMapper;
        this.doadorSearchRepository = doadorSearchRepository;
    }

    /**
     * Return a {@link List} of {@link DoadorDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DoadorDTO> findByCriteria(DoadorCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Doador> specification = createSpecification(criteria);
        return doadorMapper.toDto(doadorRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DoadorDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DoadorDTO> findByCriteria(DoadorCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Doador> specification = createSpecification(criteria);
        return doadorRepository.findAll(specification, page)
            .map(doadorMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DoadorCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Doador> specification = createSpecification(criteria);
        return doadorRepository.count(specification);
    }

    /**
     * Function to convert {@link DoadorCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Doador> createSpecification(DoadorCriteria criteria) {
        Specification<Doador> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Doador_.id));
            }
            if (criteria.getNome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNome(), Doador_.nome));
            }
            if (criteria.getCpf() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCpf(), Doador_.cpf));
            }
            if (criteria.getRg() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRg(), Doador_.rg));
            }
            if (criteria.getDataNasc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDataNasc(), Doador_.dataNasc));
            }
            if (criteria.getSexo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSexo(), Doador_.sexo));
            }
            if (criteria.getMae() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMae(), Doador_.mae));
            }
            if (criteria.getPai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPai(), Doador_.pai));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Doador_.email));
            }
            if (criteria.getCep() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCep(), Doador_.cep));
            }
            if (criteria.getEndereco() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEndereco(), Doador_.endereco));
            }
            if (criteria.getNumero() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumero(), Doador_.numero));
            }
            if (criteria.getBairro() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBairro(), Doador_.bairro));
            }
            if (criteria.getCidade() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCidade(), Doador_.cidade));
            }
            if (criteria.getEstado() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEstado(), Doador_.estado));
            }
            if (criteria.getTelefoneFixo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefoneFixo(), Doador_.telefoneFixo));
            }
            if (criteria.getCelular() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCelular(), Doador_.celular));
            }
            if (criteria.getAltura() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAltura(), Doador_.altura));
            }
            if (criteria.getPeso() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPeso(), Doador_.peso));
            }
            if (criteria.getTipoSanguineo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTipoSanguineo(), Doador_.tipoSanguineo));
            }
        }
        return specification;
    }
}
