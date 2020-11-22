package com.citel.citelapp.repository;

import com.citel.citelapp.domain.Doador;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Doador entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DoadorRepository extends JpaRepository<Doador, Long>, JpaSpecificationExecutor<Doador> {
}
