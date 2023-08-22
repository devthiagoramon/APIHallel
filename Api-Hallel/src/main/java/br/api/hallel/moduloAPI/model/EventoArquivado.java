package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoArquivado {

    @Id
    private String id;
    private List<Associado> associadoParticipando;
    private String descricao;
    private Long quantidadeMembros;
    private Long maxMembros;
    private String titulo;
    private ArrayList<Membro> integrantes;
    private MembroMarketing membroMarketing;
    private Administrador administrador;
    private String date;
    private LocalEvento localEvento;
    private String horario;
    private String imagem;
    private Long participantesEspeciais;
    private Boolean destaque;

    private List<DespesaEvento> despesas;
    private List<String> palestrantes;

    public EventoArquivado arquivarEvento(Eventos eventos) {
        EventoArquivado arquivado = new EventoArquivado();

        arquivado.setId(eventos.getId());
        arquivado.setAdministrador(eventos.getAdministrador());
        arquivado.setDescricao(eventos.getDescricao());
        arquivado.setAssociadoParticipando(eventos.getAssociadoParticipando());
        arquivado.setQuantidadeMembros(eventos.getQuantidadeMembros());
        arquivado.setMaxMembros(eventos.getMaxMembros());
        arquivado.setTitulo(eventos.getTitulo());
        arquivado.setIntegrantes(eventos.getIntegrantes());
        arquivado.setMembroMarketing(eventos.getMembroMarketing());
        arquivado.setAdministrador(eventos.getAdministrador());
        arquivado.setDate(eventos.getDate());
        arquivado.setLocalEvento(eventos.getLocalEvento());
        arquivado.setHorario(eventos.getHorario());
        arquivado.setImagem(eventos.getImagem());
        arquivado.setParticipantesEspeciais(eventos.getParticipantesEspeciais());
        arquivado.setDestaque(eventos.getDestaque());
        arquivado.setDespesas(eventos.getDespesas());
        arquivado.setPalestrantes(eventos.getPalestrantes());

        return arquivado;
    }

    public Eventos desarquivarEvento() {

        Eventos eventoDesarquivado = new Eventos();

        eventoDesarquivado.setId(getId());
        eventoDesarquivado.setAdministrador(getAdministrador());
        eventoDesarquivado.setDescricao(getDescricao());
        eventoDesarquivado.setAssociadoParticipando(getAssociadoParticipando());
        eventoDesarquivado.setQuantidadeMembros(getQuantidadeMembros());
        eventoDesarquivado.setMaxMembros(getMaxMembros());
        eventoDesarquivado.setTitulo(getTitulo());
        eventoDesarquivado.setIntegrantes(getIntegrantes());
        eventoDesarquivado.setMembroMarketing(getMembroMarketing());
        eventoDesarquivado.setAdministrador(getAdministrador());
        eventoDesarquivado.setDate(getDate());
        eventoDesarquivado.setLocalEvento(getLocalEvento());
        eventoDesarquivado.setHorario(getHorario());
        eventoDesarquivado.setImagem(getImagem());
        eventoDesarquivado.setParticipantesEspeciais(getParticipantesEspeciais());
        eventoDesarquivado.setDestaque(getDestaque());
        eventoDesarquivado.setDespesas(getDespesas());
        eventoDesarquivado.setPalestrantes(getPalestrantes());

        return eventoDesarquivado;

    }
}
