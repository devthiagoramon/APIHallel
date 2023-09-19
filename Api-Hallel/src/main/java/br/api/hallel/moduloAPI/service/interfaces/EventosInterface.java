package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.financeiroNovo.payload.request.PagamentoEntradaEventoReq;
import br.api.hallel.moduloAPI.model.DespesaEvento;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.payload.requerimento.DespesaEventoRequest;
import br.api.hallel.moduloAPI.payload.requerimento.EventosRequest;
import br.api.hallel.moduloAPI.payload.requerimento.InscreverEventoRequest;
import br.api.hallel.moduloAPI.payload.resposta.EventosResponse;
import br.api.hallel.moduloAPI.payload.resposta.EventosVisualizacaoResponse;

import java.util.List;

public interface EventosInterface {

    List<EventosResponse> listarAllEventos();

    EventosResponse listarEventoById(String id);

    EventosResponse listarEventosByTitulo(String nome);

    Eventos createEvento(EventosRequest evento);

    EventosResponse updateEventoById(String id, EventosRequest request);

    void deleteEventoById(String id);

    Boolean inscreverEvento(InscreverEventoRequest inscreverEventoRequest);

    List<EventosVisualizacaoResponse> listEventoOrdemAlfabetica();

    List<Membro> listParticipantesEventos(String id);

    EventosVisualizacaoResponse addDestaqueToEvento(String idEvento);

    EventosVisualizacaoResponse removeDestaqueToEvento(String idEvento);

    List<EventosVisualizacaoResponse> listEventosDestacados();

    EventosResponse adicionarDespesaInEvento(String idEvento, DespesaEventoRequest despesaEvento);

    String editarDespesaInEvento(String idEvento, Integer idDespesaEvento, DespesaEventoRequest despesaEvento);

    void excluirDespesaInEvento(String idEvento, Integer idDespesaEvento);

    List<DespesaEvento> listarDespesasInEvento(String idEvento);

    List<EventosVisualizacaoResponse> listEventosSemDestaqueToVisualizar();

    List<EventosVisualizacaoResponse> listEventosDestacadosToVisualizar();

    List<EventosVisualizacaoResponse> listByPage(int page);

    Boolean solicitarPagamentoEntrada(PagamentoEntradaEventoReq request);

    Boolean aceitarSolicitacaoPagamento(String idSolicitacaoPagamento, String idEvento);
    Boolean recusarSolicitacaoPagamento(String idSolicitacaoPagamento, String idEvento);

}
