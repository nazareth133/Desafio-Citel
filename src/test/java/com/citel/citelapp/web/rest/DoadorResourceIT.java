package com.citel.citelapp.web.rest;

import com.citel.citelapp.CitelApp;
import com.citel.citelapp.domain.Doador;
import com.citel.citelapp.repository.DoadorRepository;
import com.citel.citelapp.repository.search.DoadorSearchRepository;
import com.citel.citelapp.service.DoadorService;
import com.citel.citelapp.service.dto.DoadorDTO;
import com.citel.citelapp.service.mapper.DoadorMapper;
import com.citel.citelapp.service.dto.DoadorCriteria;
import com.citel.citelapp.service.DoadorQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DoadorResource} REST controller.
 */
@SpringBootTest(classes = CitelApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class DoadorResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_CPF = "AAAAAAAAAA";
    private static final String UPDATED_CPF = "BBBBBBBBBB";

    private static final String DEFAULT_RG = "AAAAAAAAAA";
    private static final String UPDATED_RG = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_NASC = "AAAAAAAAAA";
    private static final String UPDATED_DATA_NASC = "BBBBBBBBBB";

    private static final String DEFAULT_SEXO = "AAAAAAAAAA";
    private static final String UPDATED_SEXO = "BBBBBBBBBB";

    private static final String DEFAULT_MAE = "AAAAAAAAAA";
    private static final String UPDATED_MAE = "BBBBBBBBBB";

    private static final String DEFAULT_PAI = "AAAAAAAAAA";
    private static final String UPDATED_PAI = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CEP = "AAAAAAAAAA";
    private static final String UPDATED_CEP = "BBBBBBBBBB";

    private static final String DEFAULT_ENDERECO = "AAAAAAAAAA";
    private static final String UPDATED_ENDERECO = "BBBBBBBBBB";

    private static final Long DEFAULT_NUMERO = 1L;
    private static final Long UPDATED_NUMERO = 2L;
    private static final Long SMALLER_NUMERO = 1L - 1L;

    private static final String DEFAULT_BAIRRO = "AAAAAAAAAA";
    private static final String UPDATED_BAIRRO = "BBBBBBBBBB";

    private static final String DEFAULT_CIDADE = "AAAAAAAAAA";
    private static final String UPDATED_CIDADE = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONE_FIXO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONE_FIXO = "BBBBBBBBBB";

    private static final String DEFAULT_CELULAR = "AAAAAAAAAA";
    private static final String UPDATED_CELULAR = "BBBBBBBBBB";

    private static final Double DEFAULT_ALTURA = 1D;
    private static final Double UPDATED_ALTURA = 2D;
    private static final Double SMALLER_ALTURA = 1D - 1D;

    private static final Long DEFAULT_PESO = 1L;
    private static final Long UPDATED_PESO = 2L;
    private static final Long SMALLER_PESO = 1L - 1L;

    private static final String DEFAULT_TIPO_SANGUINEO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_SANGUINEO = "BBBBBBBBBB";

    @Autowired
    private DoadorRepository doadorRepository;

    @Autowired
    private DoadorMapper doadorMapper;

    @Autowired
    private DoadorService doadorService;

    /**
     * This repository is mocked in the com.citel.citelapp.repository.search test package.
     *
     * @see com.citel.citelapp.repository.search.DoadorSearchRepositoryMockConfiguration
     */
    @Autowired
    private DoadorSearchRepository mockDoadorSearchRepository;

    @Autowired
    private DoadorQueryService doadorQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDoadorMockMvc;

    private Doador doador;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Doador createEntity(EntityManager em) {
        Doador doador = new Doador()
            .nome(DEFAULT_NOME)
            .cpf(DEFAULT_CPF)
            .rg(DEFAULT_RG)
            .dataNasc(DEFAULT_DATA_NASC)
            .sexo(DEFAULT_SEXO)
            .mae(DEFAULT_MAE)
            .pai(DEFAULT_PAI)
            .email(DEFAULT_EMAIL)
            .cep(DEFAULT_CEP)
            .endereco(DEFAULT_ENDERECO)
            .numero(DEFAULT_NUMERO)
            .bairro(DEFAULT_BAIRRO)
            .cidade(DEFAULT_CIDADE)
            .estado(DEFAULT_ESTADO)
            .telefoneFixo(DEFAULT_TELEFONE_FIXO)
            .celular(DEFAULT_CELULAR)
            .altura(DEFAULT_ALTURA)
            .peso(DEFAULT_PESO)
            .tipoSanguineo(DEFAULT_TIPO_SANGUINEO);
        return doador;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Doador createUpdatedEntity(EntityManager em) {
        Doador doador = new Doador()
            .nome(UPDATED_NOME)
            .cpf(UPDATED_CPF)
            .rg(UPDATED_RG)
            .dataNasc(UPDATED_DATA_NASC)
            .sexo(UPDATED_SEXO)
            .mae(UPDATED_MAE)
            .pai(UPDATED_PAI)
            .email(UPDATED_EMAIL)
            .cep(UPDATED_CEP)
            .endereco(UPDATED_ENDERECO)
            .numero(UPDATED_NUMERO)
            .bairro(UPDATED_BAIRRO)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .telefoneFixo(UPDATED_TELEFONE_FIXO)
            .celular(UPDATED_CELULAR)
            .altura(UPDATED_ALTURA)
            .peso(UPDATED_PESO)
            .tipoSanguineo(UPDATED_TIPO_SANGUINEO);
        return doador;
    }

    @BeforeEach
    public void initTest() {
        doador = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllDoadors() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList
        restDoadorMockMvc.perform(get("/api/doadors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(doador.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].cpf").value(hasItem(DEFAULT_CPF)))
            .andExpect(jsonPath("$.[*].rg").value(hasItem(DEFAULT_RG)))
            .andExpect(jsonPath("$.[*].dataNasc").value(hasItem(DEFAULT_DATA_NASC)))
            .andExpect(jsonPath("$.[*].sexo").value(hasItem(DEFAULT_SEXO)))
            .andExpect(jsonPath("$.[*].mae").value(hasItem(DEFAULT_MAE)))
            .andExpect(jsonPath("$.[*].pai").value(hasItem(DEFAULT_PAI)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP)))
            .andExpect(jsonPath("$.[*].endereco").value(hasItem(DEFAULT_ENDERECO)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.intValue())))
            .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].telefoneFixo").value(hasItem(DEFAULT_TELEFONE_FIXO)))
            .andExpect(jsonPath("$.[*].celular").value(hasItem(DEFAULT_CELULAR)))
            .andExpect(jsonPath("$.[*].altura").value(hasItem(DEFAULT_ALTURA.doubleValue())))
            .andExpect(jsonPath("$.[*].peso").value(hasItem(DEFAULT_PESO.intValue())))
            .andExpect(jsonPath("$.[*].tipoSanguineo").value(hasItem(DEFAULT_TIPO_SANGUINEO)));
    }
    
    @Test
    @Transactional
    public void getDoador() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get the doador
        restDoadorMockMvc.perform(get("/api/doadors/{id}", doador.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(doador.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.cpf").value(DEFAULT_CPF))
            .andExpect(jsonPath("$.rg").value(DEFAULT_RG))
            .andExpect(jsonPath("$.dataNasc").value(DEFAULT_DATA_NASC))
            .andExpect(jsonPath("$.sexo").value(DEFAULT_SEXO))
            .andExpect(jsonPath("$.mae").value(DEFAULT_MAE))
            .andExpect(jsonPath("$.pai").value(DEFAULT_PAI))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.cep").value(DEFAULT_CEP))
            .andExpect(jsonPath("$.endereco").value(DEFAULT_ENDERECO))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.intValue()))
            .andExpect(jsonPath("$.bairro").value(DEFAULT_BAIRRO))
            .andExpect(jsonPath("$.cidade").value(DEFAULT_CIDADE))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO))
            .andExpect(jsonPath("$.telefoneFixo").value(DEFAULT_TELEFONE_FIXO))
            .andExpect(jsonPath("$.celular").value(DEFAULT_CELULAR))
            .andExpect(jsonPath("$.altura").value(DEFAULT_ALTURA.doubleValue()))
            .andExpect(jsonPath("$.peso").value(DEFAULT_PESO.intValue()))
            .andExpect(jsonPath("$.tipoSanguineo").value(DEFAULT_TIPO_SANGUINEO));
    }


    @Test
    @Transactional
    public void getDoadorsByIdFiltering() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        Long id = doador.getId();

        defaultDoadorShouldBeFound("id.equals=" + id);
        defaultDoadorShouldNotBeFound("id.notEquals=" + id);

        defaultDoadorShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDoadorShouldNotBeFound("id.greaterThan=" + id);

        defaultDoadorShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDoadorShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDoadorsByNomeIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where nome equals to DEFAULT_NOME
        defaultDoadorShouldBeFound("nome.equals=" + DEFAULT_NOME);

        // Get all the doadorList where nome equals to UPDATED_NOME
        defaultDoadorShouldNotBeFound("nome.equals=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllDoadorsByNomeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where nome not equals to DEFAULT_NOME
        defaultDoadorShouldNotBeFound("nome.notEquals=" + DEFAULT_NOME);

        // Get all the doadorList where nome not equals to UPDATED_NOME
        defaultDoadorShouldBeFound("nome.notEquals=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllDoadorsByNomeIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where nome in DEFAULT_NOME or UPDATED_NOME
        defaultDoadorShouldBeFound("nome.in=" + DEFAULT_NOME + "," + UPDATED_NOME);

        // Get all the doadorList where nome equals to UPDATED_NOME
        defaultDoadorShouldNotBeFound("nome.in=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllDoadorsByNomeIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where nome is not null
        defaultDoadorShouldBeFound("nome.specified=true");

        // Get all the doadorList where nome is null
        defaultDoadorShouldNotBeFound("nome.specified=false");
    }
                @Test
    @Transactional
    public void getAllDoadorsByNomeContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where nome contains DEFAULT_NOME
        defaultDoadorShouldBeFound("nome.contains=" + DEFAULT_NOME);

        // Get all the doadorList where nome contains UPDATED_NOME
        defaultDoadorShouldNotBeFound("nome.contains=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllDoadorsByNomeNotContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where nome does not contain DEFAULT_NOME
        defaultDoadorShouldNotBeFound("nome.doesNotContain=" + DEFAULT_NOME);

        // Get all the doadorList where nome does not contain UPDATED_NOME
        defaultDoadorShouldBeFound("nome.doesNotContain=" + UPDATED_NOME);
    }


    @Test
    @Transactional
    public void getAllDoadorsByCpfIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where cpf equals to DEFAULT_CPF
        defaultDoadorShouldBeFound("cpf.equals=" + DEFAULT_CPF);

        // Get all the doadorList where cpf equals to UPDATED_CPF
        defaultDoadorShouldNotBeFound("cpf.equals=" + UPDATED_CPF);
    }

    @Test
    @Transactional
    public void getAllDoadorsByCpfIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where cpf not equals to DEFAULT_CPF
        defaultDoadorShouldNotBeFound("cpf.notEquals=" + DEFAULT_CPF);

        // Get all the doadorList where cpf not equals to UPDATED_CPF
        defaultDoadorShouldBeFound("cpf.notEquals=" + UPDATED_CPF);
    }

    @Test
    @Transactional
    public void getAllDoadorsByCpfIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where cpf in DEFAULT_CPF or UPDATED_CPF
        defaultDoadorShouldBeFound("cpf.in=" + DEFAULT_CPF + "," + UPDATED_CPF);

        // Get all the doadorList where cpf equals to UPDATED_CPF
        defaultDoadorShouldNotBeFound("cpf.in=" + UPDATED_CPF);
    }

    @Test
    @Transactional
    public void getAllDoadorsByCpfIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where cpf is not null
        defaultDoadorShouldBeFound("cpf.specified=true");

        // Get all the doadorList where cpf is null
        defaultDoadorShouldNotBeFound("cpf.specified=false");
    }
                @Test
    @Transactional
    public void getAllDoadorsByCpfContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where cpf contains DEFAULT_CPF
        defaultDoadorShouldBeFound("cpf.contains=" + DEFAULT_CPF);

        // Get all the doadorList where cpf contains UPDATED_CPF
        defaultDoadorShouldNotBeFound("cpf.contains=" + UPDATED_CPF);
    }

    @Test
    @Transactional
    public void getAllDoadorsByCpfNotContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where cpf does not contain DEFAULT_CPF
        defaultDoadorShouldNotBeFound("cpf.doesNotContain=" + DEFAULT_CPF);

        // Get all the doadorList where cpf does not contain UPDATED_CPF
        defaultDoadorShouldBeFound("cpf.doesNotContain=" + UPDATED_CPF);
    }


    @Test
    @Transactional
    public void getAllDoadorsByRgIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where rg equals to DEFAULT_RG
        defaultDoadorShouldBeFound("rg.equals=" + DEFAULT_RG);

        // Get all the doadorList where rg equals to UPDATED_RG
        defaultDoadorShouldNotBeFound("rg.equals=" + UPDATED_RG);
    }

    @Test
    @Transactional
    public void getAllDoadorsByRgIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where rg not equals to DEFAULT_RG
        defaultDoadorShouldNotBeFound("rg.notEquals=" + DEFAULT_RG);

        // Get all the doadorList where rg not equals to UPDATED_RG
        defaultDoadorShouldBeFound("rg.notEquals=" + UPDATED_RG);
    }

    @Test
    @Transactional
    public void getAllDoadorsByRgIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where rg in DEFAULT_RG or UPDATED_RG
        defaultDoadorShouldBeFound("rg.in=" + DEFAULT_RG + "," + UPDATED_RG);

        // Get all the doadorList where rg equals to UPDATED_RG
        defaultDoadorShouldNotBeFound("rg.in=" + UPDATED_RG);
    }

    @Test
    @Transactional
    public void getAllDoadorsByRgIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where rg is not null
        defaultDoadorShouldBeFound("rg.specified=true");

        // Get all the doadorList where rg is null
        defaultDoadorShouldNotBeFound("rg.specified=false");
    }
                @Test
    @Transactional
    public void getAllDoadorsByRgContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where rg contains DEFAULT_RG
        defaultDoadorShouldBeFound("rg.contains=" + DEFAULT_RG);

        // Get all the doadorList where rg contains UPDATED_RG
        defaultDoadorShouldNotBeFound("rg.contains=" + UPDATED_RG);
    }

    @Test
    @Transactional
    public void getAllDoadorsByRgNotContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where rg does not contain DEFAULT_RG
        defaultDoadorShouldNotBeFound("rg.doesNotContain=" + DEFAULT_RG);

        // Get all the doadorList where rg does not contain UPDATED_RG
        defaultDoadorShouldBeFound("rg.doesNotContain=" + UPDATED_RG);
    }


    @Test
    @Transactional
    public void getAllDoadorsByDataNascIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where dataNasc equals to DEFAULT_DATA_NASC
        defaultDoadorShouldBeFound("dataNasc.equals=" + DEFAULT_DATA_NASC);

        // Get all the doadorList where dataNasc equals to UPDATED_DATA_NASC
        defaultDoadorShouldNotBeFound("dataNasc.equals=" + UPDATED_DATA_NASC);
    }

    @Test
    @Transactional
    public void getAllDoadorsByDataNascIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where dataNasc not equals to DEFAULT_DATA_NASC
        defaultDoadorShouldNotBeFound("dataNasc.notEquals=" + DEFAULT_DATA_NASC);

        // Get all the doadorList where dataNasc not equals to UPDATED_DATA_NASC
        defaultDoadorShouldBeFound("dataNasc.notEquals=" + UPDATED_DATA_NASC);
    }

    @Test
    @Transactional
    public void getAllDoadorsByDataNascIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where dataNasc in DEFAULT_DATA_NASC or UPDATED_DATA_NASC
        defaultDoadorShouldBeFound("dataNasc.in=" + DEFAULT_DATA_NASC + "," + UPDATED_DATA_NASC);

        // Get all the doadorList where dataNasc equals to UPDATED_DATA_NASC
        defaultDoadorShouldNotBeFound("dataNasc.in=" + UPDATED_DATA_NASC);
    }

    @Test
    @Transactional
    public void getAllDoadorsByDataNascIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where dataNasc is not null
        defaultDoadorShouldBeFound("dataNasc.specified=true");

        // Get all the doadorList where dataNasc is null
        defaultDoadorShouldNotBeFound("dataNasc.specified=false");
    }
                @Test
    @Transactional
    public void getAllDoadorsByDataNascContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where dataNasc contains DEFAULT_DATA_NASC
        defaultDoadorShouldBeFound("dataNasc.contains=" + DEFAULT_DATA_NASC);

        // Get all the doadorList where dataNasc contains UPDATED_DATA_NASC
        defaultDoadorShouldNotBeFound("dataNasc.contains=" + UPDATED_DATA_NASC);
    }

    @Test
    @Transactional
    public void getAllDoadorsByDataNascNotContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where dataNasc does not contain DEFAULT_DATA_NASC
        defaultDoadorShouldNotBeFound("dataNasc.doesNotContain=" + DEFAULT_DATA_NASC);

        // Get all the doadorList where dataNasc does not contain UPDATED_DATA_NASC
        defaultDoadorShouldBeFound("dataNasc.doesNotContain=" + UPDATED_DATA_NASC);
    }


    @Test
    @Transactional
    public void getAllDoadorsBySexoIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where sexo equals to DEFAULT_SEXO
        defaultDoadorShouldBeFound("sexo.equals=" + DEFAULT_SEXO);

        // Get all the doadorList where sexo equals to UPDATED_SEXO
        defaultDoadorShouldNotBeFound("sexo.equals=" + UPDATED_SEXO);
    }

    @Test
    @Transactional
    public void getAllDoadorsBySexoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where sexo not equals to DEFAULT_SEXO
        defaultDoadorShouldNotBeFound("sexo.notEquals=" + DEFAULT_SEXO);

        // Get all the doadorList where sexo not equals to UPDATED_SEXO
        defaultDoadorShouldBeFound("sexo.notEquals=" + UPDATED_SEXO);
    }

    @Test
    @Transactional
    public void getAllDoadorsBySexoIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where sexo in DEFAULT_SEXO or UPDATED_SEXO
        defaultDoadorShouldBeFound("sexo.in=" + DEFAULT_SEXO + "," + UPDATED_SEXO);

        // Get all the doadorList where sexo equals to UPDATED_SEXO
        defaultDoadorShouldNotBeFound("sexo.in=" + UPDATED_SEXO);
    }

    @Test
    @Transactional
    public void getAllDoadorsBySexoIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where sexo is not null
        defaultDoadorShouldBeFound("sexo.specified=true");

        // Get all the doadorList where sexo is null
        defaultDoadorShouldNotBeFound("sexo.specified=false");
    }
                @Test
    @Transactional
    public void getAllDoadorsBySexoContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where sexo contains DEFAULT_SEXO
        defaultDoadorShouldBeFound("sexo.contains=" + DEFAULT_SEXO);

        // Get all the doadorList where sexo contains UPDATED_SEXO
        defaultDoadorShouldNotBeFound("sexo.contains=" + UPDATED_SEXO);
    }

    @Test
    @Transactional
    public void getAllDoadorsBySexoNotContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where sexo does not contain DEFAULT_SEXO
        defaultDoadorShouldNotBeFound("sexo.doesNotContain=" + DEFAULT_SEXO);

        // Get all the doadorList where sexo does not contain UPDATED_SEXO
        defaultDoadorShouldBeFound("sexo.doesNotContain=" + UPDATED_SEXO);
    }


    @Test
    @Transactional
    public void getAllDoadorsByMaeIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where mae equals to DEFAULT_MAE
        defaultDoadorShouldBeFound("mae.equals=" + DEFAULT_MAE);

        // Get all the doadorList where mae equals to UPDATED_MAE
        defaultDoadorShouldNotBeFound("mae.equals=" + UPDATED_MAE);
    }

    @Test
    @Transactional
    public void getAllDoadorsByMaeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where mae not equals to DEFAULT_MAE
        defaultDoadorShouldNotBeFound("mae.notEquals=" + DEFAULT_MAE);

        // Get all the doadorList where mae not equals to UPDATED_MAE
        defaultDoadorShouldBeFound("mae.notEquals=" + UPDATED_MAE);
    }

    @Test
    @Transactional
    public void getAllDoadorsByMaeIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where mae in DEFAULT_MAE or UPDATED_MAE
        defaultDoadorShouldBeFound("mae.in=" + DEFAULT_MAE + "," + UPDATED_MAE);

        // Get all the doadorList where mae equals to UPDATED_MAE
        defaultDoadorShouldNotBeFound("mae.in=" + UPDATED_MAE);
    }

    @Test
    @Transactional
    public void getAllDoadorsByMaeIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where mae is not null
        defaultDoadorShouldBeFound("mae.specified=true");

        // Get all the doadorList where mae is null
        defaultDoadorShouldNotBeFound("mae.specified=false");
    }
                @Test
    @Transactional
    public void getAllDoadorsByMaeContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where mae contains DEFAULT_MAE
        defaultDoadorShouldBeFound("mae.contains=" + DEFAULT_MAE);

        // Get all the doadorList where mae contains UPDATED_MAE
        defaultDoadorShouldNotBeFound("mae.contains=" + UPDATED_MAE);
    }

    @Test
    @Transactional
    public void getAllDoadorsByMaeNotContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where mae does not contain DEFAULT_MAE
        defaultDoadorShouldNotBeFound("mae.doesNotContain=" + DEFAULT_MAE);

        // Get all the doadorList where mae does not contain UPDATED_MAE
        defaultDoadorShouldBeFound("mae.doesNotContain=" + UPDATED_MAE);
    }


    @Test
    @Transactional
    public void getAllDoadorsByPaiIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where pai equals to DEFAULT_PAI
        defaultDoadorShouldBeFound("pai.equals=" + DEFAULT_PAI);

        // Get all the doadorList where pai equals to UPDATED_PAI
        defaultDoadorShouldNotBeFound("pai.equals=" + UPDATED_PAI);
    }

    @Test
    @Transactional
    public void getAllDoadorsByPaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where pai not equals to DEFAULT_PAI
        defaultDoadorShouldNotBeFound("pai.notEquals=" + DEFAULT_PAI);

        // Get all the doadorList where pai not equals to UPDATED_PAI
        defaultDoadorShouldBeFound("pai.notEquals=" + UPDATED_PAI);
    }

    @Test
    @Transactional
    public void getAllDoadorsByPaiIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where pai in DEFAULT_PAI or UPDATED_PAI
        defaultDoadorShouldBeFound("pai.in=" + DEFAULT_PAI + "," + UPDATED_PAI);

        // Get all the doadorList where pai equals to UPDATED_PAI
        defaultDoadorShouldNotBeFound("pai.in=" + UPDATED_PAI);
    }

    @Test
    @Transactional
    public void getAllDoadorsByPaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where pai is not null
        defaultDoadorShouldBeFound("pai.specified=true");

        // Get all the doadorList where pai is null
        defaultDoadorShouldNotBeFound("pai.specified=false");
    }
                @Test
    @Transactional
    public void getAllDoadorsByPaiContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where pai contains DEFAULT_PAI
        defaultDoadorShouldBeFound("pai.contains=" + DEFAULT_PAI);

        // Get all the doadorList where pai contains UPDATED_PAI
        defaultDoadorShouldNotBeFound("pai.contains=" + UPDATED_PAI);
    }

    @Test
    @Transactional
    public void getAllDoadorsByPaiNotContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where pai does not contain DEFAULT_PAI
        defaultDoadorShouldNotBeFound("pai.doesNotContain=" + DEFAULT_PAI);

        // Get all the doadorList where pai does not contain UPDATED_PAI
        defaultDoadorShouldBeFound("pai.doesNotContain=" + UPDATED_PAI);
    }


    @Test
    @Transactional
    public void getAllDoadorsByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where email equals to DEFAULT_EMAIL
        defaultDoadorShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the doadorList where email equals to UPDATED_EMAIL
        defaultDoadorShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllDoadorsByEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where email not equals to DEFAULT_EMAIL
        defaultDoadorShouldNotBeFound("email.notEquals=" + DEFAULT_EMAIL);

        // Get all the doadorList where email not equals to UPDATED_EMAIL
        defaultDoadorShouldBeFound("email.notEquals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllDoadorsByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultDoadorShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the doadorList where email equals to UPDATED_EMAIL
        defaultDoadorShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllDoadorsByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where email is not null
        defaultDoadorShouldBeFound("email.specified=true");

        // Get all the doadorList where email is null
        defaultDoadorShouldNotBeFound("email.specified=false");
    }
                @Test
    @Transactional
    public void getAllDoadorsByEmailContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where email contains DEFAULT_EMAIL
        defaultDoadorShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the doadorList where email contains UPDATED_EMAIL
        defaultDoadorShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllDoadorsByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where email does not contain DEFAULT_EMAIL
        defaultDoadorShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the doadorList where email does not contain UPDATED_EMAIL
        defaultDoadorShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }


    @Test
    @Transactional
    public void getAllDoadorsByCepIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where cep equals to DEFAULT_CEP
        defaultDoadorShouldBeFound("cep.equals=" + DEFAULT_CEP);

        // Get all the doadorList where cep equals to UPDATED_CEP
        defaultDoadorShouldNotBeFound("cep.equals=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllDoadorsByCepIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where cep not equals to DEFAULT_CEP
        defaultDoadorShouldNotBeFound("cep.notEquals=" + DEFAULT_CEP);

        // Get all the doadorList where cep not equals to UPDATED_CEP
        defaultDoadorShouldBeFound("cep.notEquals=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllDoadorsByCepIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where cep in DEFAULT_CEP or UPDATED_CEP
        defaultDoadorShouldBeFound("cep.in=" + DEFAULT_CEP + "," + UPDATED_CEP);

        // Get all the doadorList where cep equals to UPDATED_CEP
        defaultDoadorShouldNotBeFound("cep.in=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllDoadorsByCepIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where cep is not null
        defaultDoadorShouldBeFound("cep.specified=true");

        // Get all the doadorList where cep is null
        defaultDoadorShouldNotBeFound("cep.specified=false");
    }
                @Test
    @Transactional
    public void getAllDoadorsByCepContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where cep contains DEFAULT_CEP
        defaultDoadorShouldBeFound("cep.contains=" + DEFAULT_CEP);

        // Get all the doadorList where cep contains UPDATED_CEP
        defaultDoadorShouldNotBeFound("cep.contains=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllDoadorsByCepNotContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where cep does not contain DEFAULT_CEP
        defaultDoadorShouldNotBeFound("cep.doesNotContain=" + DEFAULT_CEP);

        // Get all the doadorList where cep does not contain UPDATED_CEP
        defaultDoadorShouldBeFound("cep.doesNotContain=" + UPDATED_CEP);
    }


    @Test
    @Transactional
    public void getAllDoadorsByEnderecoIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where endereco equals to DEFAULT_ENDERECO
        defaultDoadorShouldBeFound("endereco.equals=" + DEFAULT_ENDERECO);

        // Get all the doadorList where endereco equals to UPDATED_ENDERECO
        defaultDoadorShouldNotBeFound("endereco.equals=" + UPDATED_ENDERECO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByEnderecoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where endereco not equals to DEFAULT_ENDERECO
        defaultDoadorShouldNotBeFound("endereco.notEquals=" + DEFAULT_ENDERECO);

        // Get all the doadorList where endereco not equals to UPDATED_ENDERECO
        defaultDoadorShouldBeFound("endereco.notEquals=" + UPDATED_ENDERECO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByEnderecoIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where endereco in DEFAULT_ENDERECO or UPDATED_ENDERECO
        defaultDoadorShouldBeFound("endereco.in=" + DEFAULT_ENDERECO + "," + UPDATED_ENDERECO);

        // Get all the doadorList where endereco equals to UPDATED_ENDERECO
        defaultDoadorShouldNotBeFound("endereco.in=" + UPDATED_ENDERECO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByEnderecoIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where endereco is not null
        defaultDoadorShouldBeFound("endereco.specified=true");

        // Get all the doadorList where endereco is null
        defaultDoadorShouldNotBeFound("endereco.specified=false");
    }
                @Test
    @Transactional
    public void getAllDoadorsByEnderecoContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where endereco contains DEFAULT_ENDERECO
        defaultDoadorShouldBeFound("endereco.contains=" + DEFAULT_ENDERECO);

        // Get all the doadorList where endereco contains UPDATED_ENDERECO
        defaultDoadorShouldNotBeFound("endereco.contains=" + UPDATED_ENDERECO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByEnderecoNotContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where endereco does not contain DEFAULT_ENDERECO
        defaultDoadorShouldNotBeFound("endereco.doesNotContain=" + DEFAULT_ENDERECO);

        // Get all the doadorList where endereco does not contain UPDATED_ENDERECO
        defaultDoadorShouldBeFound("endereco.doesNotContain=" + UPDATED_ENDERECO);
    }


    @Test
    @Transactional
    public void getAllDoadorsByNumeroIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where numero equals to DEFAULT_NUMERO
        defaultDoadorShouldBeFound("numero.equals=" + DEFAULT_NUMERO);

        // Get all the doadorList where numero equals to UPDATED_NUMERO
        defaultDoadorShouldNotBeFound("numero.equals=" + UPDATED_NUMERO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByNumeroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where numero not equals to DEFAULT_NUMERO
        defaultDoadorShouldNotBeFound("numero.notEquals=" + DEFAULT_NUMERO);

        // Get all the doadorList where numero not equals to UPDATED_NUMERO
        defaultDoadorShouldBeFound("numero.notEquals=" + UPDATED_NUMERO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByNumeroIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where numero in DEFAULT_NUMERO or UPDATED_NUMERO
        defaultDoadorShouldBeFound("numero.in=" + DEFAULT_NUMERO + "," + UPDATED_NUMERO);

        // Get all the doadorList where numero equals to UPDATED_NUMERO
        defaultDoadorShouldNotBeFound("numero.in=" + UPDATED_NUMERO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByNumeroIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where numero is not null
        defaultDoadorShouldBeFound("numero.specified=true");

        // Get all the doadorList where numero is null
        defaultDoadorShouldNotBeFound("numero.specified=false");
    }

    @Test
    @Transactional
    public void getAllDoadorsByNumeroIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where numero is greater than or equal to DEFAULT_NUMERO
        defaultDoadorShouldBeFound("numero.greaterThanOrEqual=" + DEFAULT_NUMERO);

        // Get all the doadorList where numero is greater than or equal to UPDATED_NUMERO
        defaultDoadorShouldNotBeFound("numero.greaterThanOrEqual=" + UPDATED_NUMERO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByNumeroIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where numero is less than or equal to DEFAULT_NUMERO
        defaultDoadorShouldBeFound("numero.lessThanOrEqual=" + DEFAULT_NUMERO);

        // Get all the doadorList where numero is less than or equal to SMALLER_NUMERO
        defaultDoadorShouldNotBeFound("numero.lessThanOrEqual=" + SMALLER_NUMERO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByNumeroIsLessThanSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where numero is less than DEFAULT_NUMERO
        defaultDoadorShouldNotBeFound("numero.lessThan=" + DEFAULT_NUMERO);

        // Get all the doadorList where numero is less than UPDATED_NUMERO
        defaultDoadorShouldBeFound("numero.lessThan=" + UPDATED_NUMERO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByNumeroIsGreaterThanSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where numero is greater than DEFAULT_NUMERO
        defaultDoadorShouldNotBeFound("numero.greaterThan=" + DEFAULT_NUMERO);

        // Get all the doadorList where numero is greater than SMALLER_NUMERO
        defaultDoadorShouldBeFound("numero.greaterThan=" + SMALLER_NUMERO);
    }


    @Test
    @Transactional
    public void getAllDoadorsByBairroIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where bairro equals to DEFAULT_BAIRRO
        defaultDoadorShouldBeFound("bairro.equals=" + DEFAULT_BAIRRO);

        // Get all the doadorList where bairro equals to UPDATED_BAIRRO
        defaultDoadorShouldNotBeFound("bairro.equals=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByBairroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where bairro not equals to DEFAULT_BAIRRO
        defaultDoadorShouldNotBeFound("bairro.notEquals=" + DEFAULT_BAIRRO);

        // Get all the doadorList where bairro not equals to UPDATED_BAIRRO
        defaultDoadorShouldBeFound("bairro.notEquals=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByBairroIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where bairro in DEFAULT_BAIRRO or UPDATED_BAIRRO
        defaultDoadorShouldBeFound("bairro.in=" + DEFAULT_BAIRRO + "," + UPDATED_BAIRRO);

        // Get all the doadorList where bairro equals to UPDATED_BAIRRO
        defaultDoadorShouldNotBeFound("bairro.in=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByBairroIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where bairro is not null
        defaultDoadorShouldBeFound("bairro.specified=true");

        // Get all the doadorList where bairro is null
        defaultDoadorShouldNotBeFound("bairro.specified=false");
    }
                @Test
    @Transactional
    public void getAllDoadorsByBairroContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where bairro contains DEFAULT_BAIRRO
        defaultDoadorShouldBeFound("bairro.contains=" + DEFAULT_BAIRRO);

        // Get all the doadorList where bairro contains UPDATED_BAIRRO
        defaultDoadorShouldNotBeFound("bairro.contains=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByBairroNotContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where bairro does not contain DEFAULT_BAIRRO
        defaultDoadorShouldNotBeFound("bairro.doesNotContain=" + DEFAULT_BAIRRO);

        // Get all the doadorList where bairro does not contain UPDATED_BAIRRO
        defaultDoadorShouldBeFound("bairro.doesNotContain=" + UPDATED_BAIRRO);
    }


    @Test
    @Transactional
    public void getAllDoadorsByCidadeIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where cidade equals to DEFAULT_CIDADE
        defaultDoadorShouldBeFound("cidade.equals=" + DEFAULT_CIDADE);

        // Get all the doadorList where cidade equals to UPDATED_CIDADE
        defaultDoadorShouldNotBeFound("cidade.equals=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllDoadorsByCidadeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where cidade not equals to DEFAULT_CIDADE
        defaultDoadorShouldNotBeFound("cidade.notEquals=" + DEFAULT_CIDADE);

        // Get all the doadorList where cidade not equals to UPDATED_CIDADE
        defaultDoadorShouldBeFound("cidade.notEquals=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllDoadorsByCidadeIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where cidade in DEFAULT_CIDADE or UPDATED_CIDADE
        defaultDoadorShouldBeFound("cidade.in=" + DEFAULT_CIDADE + "," + UPDATED_CIDADE);

        // Get all the doadorList where cidade equals to UPDATED_CIDADE
        defaultDoadorShouldNotBeFound("cidade.in=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllDoadorsByCidadeIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where cidade is not null
        defaultDoadorShouldBeFound("cidade.specified=true");

        // Get all the doadorList where cidade is null
        defaultDoadorShouldNotBeFound("cidade.specified=false");
    }
                @Test
    @Transactional
    public void getAllDoadorsByCidadeContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where cidade contains DEFAULT_CIDADE
        defaultDoadorShouldBeFound("cidade.contains=" + DEFAULT_CIDADE);

        // Get all the doadorList where cidade contains UPDATED_CIDADE
        defaultDoadorShouldNotBeFound("cidade.contains=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllDoadorsByCidadeNotContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where cidade does not contain DEFAULT_CIDADE
        defaultDoadorShouldNotBeFound("cidade.doesNotContain=" + DEFAULT_CIDADE);

        // Get all the doadorList where cidade does not contain UPDATED_CIDADE
        defaultDoadorShouldBeFound("cidade.doesNotContain=" + UPDATED_CIDADE);
    }


    @Test
    @Transactional
    public void getAllDoadorsByEstadoIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where estado equals to DEFAULT_ESTADO
        defaultDoadorShouldBeFound("estado.equals=" + DEFAULT_ESTADO);

        // Get all the doadorList where estado equals to UPDATED_ESTADO
        defaultDoadorShouldNotBeFound("estado.equals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByEstadoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where estado not equals to DEFAULT_ESTADO
        defaultDoadorShouldNotBeFound("estado.notEquals=" + DEFAULT_ESTADO);

        // Get all the doadorList where estado not equals to UPDATED_ESTADO
        defaultDoadorShouldBeFound("estado.notEquals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByEstadoIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where estado in DEFAULT_ESTADO or UPDATED_ESTADO
        defaultDoadorShouldBeFound("estado.in=" + DEFAULT_ESTADO + "," + UPDATED_ESTADO);

        // Get all the doadorList where estado equals to UPDATED_ESTADO
        defaultDoadorShouldNotBeFound("estado.in=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByEstadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where estado is not null
        defaultDoadorShouldBeFound("estado.specified=true");

        // Get all the doadorList where estado is null
        defaultDoadorShouldNotBeFound("estado.specified=false");
    }
                @Test
    @Transactional
    public void getAllDoadorsByEstadoContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where estado contains DEFAULT_ESTADO
        defaultDoadorShouldBeFound("estado.contains=" + DEFAULT_ESTADO);

        // Get all the doadorList where estado contains UPDATED_ESTADO
        defaultDoadorShouldNotBeFound("estado.contains=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByEstadoNotContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where estado does not contain DEFAULT_ESTADO
        defaultDoadorShouldNotBeFound("estado.doesNotContain=" + DEFAULT_ESTADO);

        // Get all the doadorList where estado does not contain UPDATED_ESTADO
        defaultDoadorShouldBeFound("estado.doesNotContain=" + UPDATED_ESTADO);
    }


    @Test
    @Transactional
    public void getAllDoadorsByTelefoneFixoIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where telefoneFixo equals to DEFAULT_TELEFONE_FIXO
        defaultDoadorShouldBeFound("telefoneFixo.equals=" + DEFAULT_TELEFONE_FIXO);

        // Get all the doadorList where telefoneFixo equals to UPDATED_TELEFONE_FIXO
        defaultDoadorShouldNotBeFound("telefoneFixo.equals=" + UPDATED_TELEFONE_FIXO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByTelefoneFixoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where telefoneFixo not equals to DEFAULT_TELEFONE_FIXO
        defaultDoadorShouldNotBeFound("telefoneFixo.notEquals=" + DEFAULT_TELEFONE_FIXO);

        // Get all the doadorList where telefoneFixo not equals to UPDATED_TELEFONE_FIXO
        defaultDoadorShouldBeFound("telefoneFixo.notEquals=" + UPDATED_TELEFONE_FIXO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByTelefoneFixoIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where telefoneFixo in DEFAULT_TELEFONE_FIXO or UPDATED_TELEFONE_FIXO
        defaultDoadorShouldBeFound("telefoneFixo.in=" + DEFAULT_TELEFONE_FIXO + "," + UPDATED_TELEFONE_FIXO);

        // Get all the doadorList where telefoneFixo equals to UPDATED_TELEFONE_FIXO
        defaultDoadorShouldNotBeFound("telefoneFixo.in=" + UPDATED_TELEFONE_FIXO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByTelefoneFixoIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where telefoneFixo is not null
        defaultDoadorShouldBeFound("telefoneFixo.specified=true");

        // Get all the doadorList where telefoneFixo is null
        defaultDoadorShouldNotBeFound("telefoneFixo.specified=false");
    }
                @Test
    @Transactional
    public void getAllDoadorsByTelefoneFixoContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where telefoneFixo contains DEFAULT_TELEFONE_FIXO
        defaultDoadorShouldBeFound("telefoneFixo.contains=" + DEFAULT_TELEFONE_FIXO);

        // Get all the doadorList where telefoneFixo contains UPDATED_TELEFONE_FIXO
        defaultDoadorShouldNotBeFound("telefoneFixo.contains=" + UPDATED_TELEFONE_FIXO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByTelefoneFixoNotContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where telefoneFixo does not contain DEFAULT_TELEFONE_FIXO
        defaultDoadorShouldNotBeFound("telefoneFixo.doesNotContain=" + DEFAULT_TELEFONE_FIXO);

        // Get all the doadorList where telefoneFixo does not contain UPDATED_TELEFONE_FIXO
        defaultDoadorShouldBeFound("telefoneFixo.doesNotContain=" + UPDATED_TELEFONE_FIXO);
    }


    @Test
    @Transactional
    public void getAllDoadorsByCelularIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where celular equals to DEFAULT_CELULAR
        defaultDoadorShouldBeFound("celular.equals=" + DEFAULT_CELULAR);

        // Get all the doadorList where celular equals to UPDATED_CELULAR
        defaultDoadorShouldNotBeFound("celular.equals=" + UPDATED_CELULAR);
    }

    @Test
    @Transactional
    public void getAllDoadorsByCelularIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where celular not equals to DEFAULT_CELULAR
        defaultDoadorShouldNotBeFound("celular.notEquals=" + DEFAULT_CELULAR);

        // Get all the doadorList where celular not equals to UPDATED_CELULAR
        defaultDoadorShouldBeFound("celular.notEquals=" + UPDATED_CELULAR);
    }

    @Test
    @Transactional
    public void getAllDoadorsByCelularIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where celular in DEFAULT_CELULAR or UPDATED_CELULAR
        defaultDoadorShouldBeFound("celular.in=" + DEFAULT_CELULAR + "," + UPDATED_CELULAR);

        // Get all the doadorList where celular equals to UPDATED_CELULAR
        defaultDoadorShouldNotBeFound("celular.in=" + UPDATED_CELULAR);
    }

    @Test
    @Transactional
    public void getAllDoadorsByCelularIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where celular is not null
        defaultDoadorShouldBeFound("celular.specified=true");

        // Get all the doadorList where celular is null
        defaultDoadorShouldNotBeFound("celular.specified=false");
    }
                @Test
    @Transactional
    public void getAllDoadorsByCelularContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where celular contains DEFAULT_CELULAR
        defaultDoadorShouldBeFound("celular.contains=" + DEFAULT_CELULAR);

        // Get all the doadorList where celular contains UPDATED_CELULAR
        defaultDoadorShouldNotBeFound("celular.contains=" + UPDATED_CELULAR);
    }

    @Test
    @Transactional
    public void getAllDoadorsByCelularNotContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where celular does not contain DEFAULT_CELULAR
        defaultDoadorShouldNotBeFound("celular.doesNotContain=" + DEFAULT_CELULAR);

        // Get all the doadorList where celular does not contain UPDATED_CELULAR
        defaultDoadorShouldBeFound("celular.doesNotContain=" + UPDATED_CELULAR);
    }


    @Test
    @Transactional
    public void getAllDoadorsByAlturaIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where altura equals to DEFAULT_ALTURA
        defaultDoadorShouldBeFound("altura.equals=" + DEFAULT_ALTURA);

        // Get all the doadorList where altura equals to UPDATED_ALTURA
        defaultDoadorShouldNotBeFound("altura.equals=" + UPDATED_ALTURA);
    }

    @Test
    @Transactional
    public void getAllDoadorsByAlturaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where altura not equals to DEFAULT_ALTURA
        defaultDoadorShouldNotBeFound("altura.notEquals=" + DEFAULT_ALTURA);

        // Get all the doadorList where altura not equals to UPDATED_ALTURA
        defaultDoadorShouldBeFound("altura.notEquals=" + UPDATED_ALTURA);
    }

    @Test
    @Transactional
    public void getAllDoadorsByAlturaIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where altura in DEFAULT_ALTURA or UPDATED_ALTURA
        defaultDoadorShouldBeFound("altura.in=" + DEFAULT_ALTURA + "," + UPDATED_ALTURA);

        // Get all the doadorList where altura equals to UPDATED_ALTURA
        defaultDoadorShouldNotBeFound("altura.in=" + UPDATED_ALTURA);
    }

    @Test
    @Transactional
    public void getAllDoadorsByAlturaIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where altura is not null
        defaultDoadorShouldBeFound("altura.specified=true");

        // Get all the doadorList where altura is null
        defaultDoadorShouldNotBeFound("altura.specified=false");
    }

    @Test
    @Transactional
    public void getAllDoadorsByAlturaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where altura is greater than or equal to DEFAULT_ALTURA
        defaultDoadorShouldBeFound("altura.greaterThanOrEqual=" + DEFAULT_ALTURA);

        // Get all the doadorList where altura is greater than or equal to UPDATED_ALTURA
        defaultDoadorShouldNotBeFound("altura.greaterThanOrEqual=" + UPDATED_ALTURA);
    }

    @Test
    @Transactional
    public void getAllDoadorsByAlturaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where altura is less than or equal to DEFAULT_ALTURA
        defaultDoadorShouldBeFound("altura.lessThanOrEqual=" + DEFAULT_ALTURA);

        // Get all the doadorList where altura is less than or equal to SMALLER_ALTURA
        defaultDoadorShouldNotBeFound("altura.lessThanOrEqual=" + SMALLER_ALTURA);
    }

    @Test
    @Transactional
    public void getAllDoadorsByAlturaIsLessThanSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where altura is less than DEFAULT_ALTURA
        defaultDoadorShouldNotBeFound("altura.lessThan=" + DEFAULT_ALTURA);

        // Get all the doadorList where altura is less than UPDATED_ALTURA
        defaultDoadorShouldBeFound("altura.lessThan=" + UPDATED_ALTURA);
    }

    @Test
    @Transactional
    public void getAllDoadorsByAlturaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where altura is greater than DEFAULT_ALTURA
        defaultDoadorShouldNotBeFound("altura.greaterThan=" + DEFAULT_ALTURA);

        // Get all the doadorList where altura is greater than SMALLER_ALTURA
        defaultDoadorShouldBeFound("altura.greaterThan=" + SMALLER_ALTURA);
    }


    @Test
    @Transactional
    public void getAllDoadorsByPesoIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where peso equals to DEFAULT_PESO
        defaultDoadorShouldBeFound("peso.equals=" + DEFAULT_PESO);

        // Get all the doadorList where peso equals to UPDATED_PESO
        defaultDoadorShouldNotBeFound("peso.equals=" + UPDATED_PESO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByPesoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where peso not equals to DEFAULT_PESO
        defaultDoadorShouldNotBeFound("peso.notEquals=" + DEFAULT_PESO);

        // Get all the doadorList where peso not equals to UPDATED_PESO
        defaultDoadorShouldBeFound("peso.notEquals=" + UPDATED_PESO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByPesoIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where peso in DEFAULT_PESO or UPDATED_PESO
        defaultDoadorShouldBeFound("peso.in=" + DEFAULT_PESO + "," + UPDATED_PESO);

        // Get all the doadorList where peso equals to UPDATED_PESO
        defaultDoadorShouldNotBeFound("peso.in=" + UPDATED_PESO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByPesoIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where peso is not null
        defaultDoadorShouldBeFound("peso.specified=true");

        // Get all the doadorList where peso is null
        defaultDoadorShouldNotBeFound("peso.specified=false");
    }

    @Test
    @Transactional
    public void getAllDoadorsByPesoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where peso is greater than or equal to DEFAULT_PESO
        defaultDoadorShouldBeFound("peso.greaterThanOrEqual=" + DEFAULT_PESO);

        // Get all the doadorList where peso is greater than or equal to UPDATED_PESO
        defaultDoadorShouldNotBeFound("peso.greaterThanOrEqual=" + UPDATED_PESO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByPesoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where peso is less than or equal to DEFAULT_PESO
        defaultDoadorShouldBeFound("peso.lessThanOrEqual=" + DEFAULT_PESO);

        // Get all the doadorList where peso is less than or equal to SMALLER_PESO
        defaultDoadorShouldNotBeFound("peso.lessThanOrEqual=" + SMALLER_PESO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByPesoIsLessThanSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where peso is less than DEFAULT_PESO
        defaultDoadorShouldNotBeFound("peso.lessThan=" + DEFAULT_PESO);

        // Get all the doadorList where peso is less than UPDATED_PESO
        defaultDoadorShouldBeFound("peso.lessThan=" + UPDATED_PESO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByPesoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where peso is greater than DEFAULT_PESO
        defaultDoadorShouldNotBeFound("peso.greaterThan=" + DEFAULT_PESO);

        // Get all the doadorList where peso is greater than SMALLER_PESO
        defaultDoadorShouldBeFound("peso.greaterThan=" + SMALLER_PESO);
    }


    @Test
    @Transactional
    public void getAllDoadorsByTipoSanguineoIsEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where tipoSanguineo equals to DEFAULT_TIPO_SANGUINEO
        defaultDoadorShouldBeFound("tipoSanguineo.equals=" + DEFAULT_TIPO_SANGUINEO);

        // Get all the doadorList where tipoSanguineo equals to UPDATED_TIPO_SANGUINEO
        defaultDoadorShouldNotBeFound("tipoSanguineo.equals=" + UPDATED_TIPO_SANGUINEO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByTipoSanguineoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where tipoSanguineo not equals to DEFAULT_TIPO_SANGUINEO
        defaultDoadorShouldNotBeFound("tipoSanguineo.notEquals=" + DEFAULT_TIPO_SANGUINEO);

        // Get all the doadorList where tipoSanguineo not equals to UPDATED_TIPO_SANGUINEO
        defaultDoadorShouldBeFound("tipoSanguineo.notEquals=" + UPDATED_TIPO_SANGUINEO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByTipoSanguineoIsInShouldWork() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where tipoSanguineo in DEFAULT_TIPO_SANGUINEO or UPDATED_TIPO_SANGUINEO
        defaultDoadorShouldBeFound("tipoSanguineo.in=" + DEFAULT_TIPO_SANGUINEO + "," + UPDATED_TIPO_SANGUINEO);

        // Get all the doadorList where tipoSanguineo equals to UPDATED_TIPO_SANGUINEO
        defaultDoadorShouldNotBeFound("tipoSanguineo.in=" + UPDATED_TIPO_SANGUINEO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByTipoSanguineoIsNullOrNotNull() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where tipoSanguineo is not null
        defaultDoadorShouldBeFound("tipoSanguineo.specified=true");

        // Get all the doadorList where tipoSanguineo is null
        defaultDoadorShouldNotBeFound("tipoSanguineo.specified=false");
    }
                @Test
    @Transactional
    public void getAllDoadorsByTipoSanguineoContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where tipoSanguineo contains DEFAULT_TIPO_SANGUINEO
        defaultDoadorShouldBeFound("tipoSanguineo.contains=" + DEFAULT_TIPO_SANGUINEO);

        // Get all the doadorList where tipoSanguineo contains UPDATED_TIPO_SANGUINEO
        defaultDoadorShouldNotBeFound("tipoSanguineo.contains=" + UPDATED_TIPO_SANGUINEO);
    }

    @Test
    @Transactional
    public void getAllDoadorsByTipoSanguineoNotContainsSomething() throws Exception {
        // Initialize the database
        doadorRepository.saveAndFlush(doador);

        // Get all the doadorList where tipoSanguineo does not contain DEFAULT_TIPO_SANGUINEO
        defaultDoadorShouldNotBeFound("tipoSanguineo.doesNotContain=" + DEFAULT_TIPO_SANGUINEO);

        // Get all the doadorList where tipoSanguineo does not contain UPDATED_TIPO_SANGUINEO
        defaultDoadorShouldBeFound("tipoSanguineo.doesNotContain=" + UPDATED_TIPO_SANGUINEO);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDoadorShouldBeFound(String filter) throws Exception {
        restDoadorMockMvc.perform(get("/api/doadors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(doador.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].cpf").value(hasItem(DEFAULT_CPF)))
            .andExpect(jsonPath("$.[*].rg").value(hasItem(DEFAULT_RG)))
            .andExpect(jsonPath("$.[*].dataNasc").value(hasItem(DEFAULT_DATA_NASC)))
            .andExpect(jsonPath("$.[*].sexo").value(hasItem(DEFAULT_SEXO)))
            .andExpect(jsonPath("$.[*].mae").value(hasItem(DEFAULT_MAE)))
            .andExpect(jsonPath("$.[*].pai").value(hasItem(DEFAULT_PAI)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP)))
            .andExpect(jsonPath("$.[*].endereco").value(hasItem(DEFAULT_ENDERECO)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.intValue())))
            .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].telefoneFixo").value(hasItem(DEFAULT_TELEFONE_FIXO)))
            .andExpect(jsonPath("$.[*].celular").value(hasItem(DEFAULT_CELULAR)))
            .andExpect(jsonPath("$.[*].altura").value(hasItem(DEFAULT_ALTURA.doubleValue())))
            .andExpect(jsonPath("$.[*].peso").value(hasItem(DEFAULT_PESO.intValue())))
            .andExpect(jsonPath("$.[*].tipoSanguineo").value(hasItem(DEFAULT_TIPO_SANGUINEO)));

        // Check, that the count call also returns 1
        restDoadorMockMvc.perform(get("/api/doadors/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDoadorShouldNotBeFound(String filter) throws Exception {
        restDoadorMockMvc.perform(get("/api/doadors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDoadorMockMvc.perform(get("/api/doadors/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingDoador() throws Exception {
        // Get the doador
        restDoadorMockMvc.perform(get("/api/doadors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void searchDoador() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        doadorRepository.saveAndFlush(doador);
        when(mockDoadorSearchRepository.search(queryStringQuery("id:" + doador.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(doador), PageRequest.of(0, 1), 1));

        // Search the doador
        restDoadorMockMvc.perform(get("/api/_search/doadors?query=id:" + doador.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(doador.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].cpf").value(hasItem(DEFAULT_CPF)))
            .andExpect(jsonPath("$.[*].rg").value(hasItem(DEFAULT_RG)))
            .andExpect(jsonPath("$.[*].dataNasc").value(hasItem(DEFAULT_DATA_NASC)))
            .andExpect(jsonPath("$.[*].sexo").value(hasItem(DEFAULT_SEXO)))
            .andExpect(jsonPath("$.[*].mae").value(hasItem(DEFAULT_MAE)))
            .andExpect(jsonPath("$.[*].pai").value(hasItem(DEFAULT_PAI)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP)))
            .andExpect(jsonPath("$.[*].endereco").value(hasItem(DEFAULT_ENDERECO)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.intValue())))
            .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].telefoneFixo").value(hasItem(DEFAULT_TELEFONE_FIXO)))
            .andExpect(jsonPath("$.[*].celular").value(hasItem(DEFAULT_CELULAR)))
            .andExpect(jsonPath("$.[*].altura").value(hasItem(DEFAULT_ALTURA.doubleValue())))
            .andExpect(jsonPath("$.[*].peso").value(hasItem(DEFAULT_PESO.intValue())))
            .andExpect(jsonPath("$.[*].tipoSanguineo").value(hasItem(DEFAULT_TIPO_SANGUINEO)));
    }
}
