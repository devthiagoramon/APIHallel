package br.api.hallel.moduloAPI.controller.administrador;

import br.api.hallel.moduloAPI.exceptions.ApiError;
import br.api.hallel.moduloAPI.financeiroNovo.service.PagamentoEntradaEventoService;
import br.api.hallel.moduloAPI.model.DespesaEvento;
import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.payload.requerimento.DespesaEventoRequest;
import br.api.hallel.moduloAPI.payload.requerimento.EventosRequest;
import br.api.hallel.moduloAPI.payload.resposta.*;
import br.api.hallel.moduloAPI.service.eventos.EventoArquivadoService;
import br.api.hallel.moduloAPI.service.eventos.EventosService;
import br.api.hallel.moduloAPI.service.eventos.VoluntarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/administrador/eventos")
@Slf4j
public class AdmEventosController {

    @Autowired
    private EventosService eventosService;
    @Autowired
    private EventoArquivadoService eventoArquivadoService;
    @Autowired
    private PagamentoEntradaEventoService pagamentoEntradaService;

    @Autowired
    private VoluntarioService voluntarioService;


    @PostMapping("/create")
    public ResponseEntity<?> createEventos(@RequestBody EventosRequest request) {
        log.info("Create eventos acessado infos:\n{\n titulo:" + request.getTitulo());
        log.info(request.toString());
        return ResponseEntity.status(200).body(eventosService.createEvento(request));
    }

    @PostMapping("{data}/create")
    public ResponseEntity<?> createEventosMobile(@PathVariable(value = "data") String data,
                                               @RequestBody EventosRequest request) throws ParseException {

        data = data.replace("-", "/");

        SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yyyy"); // Formato da string recebida





        Date data2 = sdfInput.parse(data);


        request.setDate(data2);

        log.info("Create eventos acessado infos:\n{\n titulo:" + request.getTitulo());

        log.info(request.toString());


        return ResponseEntity.status(200).body(eventosService.createEvento(request));

    }


    @PatchMapping("/{id}/edit")
    public EventosResponse updateEventos(@PathVariable(value = "id") String id,
                                         @RequestBody EventosRequest request) {

        System.out.println(request.toString());
        return this.eventosService.updateEventoById(id, request);
    }


    @PostMapping("/{id}/{data}/edit")
    public EventosResponse updateEventosMobile(@PathVariable(value = "id") String id,@PathVariable(value = "data") String data,
                                         @RequestBody EventosRequest request) throws ParseException {

        data = data.replace("-", "/");

        SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yyyy"); // Formato da string recebida





        Date data2 = sdfInput.parse(data);


        request.setDate(data2);

        System.out.println(request.toString());
        return this.eventosService.updateEventoById(id, request);
    }



    @DeleteMapping("/{id}/delete")
    public void deleteEvento(@PathVariable(value = "id") String id) {


        System.out.println("evento deletado");
        this.eventosService.deleteEventoById(id);
    }

    @GetMapping("/{idEvento}/list")
    public ResponseEntity<EventosResponse> listarEventoByIdEvento(@PathVariable(value = "idEvento") String idEvento) {
        return ResponseEntity.status(200).body(this.eventosService.listarEventoById(idEvento));
    }


    @GetMapping("/{idEvento}/arquivar")
    public void arquivarEvento(@PathVariable(value = "idEvento") String id) {
        this.eventoArquivadoService.addEventoArquivado(id);
    }

    @GetMapping("/{idEvento}/desarquivar")
    public void desarquivarEvento(@PathVariable(value = "idEvento") String idEvento) {
        System.out.println("evento desarquivados");

        this.eventoArquivadoService.retirarEventoArquivado(idEvento);
    }

    @GetMapping("/arquivados")
    public ResponseEntity<?> eventosArquivados() {
        System.out.println("eventos arquivados");

        if (this.eventoArquivadoService.listarEventosArquivados() != null) {
            return ResponseEntity.ok().body(this.eventoArquivadoService.listarEventosArquivados());

        }
        return new ResponseEntity<>(new ApiError(400, "Nenhum evento arquivo para listar", new Date()),
                HttpStatus.NO_CONTENT);
    }

    @GetMapping("/asc")
    public ResponseEntity<?> getEventsByOrderAsc() {
        if (!this.eventosService.listEventoOrdemAlfabetica().isEmpty()) {
            return ResponseEntity.status(200).body(this.eventosService.listEventoOrdemAlfabetica());

        }
        return new ResponseEntity<>(new ApiError(400, "Nenhum evento para listar", new Date()),
                HttpStatus.NO_CONTENT);
    }

    @PostMapping("/addDestaque/{id}")
    public ResponseEntity<EventosVisualizacaoResponse> addDestaqueToEvent(@PathVariable(value = "id") String id) {
        return ResponseEntity.status(200).body(this.eventosService.addDestaqueToEvento(id));
    }

    @PostMapping("/removeDestaque/{id}")
    public ResponseEntity<EventosVisualizacaoResponse> removeDestaqueToEvent(@PathVariable(value = "id") String id) {
        return ResponseEntity.status(200).body(this.eventosService.removeDestaqueToEvento(id));
    }

    @GetMapping("/destaques")
    public ResponseEntity<?> listParticipantesEventos() {
        List<EventosVisualizacaoResponse> eventosVisualizacaoResponses = this.eventosService.listEventosDestacados();
        if (!eventosVisualizacaoResponses.isEmpty()) {
            return ResponseEntity.status(200).body(eventosVisualizacaoResponses);
        }
        return new ResponseEntity<>(new ApiError(400, "Nenhum evento em destaque", new Date()),
                HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/get/participantes")
    public ResponseEntity<List<Membro>> listParticipantesEventos(@PathVariable(value = "id") String id) {
        return ResponseEntity.status(200).body(this.eventosService.listParticipantesEventos(id));
    }

    @PostMapping("/{id}/despesa/add")
    public ResponseEntity<EventosResponse> adicionarDespesaNoEvento(@PathVariable(value = "id") String idEvento,
                                                                    @RequestBody DespesaEventoRequest despesaEventoRequest) {

        return ResponseEntity.status(200).body(this.eventosService.adicionarDespesaInEvento(idEvento, despesaEventoRequest));
    }

    @PutMapping("/{idEvento}/despesa/{idDespesa}/edit")
    public ResponseEntity<String> editarDespesaNoEvento(@PathVariable(value = "idEvento") String idEvento,
                                                        @PathVariable(value = "idDespesa") Integer idDespesa,
                                                        @RequestBody DespesaEventoRequest despesaEventoRequestNew) {
        return ResponseEntity.status(200).body(this.eventosService.editarDespesaInEvento(idEvento, idDespesa, despesaEventoRequestNew));
    }

    @DeleteMapping("/{idEvento}/despesa/{idDespesa}/delete")
    public ResponseEntity<String> excluirDespesaNoEvento(@PathVariable(value = "idEvento") String idEvento,
                                                         @PathVariable(value = "idDespesa") Integer idDespesa) {
        this.eventosService.excluirDespesaInEvento(idEvento, idDespesa);
        return ResponseEntity.status(200).body("Deleta com sucesso");
    }

    @GetMapping("/{idEvento}/despesa/listAll")
    public ResponseEntity<?> listarTodasDespesasNoEvento(@PathVariable(value = "idEvento") String idEvento) {
        List<DespesaEvento> despesas = this.eventosService.listarDespesasInEvento(idEvento);
        if (!despesas.isEmpty()) {
            return ResponseEntity.status(200).body(despesas);
        }
        return new ResponseEntity<>(new ApiError(400,"Nenhuma despesa cadastrada",new Date()),HttpStatus.NO_CONTENT);
    }

    @PostMapping("/confirmar/{idPagamento}/entrada/{idEvento}")
    public ResponseEntity<?> confirmarPagamentoEntrada(@PathVariable(value = "idPagamento") String idPagamento,
                                                       @PathVariable(value = "idEvento") String idEvento) {

        if (eventosService.aceitarSolicitacaoPagamento(idPagamento, idEvento)) {
            return ResponseEntity.accepted().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/recusar/{idPagamento}/entrada/{idEvento}")
    public ResponseEntity<?> recusarPagamentoEntrada(@PathVariable(value = "idPagamento") String idPagamento,
                                                     @PathVariable(value = "idEvento") String idEvento) {

        if (eventosService.recusarSolicitacaoPagamento(idPagamento, idEvento)) {
            return ResponseEntity.accepted().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/AdicionarDescontoParaMembro/{id}")
    public ResponseEntity<Boolean> AdicionaDescontoMembro(@PathVariable(value="id")String idEvento,Double valorDesconto){
        System.out.println(valorDesconto+idEvento);
        return ResponseEntity.ok().body(eventosService.adicionarDescontoParaMembro(idEvento,valorDesconto));
    }

    @PostMapping("/AdicionarDescontoAssociado/{id}")
    public ResponseEntity<Boolean> AdicionaDescontoAssociado(@PathVariable(value = "id") String idEvento, Double valorDesconto){
        return  ResponseEntity.ok().body(eventosService.adicionarDescontoParaAssociado(idEvento,valorDesconto));
    }

    @PostMapping("/AlterarValorEvento/{id}")
    public ResponseEntity<Boolean> AlteraValorEvento(@PathVariable(value="id") String idEvento,Double valorEvento){
        return ResponseEntity.ok().body(eventosService.AlterarValorEvento(idEvento,valorEvento));
    }

    @GetMapping("{id}/listVoluntarios")
    public ResponseEntity<List<SeVoluntariarEventoResponse>> listAllVoluntarios(@PathVariable(value = "id") String idEvento){
        return ResponseEntity.ok().body(voluntarioService.listAllVoluntarios(idEvento));
    }


}
