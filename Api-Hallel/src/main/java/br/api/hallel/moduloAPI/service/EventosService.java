package br.api.hallel.moduloAPI.service;

import br.api.hallel.moduloAPI.model.DespesaEvento;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.model.LocalEvento;
import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.payload.requerimento.DespesaEventoRequest;
import br.api.hallel.moduloAPI.payload.requerimento.EventosRequest;
import br.api.hallel.moduloAPI.payload.requerimento.LocalEventoLocalizacaoRequest;
import br.api.hallel.moduloAPI.payload.resposta.EventosResponse;
import br.api.hallel.moduloAPI.payload.resposta.EventosVisualizacaoResponse;
import br.api.hallel.moduloAPI.repository.EventosRepository;
import br.api.hallel.moduloAPI.repository.LocalEventoRepository;
import br.api.hallel.moduloAPI.service.interfaces.EventosInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class EventosService implements EventosInterface {

    @Autowired
    private EventosRepository repository;
    @Autowired
    private MembroService membroService;
    @Autowired
    private ComunidadeService comunidadeService;

    @Autowired
    private LocalEventoRepository localEventoRepository;


    //CRIA EVENTOS
    @Override
    public Eventos createEvento(EventosRequest evento) {
        log.info("EVENT0 CRIADO!");

        LocalEventoLocalizacaoRequest localEventoRequest = evento.getLocalEventoRequest();

        Optional<LocalEvento> optional = localEventoRepository.findById(localEventoRequest.getId());
        if (optional.isEmpty()){
            return null;
        }
        return this.repository.insert(evento.toCreateRequest(optional.get()));
    }

    public Eventos createEvento(Eventos evento) {
        log.info("EVENT0 CRIADO!");
        return this.repository.insert(evento);
    }

    //LISTA OS EVENTOS JÁ CRIADOS
    @Override
    public List<EventosResponse> listarAllEventos() {

        List<EventosResponse> listaResponse = new ArrayList<>();

        this.repository.findAll().forEach(eventos -> {
            listaResponse.add(new EventosResponse().toEventosResponse(eventos));
        });

        log.info("Eventos listados!");

        return listaResponse;
    }

    //LISTA APENAS UM EVENTO PELO SEU ID
    @Override
    public EventosResponse listarEventoById(String id) {
        EventosResponse response = new EventosResponse();
        Optional<Eventos> optional = this.repository.findById(String.valueOf(id));

        if (optional.isPresent()) {
            Eventos eventos = optional.get();

            log.info("Evento listado");
            return response.toEventosResponse(eventos);
        }

        return null;
    }

    @Override
    public List<EventosVisualizacaoResponse> listEventosToVisualizar() {
        List<EventosVisualizacaoResponse> listaResponse = new ArrayList<>();

        this.repository.findAll().forEach(eventos -> {
            listaResponse.add(new EventosVisualizacaoResponse().toListEventosResponse(eventos));
        });

        log.info("Eventos para visualização listados!");
        return listaResponse;
    }


    //LISTA O EVENTO PELO SEU NOME
    @Override
    public EventosResponse listarEventosByTitulo(String nome) {
        EventosResponse response = new EventosResponse();
        Optional<Eventos> optional = this.repository.findByTitulo(nome);

        if (optional.isPresent()) {
            Eventos eventos = optional.get();

            log.info("Evento listado");
            return response.toEventosResponse(eventos);
        }

        return null;
    }

    //ATUALIZA AS INFORMAÇÕES DO EVENTO
    @Override
    public EventosResponse updateEventoById(String id, EventosRequest request) {

        LocalEventoLocalizacaoRequest localEventoRequest = request.getLocalEventoRequest();

        Optional<LocalEvento> optional = localEventoRepository.findById(localEventoRequest.getId());
        if (optional.isEmpty()){
            return null;
        }

        EventosResponse eventoOld = this.listarEventoById(id);

        eventoOld.setId(id);
        eventoOld.setTitulo(request.getTitulo());
        eventoOld.setDescricao(request.getDescricao());
        eventoOld.setImagem(request.getImagem());
        eventoOld.setDate(request.getDate());
        eventoOld.setHorario(request.getHorario());
        eventoOld.setLocalEvento(optional.get());

        if(request.getPalestrantes() != null || request.getPalestrantes().size() == 0) {
            eventoOld.setPalestrantes(request.getPalestrantes());
        }

        Eventos eventosResponse = this.repository.save(eventoOld.toEvento());

        log.info("Evento Atualizado!");

        return new EventosResponse().toEventosResponse(eventosResponse);
    }

    //REMOVE UM EVENTO
    @Override
    public void deleteEventoById(String id) {


        if (listarEventoById(id) != null) {

            this.repository.deleteById(id);
            log.info("EVENTO DELETADO!");

        } else {
            log.warn("EVENTO NÃO ENCONTRADO, ENTÃO NAO FOI REMOVIDO!");

        }
    }


    //ADICIONA UM MEMBRO AO EVENTO
    @Override
    public String adicionarMembro(String titulo, String emailUser) {
        Optional<Eventos> optional = this.repository.findByTitulo(titulo);

        if (optional.isPresent()) {
            Eventos evento = optional.get();
            if (evento.getIntegrantes() != null) {
                evento.getIntegrantes().add(membroService.findByEmail(emailUser));
            } else {
                ArrayList<Membro> integrantes = new ArrayList<>();
                integrantes.add(membroService.findByEmail(emailUser));
                evento.setIntegrantes(integrantes);
            }
            log.info("MEMBRO CADASTRADO AO EVENTO!");
            this.repository.save(evento);

            return "Membro cadastrado no evento com sucesso";
        }
        log.info("EVENTO NÃO ECONTRADO!");

        return "Evento não encontrado";
    }


    @Override
    public List<EventosVisualizacaoResponse> listEventoOrdemAlfabetica() {

        List<EventosVisualizacaoResponse> listResponse = new ArrayList<>();

        this.repository.findAllByOrderByTituloAsc().forEach(eventos -> {
            listResponse.add(new EventosVisualizacaoResponse().toListEventosResponse(eventos));
        });

        log.info("Listando eventos em ordem alfabetica");
        return listResponse;
    }

    @Override
    public List<Membro> listMembrosEventos(String id) {

        EventosResponse eventosResponse = this.listarEventoById(id);

        log.info("Listando membros participando de um evento");

        return eventosResponse.getIntegrantes();
    }


    @Override
    public EventosVisualizacaoResponse addDestaqueToEvento(String idEvento) {

        if (!listarEventoById(idEvento).getDestaque()) {
            Eventos request = this.repository.findById(idEvento).get();
            request.setId(idEvento);
            request.setDestaque(true);

            Eventos eventosResponse = this.listarEventoById(idEvento) != null ?
                    this.repository.save(request) : null;

            return new EventosVisualizacaoResponse().toListEventosResponse(eventosResponse);
        } else {
            log.warn("Evento já está em destaque");
            return null;
        }

    }

    @Override
    public EventosVisualizacaoResponse removeDestaqueToEvento(String idEvento) {

        if (listarEventoById(idEvento).getDestaque()) {
            Eventos request = this.repository.findById(idEvento).get();
            request.setId(idEvento);
            request.setDestaque(false);

            Eventos eventosResponse = this.listarEventoById(idEvento) != null ?
                    this.repository.save(request) : null;

            return new EventosVisualizacaoResponse().toListEventosResponse(eventosResponse);
        } else {

            log.warn("Evento já está sem destaque");
            return null;
        }
    }

    @Override
    public List<EventosVisualizacaoResponse> listEventosDestaque() {

        List<EventosVisualizacaoResponse> responseList = new ArrayList<>();

        this.repository.findAllByDestaqueEquals(true).forEach(eventos -> {
            responseList.add(new EventosVisualizacaoResponse().toListEventosResponse(eventos));
        });

        log.info("Listando eventos em destaque");

        return responseList;
    }

    @Override
    public EventosResponse adicionarDespesaInEvento(String idEvento, DespesaEventoRequest despesaEvento) {
        Eventos evento = listarEventoById(idEvento).toEvento();
        DespesaEvento despesaEventoObj = despesaEvento.toDespesaEvento();
        if (evento.getDespesas() == null) {
            evento.setDespesas(new ArrayList<>());
        }
        despesaEventoObj.setId(evento.getDespesas().size() + 1);
        evento.getDespesas().add(despesaEventoObj);
        this.repository.save(evento);

        return new EventosResponse().toEventosResponse(evento);
    }

    @Override
    public String editarDespesaInEvento(String idEvento, Integer idDespesaEvento, DespesaEventoRequest despesaEvento) {
        Eventos evento = listarEventoById(idEvento).toEvento();
        DespesaEvento despesaEventoObj = despesaEvento.toDespesaEvento();

        /**
         * Pegar o index no array de despesas da despesa que for alterar
         * */
        int indexDespesa = -1;

        for (DespesaEvento despesaEventoInterator : evento.getDespesas()) {
            if (Objects.equals(idDespesaEvento, despesaEventoInterator.getId())) {
                indexDespesa = evento.getDespesas().indexOf(despesaEventoInterator);
            }
        }

        DespesaEvento despesaEventoObjOld = evento.getDespesas().get(indexDespesa);

        despesaEventoObjOld.setNome(despesaEventoObj.getNome());
        despesaEventoObjOld.setTipoDespesa(despesaEventoObj.getTipoDespesa());
        despesaEventoObjOld.setDescricao(despesaEventoObj.getDescricao());
        despesaEventoObjOld.setQuantidade(despesaEventoObj.getQuantidade());
        despesaEventoObjOld.setValor(despesaEventoObj.getValor());

        evento.getDespesas().set(indexDespesa, despesaEventoObjOld);

        this.repository.save(evento);

        log.info("Despesa de id "+idDespesaEvento+" evento "+evento.getTitulo()+" editado com sucesso");

        return "Despesa de id "+idDespesaEvento+" evento "+evento.getTitulo()+" editado com sucesso";
    }

    @Override
    public void excluirDespesaInEvento(String idEvento, Integer idDespesaEvento) {
        Eventos evento = listarEventoById(idEvento).toEvento();

        int indexDespesa = -1;

        for (DespesaEvento despesaEvento : evento.getDespesas()) {
            if (Objects.equals(idDespesaEvento, despesaEvento.getId())) {
                indexDespesa = evento.getDespesas().indexOf(despesaEvento);
            }
        }

        if(indexDespesa == -1){
            log.info("Despesa com id: "+idDespesaEvento+", inexistente");
            return;
        }

        evento.getDespesas().remove(indexDespesa);
        this.repository.save(evento);
    }

    @Override
    public List<DespesaEvento> listarDespesasInEvento(String idEvento) {
        Eventos evento = listarEventoById(idEvento).toEvento();
        return evento.getDespesas();
    }


}
