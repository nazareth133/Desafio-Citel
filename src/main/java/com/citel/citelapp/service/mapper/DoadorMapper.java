package com.citel.citelapp.service.mapper;


import com.citel.citelapp.domain.*;
import com.citel.citelapp.service.dto.DoadorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Doador} and its DTO {@link DoadorDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DoadorMapper extends EntityMapper<DoadorDTO, Doador> {



    default Doador fromId(Long id) {
        if (id == null) {
            return null;
        }
        Doador doador = new Doador();
        doador.setId(id);
        return doador;
    }
}
