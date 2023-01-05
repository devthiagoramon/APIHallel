package br.api.hallel.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Eventos {

    @Id
    private String id;
    private String descricao;
    private Long quantidadeMembros;
    private Long maxMembros;
    private String nome;
    private MembroMarketing membroMarketing;
    private Administrador administrador;
    private String dataInicio;
    private String dataFim;
    private String localidade;

    public Eventos() {
    }

    public Eventos(String descricao, Long quantidadeMembros, String nome, String dataInicio, String dataFim, String localidade) {
        this.descricao = descricao;
        this.quantidadeMembros = quantidadeMembros;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.localidade = localidade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getQuantidadeMembros() {
        return quantidadeMembros;
    }

    public void setQuantidadeMembros(Long quantidadeMembros) {
        this.quantidadeMembros = quantidadeMembros;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public MembroMarketing getMembroMarketing() {
        return membroMarketing;
    }

    public void setMembroMarketing(MembroMarketing membroMarketing) {
        this.membroMarketing = membroMarketing;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public Long getMaxMembros() {
        return maxMembros;
    }

    public void setMaxMembros(Long maxMembros) {
        this.maxMembros = maxMembros;
    }
}
