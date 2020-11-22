package com.citel.citelapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A Doador.
 */
@Entity
@Table(name = "doador")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "doador")
public class Doador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "rg")
    private String rg;

    @Column(name = "data_nasc")
    private String dataNasc;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "mae")
    private String mae;

    @Column(name = "pai")
    private String pai;

    @Column(name = "email")
    private String email;

    @Column(name = "cep")
    private String cep;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "numero")
    private Long numero;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "telefone_fixo")
    private String telefoneFixo;

    @Column(name = "celular")
    private String celular;

    @Column(name = "altura")
    private Double altura;

    @Column(name = "peso")
    private Long peso;

    @Column(name = "tipo_sanguineo")
    private String tipoSanguineo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Doador nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Doador cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public Doador rg(String rg) {
        this.rg = rg;
        return this;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public Doador dataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
        return this;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public Doador sexo(String sexo) {
        this.sexo = sexo;
        return this;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getMae() {
        return mae;
    }

    public Doador mae(String mae) {
        this.mae = mae;
        return this;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getPai() {
        return pai;
    }

    public Doador pai(String pai) {
        this.pai = pai;
        return this;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getEmail() {
        return email;
    }

    public Doador email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public Doador cep(String cep) {
        this.cep = cep;
        return this;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public Doador endereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getNumero() {
        return numero;
    }

    public Doador numero(Long numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public Doador bairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public Doador cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public Doador estado(String estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public Doador telefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
        return this;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getCelular() {
        return celular;
    }

    public Doador celular(String celular) {
        this.celular = celular;
        return this;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Double getAltura() {
        return altura;
    }

    public Doador altura(Double altura) {
        this.altura = altura;
        return this;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Long getPeso() {
        return peso;
    }

    public Doador peso(Long peso) {
        this.peso = peso;
        return this;
    }

    public void setPeso(Long peso) {
        this.peso = peso;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public Doador tipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
        return this;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Doador)) {
            return false;
        }
        return id != null && id.equals(((Doador) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Doador{" +
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
