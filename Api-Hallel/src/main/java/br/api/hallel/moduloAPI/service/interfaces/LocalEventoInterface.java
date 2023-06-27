package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.LocalEvento;
import br.api.hallel.moduloAPI.payload.requerimento.LocalEventoReq;
import br.api.hallel.moduloAPI.payload.resposta.LocalEventoLocalizacaoResponse;
import br.api.hallel.moduloAPI.payload.resposta.LocalEventoResponse;

import java.util.List;

public interface LocalEventoInterface {
    public LocalEvento adicionarLocalEvento(LocalEventoReq localEventoReq);
    public LocalEvento editarLocalEvento(String idLocalEvento, LocalEventoReq newLocalEvento);

    public void excluirLocalEvento(String id);
    public List<LocalEventoLocalizacaoResponse> listarTodasLocalizacaoLocalEvento();
    public List<LocalEvento> listarTodosLocalEvento();
    public LocalEventoResponse listarLocalEventoPorId(String id);
    public LocalEventoLocalizacaoResponse listarLocalizacaoLocalEventoPorId(String id);
}
