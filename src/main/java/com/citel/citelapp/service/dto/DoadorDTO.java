package com.citel.citelapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.citel.citelapp.domain.Doador} entity.
 */
public class DoadorDTO implements Serializable {
    
    private Long id;

    private String nome;

    private String cpf;

    private String rg;

    private String dataNasc;

    private String sexo;

    private String mae;

    private String pai;

    private String email;

    private String cep;

    private String endereco;

    private Long numero;

    private String bairro;

    private String cidade;

    private String estado;

    private String telefoneFixo;

    private String celular;

    private Double altura;

    private Long peso;

    private String tipoSanguineo;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Long getPeso() {
        return peso;
    }

    public void setPeso(Long peso) {
        this.peso = peso;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DoadorDTO)) {
            return false;
        }

        return id != null && id.equals(((DoadorDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DoadorDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", rg='" + getRg() + "'" +
            ", dataNasc='" + getDataNasc() + "'" +
            ", sexo='" + getSexo() + "'" +
            ", mae='" + getMae() + "'" +
            ", pai='" + getPai() + "'" +
            ", email='" + getEmail() + "'" +
            ", cep='" + getCep() + "'" +
            ", endereco='" + getEndereco() + "'" +
            ", numero=" + getNumero() +
            ", bairro='" + getBairro() + "'" +
            ", cidade='" + getCidade() + "'" +
            ", estado='" + getEstado() + "'" +
            ", telefoneFixo='" + getTelefoneFixo() + "'" +
            ", celular='" + getCelular() + "'" +
            ", altura=" + getAltura() +
            ", peso=" + getPeso() +
            ", tipoSanguineo='" + getTipoSanguineo() + "'" +
            "}";
    }
}
