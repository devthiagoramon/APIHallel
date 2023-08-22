package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.financeiroNovo.payload.request.PagamentoEntradaEventoReq;
import br.api.hallel.moduloAPI.model.DespesaEvento;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.payload.requerimento.DespesaEventoRequest;
import br.api.hallel.moduloAPI.payload.requerimento.EventosRequest;
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
    String adicionarMembro(String titulo, String emailUser);
    List<EventosVisualizacaoResponse> listEventoOrdemAlfabetica();
    List<Membro> listMembrosEventos(String id);
    EventosVisualizacaoResponse addDestaqueToEvento(String idEvento);
    EventosVisualizacaoResponse removeDestaqueToEvento(String idEvento);
    List<EventosVisualizacaoResponse> listEventosDestaque();

    EventosResponse adicionarDespesaInEvento(String idEvento, DespesaEventoRequest despesaEvento);
    String editarDespesaInEvento(String idEvento, Integer idDespesaEvento, DespesaEventoRequest despesaEvento);
    void excluirDespesaInEvento(String idEvento, Integer idDespesaEvento);
    List<DespesaEvento> listarDespesasInEvento(String idEvento);
    List<EventosVisualizacaoResponse> listEventosToVisualizar();
    List<EventosVisualizacaoResponse> listByPage(int page);
    Boolean solicitarPagamentoEntrada(String idEvento, String idMembro,
                                      PagamentoEntradaEventoReq request);
    Boolean aceitarSolicitacaoPagamento(String idSolicitacaoPagamento, String idEvento);
}
