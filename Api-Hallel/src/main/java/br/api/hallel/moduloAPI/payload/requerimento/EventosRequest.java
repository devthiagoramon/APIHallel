package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EventosRequest {
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

    public Eventos toEventosRequest(){

        Eventos request = new Eventos(getAssociadoParticipando(),
                getDescricao(), getQuantidadeMembros(),
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
                false);

        return request;
    }

}
