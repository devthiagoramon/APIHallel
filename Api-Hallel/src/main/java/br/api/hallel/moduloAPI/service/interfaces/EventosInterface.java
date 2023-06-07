package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.Eventos;

import java.util.List;

public interface EventosInterface {

    List<Eventos> listarAllEventos();
    Eventos listarEventoById(String id);
    Eventos listarEventosByNome(String nome);
    Eventos createEvento(Eventos evento);
    Eventos updateEventoById(String id);
    void deleteEventoById(String id);
    String adicionarMembro(String titulo, String emailUser);
    List<Eventos> listEventoOrdemAlfabetica();
}
