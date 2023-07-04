package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventosResponse {

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

    public EventosResponse toEventosResponse(Eventos eventos) {

        EventosResponse response = new EventosResponse(
                eventos.getId(),
                eventos.getAssociadoParticipando(),
                eventos.getDescricao(),
                eventos.getQuantidadeMembros(),
                eventos.getMaxMembros(),
                eventos.getTitulo(),
                eventos.getIntegrantes(),
                eventos.getMembroMarketing(),
                eventos.getAdministrador(),
                eventos.getDate(),
                eventos.getLocalEvento(),
                eventos.getHorario(),
                eventos.getImagem(),
                eventos.getParticipantesEspeciais(),
                eventos.getDestaque(),
                eventos.getDespesas());

        return response;
    }

    public Eventos toEvento() {
        return new Eventos(this.getId(),
                this.getAssociadoParticipando(),
                this.getDescricao(),
                getQuantidadeMembros(),
                getMaxMembros(),
                getTitulo(),
                getIntegrantes(),
                getMembroMarketing(),
                getAdministrador(),
                getDate(),
                getLocalEvento(),
                getHorario(),
                getImagem(),
                getParticipantesEspeciais(),
                getDestaque(),
                getDespesas());
    }
}
