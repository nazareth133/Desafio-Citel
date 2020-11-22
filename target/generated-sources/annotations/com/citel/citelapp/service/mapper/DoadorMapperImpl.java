package com.citel.citelapp.service.mapper;

import com.citel.citelapp.domain.Doador;
import com.citel.citelapp.service.dto.DoadorDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-21T21:56:47-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_252 (Private Build)"
)
@Component
public class DoadorMapperImpl implements DoadorMapper {

    @Override
    public Doador toEntity(DoadorDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Doador doador = new Doador();

        doador.setId( dto.getId() );
        doador.setNome( dto.getNome() );
        doador.setCpf( dto.getCpf() );
        doador.setRg( dto.getRg() );
        doador.setDataNasc( dto.getDataNasc() );
        doador.setSexo( dto.getSexo() );
        doador.setMae( dto.getMae() );
        doador.setPai( dto.getPai() );
        doador.setEmail( dto.getEmail() );
        doador.setCep( dto.getCep() );
        doador.setEndereco( dto.getEndereco() );
        doador.setNumero( dto.getNumero() );
        doador.setBairro( dto.getBairro() );
        doador.setCidade( dto.getCidade() );
        doador.setEstado( dto.getEstado() );
        doador.setTelefoneFixo( dto.getTelefoneFixo() );
        doador.setCelular( dto.getCelular() );
        doador.setAltura( dto.getAltura() );
        doador.setPeso( dto.getPeso() );
        doador.setTipoSanguineo( dto.getTipoSanguineo() );

        return doador;
    }

    @Override
    public DoadorDTO toDto(Doador entity) {
        if ( entity == null ) {
            return null;
        }

        DoadorDTO doadorDTO = new DoadorDTO();

        doadorDTO.setId( entity.getId() );
        doadorDTO.setNome( entity.getNome() );
        doadorDTO.setCpf( entity.getCpf() );
        doadorDTO.setRg( entity.getRg() );
        doadorDTO.setDataNasc( entity.getDataNasc() );
        doadorDTO.setSexo( entity.getSexo() );
        doadorDTO.setMae( entity.getMae() );
        doadorDTO.setPai( entity.getPai() );
        doadorDTO.setEmail( entity.getEmail() );
        doadorDTO.setCep( entity.getCep() );
        doadorDTO.setEndereco( entity.getEndereco() );
        doadorDTO.setNumero( entity.getNumero() );
        doadorDTO.setBairro( entity.getBairro() );
        doadorDTO.setCidade( entity.getCidade() );
        doadorDTO.setEstado( entity.getEstado() );
        doadorDTO.setTelefoneFixo( entity.getTelefoneFixo() );
        doadorDTO.setCelular( entity.getCelular() );
        doadorDTO.setAltura( entity.getAltura() );
        doadorDTO.setPeso( entity.getPeso() );
        doadorDTO.setTipoSanguineo( entity.getTipoSanguineo() );

        return doadorDTO;
    }

    @Override
    public List<Doador> toEntity(List<DoadorDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Doador> list = new ArrayList<Doador>( dtoList.size() );
        for ( DoadorDTO doadorDTO : dtoList ) {
            list.add( toEntity( doadorDTO ) );
        }

        return list;
    }

    @Override
    public List<DoadorDTO> toDto(List<Doador> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DoadorDTO> list = new ArrayList<DoadorDTO>( entityList.size() );
        for ( Doador doador : entityList ) {
            list.add( toDto( doador ) );
        }

        return list;
    }
}
