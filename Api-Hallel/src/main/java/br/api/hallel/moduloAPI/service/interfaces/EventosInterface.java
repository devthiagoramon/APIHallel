package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.DespesaEvento;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.payload.requerimento.DespesaEventoRequest;
import br.api.hallel.moduloAPI.payload.requerimento.EventosRequest;
import br.api.hallel.moduloAPI.payload.resposta.EventosResponse;

import java.util.List;

public interface EventosInterface {

    List<EventosResponse> listarAllEventos();
    EventosResponse listarEventoById(String id);
    EventosResponse listarEventosByTitulo(String nome);
    Eventos createEvento(EventosRequest evento);
    EventosResponse updateEventoById(String id, EventosRequest request);
    void deleteEventoById(String id);
    String adicionarMembro(String titulo, String emailUser);
    List<EventosResponse> listEventoOrdemAlfabetica();
    List<Membro> listMembrosEventos(String id);
    EventosResponse addDestaqueToEvento(String idEvento);
    EventosResponse removeDestaqueToEvento(String idEvento);
    List<EventosResponse> listEventosDestaque();

    EventosResponse adicionarDespesaInEvento(String idEvento, DespesaEventoRequest despesaEvento);
    EventosResponse editarDespesaInEvento(String idEvento, DespesaEventoRequest despesaEvento);
    EventosResponse excluirDespesaInEvento(String idEvento, DespesaEventoRequest despesaEvento);
    List<DespesaEvento> listarDespesasInEvento(String idEvento);

}
