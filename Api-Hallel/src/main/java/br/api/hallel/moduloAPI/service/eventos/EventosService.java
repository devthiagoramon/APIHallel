package br.api.hallel.moduloAPI.service.eventos;

import br.api.hallel.moduloAPI.financeiroNovo.model.EntradasFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentoEntradaEvento;
import br.api.hallel.moduloAPI.financeiroNovo.model.StatusEntradaEvento;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.PagamentoEntradaEventoReq;
import br.api.hallel.moduloAPI.financeiroNovo.repository.PagamentoEntradaEventoRepository;
import br.api.hallel.moduloAPI.financeiroNovo.service.PagamentoEntradaEventoService;
import br.api.hallel.moduloAPI.model.*;
import br.api.hallel.moduloAPI.payload.requerimento.*;
import br.api.hallel.moduloAPI.payload.resposta.EventosResponse;
import br.api.hallel.moduloAPI.payload.resposta.EventosVisualizacaoResponse;
import br.api.hallel.moduloAPI.repository.EventosRepository;
import br.api.hallel.moduloAPI.repository.LocalEventoRepository;
import br.api.hallel.moduloAPI.service.interfaces.EventosInterface;
import br.api.hallel.moduloAPI.service.main.MembroService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log4j2
public class EventosService implements EventosInterface {

    @Autowired
    private EventosRepository repository;
    @Autowired
    private PagamentoEntradaEventoRepository pagamentoRepository;
    @Autowired
    private MembroService membroService;
    @Autowired
    private LocalEventoRepository localEventoRepository;
    @Autowired
    private PagamentoEntradaEventoService pagamentoEntradaService;

    //CRIA EVENTOS
    @Override
    public Eventos createEvento(EventosRequest evento) {
        log.info("EVENT0 CRIADO!");

        LocalEventoLocalizacaoRequest localEventoRequest = evento.getLocalEventoRequest();

        Optional<LocalEvento> optional = localEventoRepository.findById(localEventoRequest.getId());
        if (optional.isEmpty()) {
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

    //LISTAR EVENTOS NA HOMEPAGE
    @Override
    public List<EventosVisualizacaoResponse> listEventosSemDestaqueToVisualizar() {
        List<EventosVisualizacaoResponse> listaResponse = new ArrayList<>();
        this.repository.findAll().forEach(eventos ->{
            if(eventos.getDestaque() == null){
                listaResponse.add(new EventosVisualizacaoResponse().toListEventosResponse(eventos));
            }else {
                if (!eventos.getDestaque()) {
                    listaResponse.add(new EventosVisualizacaoResponse().toListEventosResponse(eventos));
                }
            }
        });
        return listaResponse;
    }

    @Override
    public List<EventosVisualizacaoResponse> listEventosDestacadosToVisualizar(){
        List<EventosVisualizacaoResponse> listaResponse = new ArrayList<>();
        this.repository.findAll().forEach(eventos -> {
            if(eventos.getDestaque() != null) {
                if (eventos.getDestaque()) {
                    listaResponse.add(new EventosVisualizacaoResponse().toListEventosResponse(eventos));
                }
            }
        });
        return listaResponse;
    }

    //Listar eventos por página
    @Override
    public List<EventosVisualizacaoResponse> listByPage(int page) {
        List<EventosVisualizacaoResponse> listaResponse = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, 15);

        for (Eventos eventos : this.repository.findAll(pageable)) {
            listaResponse.add(new EventosVisualizacaoResponse().toListEventosResponse(eventos));
        }

        log.info("Eventos para visualização listados!");

        return listaResponse;
    }

    //Solicitar a entrada do evento (precisa pagar a entrada)
    @Override
    public Boolean solicitarPagamentoEntrada(PagamentoEntradaEventoReq request, Eventos eventos, Membro membro) {
        log.info("126 - FUNCIONANDO");
        log.info("129 - FUNCIONANDO");
        log.info("137 - FUNCIONANDO");


        if (eventos.getPagamentoEntradaEventoList() == null) {
            List<PagamentoEntradaEvento> list = new ArrayList<>();
            list.add(request.toPagamentoEntradaEvento());
            eventos.setPagamentoEntradaEventoList(list);

        } else {
            log.info(request.toPagamentoEntradaEvento().toString());
            eventos.getPagamentoEntradaEventoList().add(request.toPagamentoEntradaEvento());

        }

        log.info("148 - FUNCIONANDO");


//
//        this.repository.save(eventos);
//        this.membroService.updateMembro(membro.getId(), membro);
//        this.pagamentoEntradaService.cadastrar(request);

        return true;
    }

    //Administrador aceita o pagamento realizado pelo membro que deseja participar do evento
    @Override
    public Boolean aceitarSolicitacaoPagamento(String idSolicitacaoPagamento, String idEvento) {
        PagamentoEntradaEvento pagamento = this.pagamentoRepository.findById(idSolicitacaoPagamento).isPresent() ?
                this.pagamentoRepository.findById(idSolicitacaoPagamento).get() : null;

        Eventos eventos = this.repository.findById(idEvento).isPresent() ?
                this.repository.findById(idEvento).get() : null;

        EntradasFinanceiro entradaOld = new EntradasFinanceiro();
        entradaOld.setDate(pagamento.getDate());
        entradaOld.setMetodoPagamento(pagamento.getMetodoPagamento());
        entradaOld.setValor(pagamento.getValor());
        entradaOld.setCodigo(pagamento.getCodigo());

        EntradasFinanceiro entradaNew = new EntradasFinanceiro();
        for (PagamentoEntradaEvento pagamentoEntrada : eventos.getPagamentoEntradaEventoList()) {
            entradaNew.setDate(pagamentoEntrada.getDate());
            entradaNew.setMetodoPagamento(pagamentoEntrada.getMetodoPagamento());
            entradaNew.setValor(pagamentoEntrada.getValor());
            entradaNew.setCodigo(pagamentoEntrada.getCodigo());

            if (entradaOld.equals(entradaNew)) {
                pagamento.setStatusEntrada(StatusEntradaEvento.CONFIRMADO);
                int index = eventos.getPagamentoEntradaEventoList().indexOf(entradaOld);
                eventos.getPagamentoEntradaEventoList().set(index, new PagamentoEntradaEventoReq().toPag(pagamentoEntrada));

                break;
            }
        }

        this.pagamentoRepository.save(pagamento);
        this.repository.save(eventos);
        return true;
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
        if (optional.isEmpty()) {
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
        eventoOld.setDestaque(request.getDestaque());

        if (request.getPalestrantes() != null || request.getPalestrantes().isEmpty()) {
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
    public Boolean inscreverEvento(InscreverEventoRequest inscreverEventoRequest) {
        Optional<Eventos> eventosOptional = this.repository.findById(inscreverEventoRequest.getIdEvento());

        if (eventosOptional.isPresent()) {
            Eventos eventos = eventosOptional.get();
            Membro membro = this.membroService.listMembroId(inscreverEventoRequest.getIdMembro());

            log.info("LINHA 265 - FUNCIONANDO");

            if (eventos.getIntegrantes() == null) {
                List<Membro> membros = new ArrayList<>();
                membros.add(membro);
                eventos.setIntegrantes(membros);
            } else {
                eventos.getIntegrantes().add(membro);
            }

            log.info("LINHA 275 - FUNCIONANDO");
            if (membro.getEventosParticipando() == null) {
                List<Eventos> eventosList = new ArrayList<>();
                eventosList.add(eventos);
                membro.setEventosParticipando(eventosList);
            } else {
                membro.getEventosParticipando().add(eventos);
            }

            log.info("LINHA 283 - FUNCIONANDO");

            log.info(membro.getNome());
            log.info(eventos.getTitulo());
            log.info(inscreverEventoRequest.toString());

            PagamentoEntradaEventoReq request = new PagamentoEntradaEventoReq();
            request.setStatus(StatusEntradaEvento.ANDAMENTO);
            request.setMembro(membro);
            request.setEventos(eventos);
            CartaoAssociado cart = new CartaoAssociado();
            cart.setCvc(123);
            cart.setNomeTitular("Miguel Arcanjo Brasil de Lima");
            cart.setEndereco("Rua jose da Costa tapajos");
            cart.setDataValidadeCartao(new Date());
            request.setCartaoAssociado(cart);
            inscreverEventoRequest.setPagamentoEntradaEvento(request);

            this.solicitarPagamentoEntrada(inscreverEventoRequest.getPagamentoEntradaEvento(), eventos, membro);
            return true;
        }

        return false;
    }

    //Lista eventos por ordem alfabética
    @Override
    public List<EventosVisualizacaoResponse> listEventoOrdemAlfabetica() {

        List<EventosVisualizacaoResponse> listResponse = new ArrayList<>();

        this.repository.findAllByOrderByTituloAsc().forEach(eventos -> {
            listResponse.add(new EventosVisualizacaoResponse().toListEventosResponse(eventos));
        });

        log.info("Listando eventos em ordem alfabetica");
        return listResponse;
    }

    //Lista membros de um evento
    @Override
    public List<Membro> listMembrosEventos(String id) {

        EventosResponse eventosResponse = this.listarEventoById(id);

        log.info("Listando membros participando de um evento");

        return eventosResponse.getIntegrantes();
    }


    //Eventos em Destaque na HomePage
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

    //Remover esse destaque da HomePage
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

    //Listar os eventos em Destaque
    @Override
    public List<EventosVisualizacaoResponse> listEventosDestacados() {

        List<EventosVisualizacaoResponse> responseList = new ArrayList<>();

        this.repository.findAllByDestaqueEquals(true).forEach(eventos -> {
            responseList.add(new EventosVisualizacaoResponse().toListEventosResponse(eventos));
        });

        log.info("Listando eventos em destaque");

        return responseList;
    }

    //Adicionar despesas de um evento
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

    //Editar a despesa desse evento
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

        log.info("Despesa de id " + idDespesaEvento + " evento " + evento.getTitulo() + " editado com sucesso");

        return "Despesa de id " + idDespesaEvento + " evento " + evento.getTitulo() + " editado com sucesso";
    }

    //Remover a despesa desse evento
    @Override
    public void excluirDespesaInEvento(String idEvento, Integer idDespesaEvento) {
        Eventos evento = listarEventoById(idEvento).toEvento();

        int indexDespesa = -1;

        for (DespesaEvento despesaEvento : evento.getDespesas()) {
            if (Objects.equals(idDespesaEvento, despesaEvento.getId())) {
                indexDespesa = evento.getDespesas().indexOf(despesaEvento);
            }
        }

        if (indexDespesa == -1) {
            log.info("Despesa com id: " + idDespesaEvento + ", inexistente");
            return;
        }

        evento.getDespesas().remove(indexDespesa);
        this.repository.save(evento);
    }

    //Listar todas as despesas do evento
    @Override
    public List<DespesaEvento> listarDespesasInEvento(String idEvento) {
        Eventos evento = listarEventoById(idEvento).toEvento();
        return evento.getDespesas();
    }


}
