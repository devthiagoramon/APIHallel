package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentoEntradaEvento;
import br.api.hallel.moduloAPI.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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

    private List<Membro> integrantes;
    private MembroMarketing membroMarketing;
    private Administrador administrador;


    private Date date;
    private LocalEvento localEvento;
    private String horario;
    private String imagem;
    private String banner;
    private Long participantesEspeciais;
    private Boolean destaque;

    private List<DespesaEvento> despesas;

    private List<String> palestrantes;
    private List<PagamentoEntradaEvento> pagamentoEntradaEventos;
    private List<DoacaoDinheiroEvento> doacaoDinheiroEventos;
    private List<DoacaoObjetosEventos> doacaoObjetosEventos;
    private Double valorDoEvento;
    private Double ValorDescontoMembro;
    private Double ValorDescontoAssociado;

    public EventosResponse toEventosResponse(Eventos eventos) {

        EventosResponse response = new EventosResponse();

        response.setId(eventos.getId());
        response.setAdministrador(eventos.getAdministrador());
        response.setDescricao(eventos.getDescricao());
        response.setAssociadoParticipando(eventos.getAssociadosParticipando());
        response.setQuantidadeMembros(eventos.getQuantidadeMembros());
        response.setMaxMembros(eventos.getMaxMembros());
        response.setTitulo(eventos.getTitulo());
        response.setIntegrantes(eventos.getIntegrantes());
        response.setMembroMarketing(eventos.getMembroMarketing());
        response.setAdministrador(eventos.getAdministrador());
        response.setDate(eventos.getDate());
        response.setLocalEvento(eventos.getLocalEvento());
        response.setHorario(eventos.getHorario());
        response.setImagem(eventos.getImagem());
        response.setParticipantesEspeciais(eventos.getParticipantesEspeciais());
        response.setDestaque(eventos.getDestaque());
        response.setDespesas(eventos.getDespesas());
        response.setBanner(eventos.getBanner());
        response.setPalestrantes(eventos.getPalestrantes());
        response.setValorDoEvento(eventos.getValorDoEvento());
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
                getBanner(),
                getParticipantesEspeciais(),
                getDestaque(),
                getDespesas(),
                getPalestrantes(),
                getPagamentoEntradaEventos(),
                getValorDoEvento()
        );
    }
}
