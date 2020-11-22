package com.citel.citelapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.citel.citelapp.domain.Doador} entity. This class is used
 * in {@link com.citel.citelapp.web.rest.DoadorResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /doadors?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DoadorCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nome;

    private StringFilter cpf;

    private StringFilter rg;

    private StringFilter dataNasc;

    private StringFilter sexo;

    private StringFilter mae;

    private StringFilter pai;

    private StringFilter email;

    private StringFilter cep;

    private StringFilter endereco;

    private LongFilter numero;

    private StringFilter bairro;

    private StringFilter cidade;

    private StringFilter estado;

    private StringFilter telefoneFixo;

    private StringFilter celular;

    private DoubleFilter altura;

    private LongFilter peso;

    private StringFilter tipoSanguineo;

    public DoadorCriteria() {
    }

    public DoadorCriteria(DoadorCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nome = other.nome == null ? null : other.nome.copy();
        this.cpf = other.cpf == null ? null : other.cpf.copy();
        this.rg = other.rg == null ? null : other.rg.copy();
        this.dataNasc = other.dataNasc == null ? null : other.dataNasc.copy();
        this.sexo = other.sexo == null ? null : other.sexo.copy();
        this.mae = other.mae == null ? null : other.mae.copy();
        this.pai = other.pai == null ? null : other.pai.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.cep = other.cep == null ? null : other.cep.copy();
        this.endereco = other.endereco == null ? null : other.endereco.copy();
        this.numero = other.numero == null ? null : other.numero.copy();
        this.bairro = other.bairro == null ? null : other.bairro.copy();
        this.cidade = other.cidade == null ? null : other.cidade.copy();
        this.estado = other.estado == null ? null : other.estado.copy();
        this.telefoneFixo = other.telefoneFixo == null ? null : other.telefoneFixo.copy();
        this.celular = other.celular == null ? null : other.celular.copy();
        this.altura = other.altura == null ? null : other.altura.copy();
        this.peso = other.peso == null ? null : other.peso.copy();
        this.tipoSanguineo = other.tipoSanguineo == null ? null : other.tipoSanguineo.copy();
    }

    @Override
    public DoadorCriteria copy() {
        return new DoadorCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNome() {
        return nome;
    }

    public void setNome(StringFilter nome) {
        this.nome = nome;
    }

    public StringFilter getCpf() {
        return cpf;
    }

    public void setCpf(StringFilter cpf) {
        this.cpf = cpf;
    }

    public StringFilter getRg() {
        return rg;
    }

    public void setRg(StringFilter rg) {
        this.rg = rg;
    }

    public StringFilter getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(StringFilter dataNasc) {
        this.dataNasc = dataNasc;
    }

    public StringFilter getSexo() {
        return sexo;
    }

    public void setSexo(StringFilter sexo) {
        this.sexo = sexo;
    }

    public StringFilter getMae() {
        return mae;
    }

    public void setMae(StringFilter mae) {
        this.mae = mae;
    }

    public StringFilter getPai() {
        return pai;
    }

    public void setPai(StringFilter pai) {
        this.pai = pai;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getCep() {
        return cep;
    }

    public void setCep(StringFilter cep) {
        this.cep = cep;
    }

    public StringFilter getEndereco() {
        return endereco;
    }

    public void setEndereco(StringFilter endereco) {
        this.endereco = endereco;
    }

    public LongFilter getNumero() {
        return numero;
    }

    public void setNumero(LongFilter numero) {
        this.numero = numero;
    }

    public StringFilter getBairro() {
        return bairro;
    }

    public void setBairro(StringFilter bairro) {
        this.bairro = bairro;
    }

    public StringFilter getCidade() {
        return cidade;
    }

    public void setCidade(StringFilter cidade) {
        this.cidade = cidade;
    }

    public StringFilter getEstado() {
        return estado;
    }

    public void setEstado(StringFilter estado) {
        this.estado = estado;
    }

    public StringFilter getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(StringFilter telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public StringFilter getCelular() {
        return celular;
    }

    public void setCelular(StringFilter celular) {
        this.celular = celular;
    }

    public DoubleFilter getAltura() {
        return altura;
    }

    public void setAltura(DoubleFilter altura) {
        this.altura = altura;
    }

    public LongFilter getPeso() {
        return peso;
    }

    public void setPeso(LongFilter peso) {
        this.peso = peso;
    }

    public StringFilter getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(StringFilter tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DoadorCriteria that = (DoadorCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nome, that.nome) &&
            Objects.equals(cpf, that.cpf) &&
            Objects.equals(rg, that.rg) &&
            Objects.equals(dataNasc, that.dataNasc) &&
            Objects.equals(sexo, that.sexo) &&
            Objects.equals(mae, that.mae) &&
            Objects.equals(pai, that.pai) &&
            Objects.equals(email, that.email) &&
            Objects.equals(cep, that.cep) &&
            Objects.equals(endereco, that.endereco) &&
            Objects.equals(numero, that.numero) &&
            Objects.equals(bairro, that.bairro) &&
            Objects.equals(cidade, that.cidade) &&
            Objects.equals(estado, that.estado) &&
            Objects.equals(telefoneFixo, that.telefoneFixo) &&
            Objects.equals(celular, that.celular) &&
            Objects.equals(altura, that.altura) &&
            Objects.equals(peso, that.peso) &&
            Objects.equals(tipoSanguineo, that.tipoSanguineo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nome,
        cpf,
        rg,
        dataNasc,
        sexo,
        mae,
        pai,
        email,
        cep,
        endereco,
        numero,
        bairro,
        cidade,
        estado,
        telefoneFixo,
        celular,
        altura,
        peso,
        tipoSanguineo
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DoadorCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nome != null ? "nome=" + nome + ", " : "") +
                (cpf != null ? "cpf=" + cpf + ", " : "") +
                (rg != null ? "rg=" + rg + ", " : "") +
                (dataNasc != null ? "dataNasc=" + dataNasc + ", " : "") +
                (sexo != null ? "sexo=" + sexo + ", " : "") +
                (mae != null ? "mae=" + mae + ", " : "") +
                (pai != null ? "pai=" + pai + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (cep != null ? "cep=" + cep + ", " : "") +
                (endereco != null ? "endereco=" + endereco + ", " : "") +
                (numero != null ? "numero=" + numero + ", " : "") +
                (bairro != null ? "bairro=" + bairro + ", " : "") +
                (cidade != null ? "cidade=" + cidade + ", " : "") +
                (estado != null ? "estado=" + estado + ", " : "") +
                (telefoneFixo != null ? "telefoneFixo=" + telefoneFixo + ", " : "") +
                (celular != null ? "celular=" + celular + ", " : "") +
                (altura != null ? "altura=" + altura + ", " : "") +
                (peso != null ? "peso=" + peso + ", " : "") +
                (tipoSanguineo != null ? "tipoSanguineo=" + tipoSanguineo + ", " : "") +
            "}";
    }

}
