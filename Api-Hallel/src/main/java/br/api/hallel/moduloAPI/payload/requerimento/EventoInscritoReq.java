package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.model.Membro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoInscritoReq {

    private String id;
    private String titulo;
    private List<Associado> associadosParticipando;
    private List<Membro> integrantes;
    private String horario;
    private String descricao;
    private List<String> palestrantes;

    public EventoInscritoReq toEventosReq(Eventos eventos){
        EventoInscritoReq request = new EventoInscritoReq();
        request.setId(eventos.getId());
        request.setTitulo(eventos.getTitulo());
        request.setDescricao(eventos.getDescricao());
        request.setAssociadosParticipando(eventos.getAssociadosParticipando());
        request.setIntegrantes(eventos.getIntegrantes());
        request.setHorario(eventos.getHorario());
        request.setPalestrantes(eventos.getPalestrantes());
        return request;
    }

}
