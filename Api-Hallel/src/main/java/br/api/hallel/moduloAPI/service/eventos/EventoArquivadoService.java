package br.api.hallel.moduloAPI.service.eventos;

import br.api.hallel.moduloAPI.model.EventoArquivado;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.payload.resposta.EventosResponse;
import br.api.hallel.moduloAPI.repository.EventoArquivadoRepository;
import br.api.hallel.moduloAPI.service.interfaces.EventoArquivadoInterface;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class EventoArquivadoService implements EventoArquivadoInterface {

    @Autowired
    private EventoArquivadoRepository repository;
    @Autowired
    private EventosService eventosService;

    @Override
    public void addEventoArquivado(String idEvento) {
        EventosResponse eventosResponse = eventosService.listarEventoById(idEvento);
        Eventos evento = eventosResponse.toEvento();
        eventosService.deleteEventoById(idEvento);
        log.info("Evento " + evento.getTitulo() + " arquivado");
        this.repository.insert(new EventoArquivado().arquivarEvento(evento));
    }

    @Override
    public void retirarEventoArquivado(String idEventoArquivado) {
        EventoArquivado eventoArquivado = this.repository.findById(idEventoArquivado).get() != null ? this.repository.findById(idEventoArquivado).get() : null;
        log.info("Evento " + eventoArquivado.getTitulo() + " desarquivado");
        eventosService.createEvento(eventoArquivado.desarquivarEvento());
        this.repository.deleteById(idEventoArquivado);
    }

    @Override
    public void excluirEventoArquivado(String idEvento) {

    }

    @Override
    public List<EventoArquivado> listarEventosArquivados() {
        return this.repository.findAll();
    }
}
