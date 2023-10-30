package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.EventosProgramados;
import br.api.hallel.moduloAPI.payload.requerimento.EventoProgramadoRequest;

import java.util.List;

public interface EventosProgramadosInterface {

    Boolean createEventoProgramado(EventoProgramadoRequest request);
    List<EventosProgramados> listAllEventosProgramado();
    EventosProgramados listEventoProgramadoById(String id);
    Boolean updateEventoProgramadoById(String id, EventoProgramadoRequest request);
    Boolean deleteEventoProgramado(String id);

}
