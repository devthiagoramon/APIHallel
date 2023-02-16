package br.api.hallel.service.interfaces;

import br.api.hallel.model.Doacao;
import br.api.hallel.model.Eventos;

import java.util.Date;
import java.util.List;

public interface EventosInterface {

    List<Eventos> listarAllEventos();
    Eventos listarEventoById(String id);
    Eventos listarEventosByNome(String nome);
    Eventos createEvento(Eventos evento);
    Eventos updateEventoById(String id);
    void deleteEventoById(String id);
    Double getDespesaMensal();
    Eventos updateValorTotal(String id);
    Eventos despesasEvento(String id, Double despesa);
    Eventos lucroEvento(String id, Double lucro);


    String adicionarMembro(String titulo, String emailUser);
}
