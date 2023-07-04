package br.api.hallel.moduloAPI.service;

import br.api.hallel.moduloAPI.model.DespesaEvento;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.payload.requerimento.DespesaEventoRequest;
import br.api.hallel.moduloAPI.payload.requerimento.EventosRequest;
import br.api.hallel.moduloAPI.payload.resposta.EventosResponse;
import br.api.hallel.moduloAPI.repository.EventosRepository;
import br.api.hallel.moduloAPI.service.interfaces.EventosInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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


    //CRIA EVENTOS
    @Override
    public Eventos createEvento(EventosRequest evento) {
        log.info("EVENT0 CRIADO!");

        return this.repository.insert(evento.toCreateRequest());
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
        Optional<Eventos> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Eventos eventos = optional.get();

            log.info("Evento listado");
            return response.toEventosResponse(eventos);
        }

        return null;
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


        Eventos eventoAux = request.toEventosRequest();
        eventoAux.setId(id);

        Eventos eventosResponse = this.listarEventoById(id) != null ?
                this.repository.save(eventoAux) : null;

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
    public List<EventosResponse> listEventoOrdemAlfabetica() {

        List<EventosResponse> listResponse = new ArrayList<>();

        this.repository.findAllByOrderByTituloAsc().forEach(eventos -> {
            listResponse.add(new EventosResponse().toEventosResponse(eventos));
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
    public EventosResponse addDestaqueToEvento(String idEvento) {

        if (!listarEventoById(idEvento).getDestaque()) {
            Eventos request = this.repository.findById(idEvento).get();
            request.setId(idEvento);
            request.setDestaque(true);

            Eventos eventosResponse = this.listarEventoById(idEvento) != null ?
                    this.repository.save(request) : null;

            return new EventosResponse().toEventosResponse(eventosResponse);
        } else {
            log.warn("Evento já está em destaque");
            return null;
        }

    }

    @Override
    public EventosResponse removeDestaqueToEvento(String idEvento) {

        if (listarEventoById(idEvento).getDestaque()) {
            Eventos request = this.repository.findById(idEvento).get();
            request.setId(idEvento);
            request.setDestaque(false);

            Eventos eventosResponse = this.listarEventoById(idEvento) != null ?
                    this.repository.save(request) : null;

            return new EventosResponse().toEventosResponse(eventosResponse);
        } else {

            log.warn("Evento já está sem destaque");
            return null;
        }
    }

    @Override
    public List<EventosResponse> listEventosDestaque() {

        List<EventosResponse> responseList = new ArrayList<>();

        this.repository.findAllByDestaqueEquals(true).forEach(eventos -> {
            responseList.add(new EventosResponse().toEventosResponse(eventos));
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
        return new EventosResponse().toEventosResponse(evento);
    }

    @Override
    public String editarDespesaInEvento(String idEvento, Integer idDespesaEvento, DespesaEventoRequest despesaEvento) {
        Eventos evento = listarEventoById(idEvento).toEvento();
        DespesaEvento despesaEventoObj = despesaEvento.toDespesaEvento();

        DespesaEvento despesaEventoObjOld = evento.getDespesas().get(idDespesaEvento);

        despesaEventoObjOld.setNome(despesaEventoObj.getNome());
        despesaEventoObjOld.setTipoDespesa(despesaEventoObj.getTipoDespesa());
        despesaEventoObjOld.setDescricao(despesaEventoObj.getDescricao());
        despesaEventoObjOld.setQuantidade(despesaEventoObj.getQuantidade());
        despesaEventoObjOld.setValor(despesaEventoObj.getValor());

        evento.getDespesas().add(idDespesaEvento, despesaEventoObjOld);

        log.info("Despesa de id "+idDespesaEvento+" evento "+evento.getTitulo()+" editado com sucesso");

        return "Despesa de id "+idDespesaEvento+" evento "+evento.getTitulo()+" editado com sucesso";
    }

    @Override
    public void excluirDespesaInEvento(String idEvento, Integer idDespesaEvento) {
        Eventos evento = listarEventoById(idEvento).toEvento();

        if(evento.getDespesas().get(idDespesaEvento) != null){
            evento.getDespesas().remove(idDespesaEvento);
        }else{
            log.info("Despesa com id: "+idDespesaEvento+", inexistente");
        }
    }

    @Override
    public List<DespesaEvento> listarDespesasInEvento(String idEvento) {
        Eventos evento = listarEventoById(idEvento).toEvento();
        return evento.getDespesas();
    }


}
