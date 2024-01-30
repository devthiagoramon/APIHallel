package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.VoluntarioEvento;
import br.api.hallel.moduloAPI.payload.requerimento.SeVoluntariarEventoReq;
import br.api.hallel.moduloAPI.payload.resposta.SeVoluntariarEventoResponse;

import java.util.List;

public interface VoluntarioEventonterface {

    VoluntarioEvento createVoluntario(SeVoluntariarEventoReq seVoluntariarEventoReq);

    List<SeVoluntariarEventoResponse> listAllVoluntarios(String idEevnto);

    List<SeVoluntariarEventoResponse> listVoluntariosById(String Id);

    SeVoluntariarEventoResponse updateVoluntarioById(String Id, SeVoluntariarEventoReq seVoluntariarEventoReq);

    void deleteVoluntarioById(String Id);

    void deleteVoluntarioByObjeto(SeVoluntariarEventoReq seVoluntariarEventoReq);
}
