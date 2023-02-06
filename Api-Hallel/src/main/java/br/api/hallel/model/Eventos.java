package br.api.hallel.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
public class Eventos {

    @Id
    private String id;
    private String descricao;
    private Long quantidadeMembros;
    private Long maxMembros;
    private String titulo;

    private ArrayList<Membro> integrantes;
    private MembroMarketing membroMarketing;
    private Administrador administrador;
    private String date;
    private String localidade;
    private String horario;
    private String imagem;
    private Long participantesEspeciais;

    public Eventos() {
    }

    public Eventos(String descricao, Long quantidadeMembros, String nome, String date, String localidade
            , String horario, Long participantesEspeciais ) {
        this.descricao = descricao;
        this.quantidadeMembros = quantidadeMembros;
        this.titulo = nome;
        this.date = date;
        this.localidade = localidade;
        this.horario = horario;
        this.participantesEspeciais = participantesEspeciais;
    }


    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Long getParticipantesEspeciais() {
        return participantesEspeciais;
    }

    public void setParticipantesEspeciais(Long participantesEspeciais) {
        this.participantesEspeciais = participantesEspeciais;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public ArrayList<Membro> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(ArrayList<Membro> integrantes) {
        this.integrantes = integrantes;
    }
}
