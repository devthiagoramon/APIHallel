package br.api.hallel.moduloAPI.service.eventos;

import br.api.hallel.moduloAPI.exceptions.events.EventoNotFoundException;
import br.api.hallel.moduloAPI.model.EventoArquivado;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.payload.resposta.EventosResponse;
import br.api.hallel.moduloAPI.repository.EventoArquivadoRepository;
import br.api.hallel.moduloAPI.service.interfaces.EventoArquivadoInterface;
import br.api.hallel.moduloAPI.service.main.MainService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class EventoArquivadoService implements EventoArquivadoInterface {

    @Autowired
    private EventoArquivadoRepository repository;
    @Autowired
    private EventosService eventosService;

    //Deixa um evento arquivado no Banco, e remove ele da Tabela de 'Eventos'
    @Override
    public void addEventoArquivado(String idEvento) {
        EventosResponse eventosResponse = eventosService.listarEventoById(idEvento);
        Eventos evento = eventosResponse.toEvento();

        if(!MainService.comparateDatas(evento.getDate(),new Date())){
            eventosService.deleteEventoById(idEvento);
            log.info("Evento " + evento.getId()+ " já ocorreu... Sendo arquivado");
            this.repository.insert(new EventoArquivado().arquivarEvento(evento));
        }

    }

    //Retira o evento do arquivado, e deixa ele de volta pra Tabela de 'Eventos'
    @Override
    public void retirarEventoArquivado(String idEventoArquivado) {
        EventoArquivado eventoArquivado = this.repository.findById(idEventoArquivado).isPresent() ?
                this.repository.findById(idEventoArquivado).get() : null;

        if (eventoArquivado == null){
            throw new EventoNotFoundException("Evento não encontrado. Não foi possível desarquivar!");
        }

        log.info("Evento " + eventoArquivado.getTitulo() + " desarquivado");
        eventosService.createEvento(eventoArquivado.desarquivarEvento());
        this.repository.deleteById(idEventoArquivado);
    }

    //Remover um evento arquivado
    @Override
    public void excluirEventoArquivado(String idEvento) {

    }

    //Listar todos os eventos arquivados
    @Override
    public List<EventoArquivado> listarEventosArquivados() {
        return !this.repository.findAll().isEmpty() ? this.repository.findAll(): null;
    }
}
