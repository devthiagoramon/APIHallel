package br.api.hallel.moduloAPI.controller.administrador;

import br.api.hallel.moduloAPI.model.DespesaEvento;
import br.api.hallel.moduloAPI.model.EventoArquivado;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.payload.requerimento.DespesaEventoRequest;
import br.api.hallel.moduloAPI.payload.requerimento.EventosRequest;
import br.api.hallel.moduloAPI.payload.resposta.EventosResponse;
import br.api.hallel.moduloAPI.payload.resposta.EventosVisualizacaoResponse;
import br.api.hallel.moduloAPI.service.EventoArquivadoService;
import br.api.hallel.moduloAPI.service.EventosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/create")
    public ResponseEntity<Eventos> createEventos(@RequestBody EventosRequest request) {

        log.info("Create eventos acessado infos:\n{\n titulo:" + request.getTitulo());

        return ResponseEntity.status(201).body(eventosService.createEvento(request));
    }

    @PostMapping("/{id}/edit")
    public EventosResponse updateEventos(@PathVariable(value = "id") String id,
                                         @RequestBody EventosRequest request) {
        return this.eventosService.updateEventoById(id, request);
    }

    @PostMapping("/{id}/delete")
    public void deleteEvento(@PathVariable(value = "id") String id) {
        this.eventosService.deleteEventoById(id);
    }

    @GetMapping("/{idEvento}/list")
    public ResponseEntity<EventosResponse> listarEventoByIdEvento(@PathVariable(value = "idEvento") String idEvento){
        return ResponseEntity.status(200).body(this.eventosService.listarEventoById(idEvento));
    }


    @GetMapping("/{idEvento}/arquivar")
    public void arquivarEvento(@PathVariable(value = "idEvento") String id){
        this.eventoArquivadoService.addEventoArquivado(id);
    }

    @GetMapping("/{idEvento}/desarquivar")
    public void desarquivarEvento(@PathVariable(value = "idEvento") String idEvento){
        this.eventoArquivadoService.retirarEventoArquivado(idEvento);
    }

    @GetMapping("/arquivados")
    public ResponseEntity<List<EventoArquivado>> eventosArquivados() {
        return ResponseEntity.ok().body(this.eventoArquivadoService.listarEventosArquivados());
    }

    @GetMapping("/asc")
    public List<EventosVisualizacaoResponse> getEventsByOrderAsc() {
        return this.eventosService.listEventoOrdemAlfabetica();
    }

    @PostMapping("/addDestaque/{id}")
    public EventosVisualizacaoResponse addDestaqueToEvent(@PathVariable(value = "id") String id) {
        return this.eventosService.addDestaqueToEvento(id);
    }

    @PostMapping("/removeDestaque/{id}")
    public EventosVisualizacaoResponse removeDestaqueToEvent(@PathVariable(value = "id") String id) {
        return this.eventosService.removeDestaqueToEvento(id);
    }

    @GetMapping("/destaques")
    public List<EventosVisualizacaoResponse> listAllEventsByDestaque() {
        return this.eventosService.listEventosDestaque();
    }

    @GetMapping("/{id}/get/participantes")
    public List<Membro> listAllEventsByDestaque(@PathVariable(value = "id") String id) {
        return this.eventosService.listMembrosEventos(id);
    }

    @PostMapping("/{id}/despesa/add")
    public ResponseEntity<EventosResponse> adicionarDespesaNoEvento(@PathVariable(value = "id") String idEvento,
                                                                    @RequestBody DespesaEventoRequest despesaEventoRequest) {
        return ResponseEntity.status(201).body(this.eventosService.adicionarDespesaInEvento(idEvento, despesaEventoRequest));
    }

    @PutMapping("/{idEvento}/despesa/{idDespesa}/edit")
    public ResponseEntity<String> editarDespesaNoEvento(@PathVariable(value = "idEvento") String idEvento,
                                                        @PathVariable(value = "idDespesa") Integer idDespesa,
                                                        @RequestBody DespesaEventoRequest despesaEventoRequestNew) {
        return ResponseEntity.status(202).body(this.eventosService.editarDespesaInEvento(idEvento, idDespesa, despesaEventoRequestNew));
    }

    @DeleteMapping("/{idEvento}/despesa/{idDespesa}/delete")
    public ResponseEntity<String> excluirDespesaNoEvento(@PathVariable(value = "idEvento") String idEvento,
                                                         @PathVariable(value = "idDespesa") Integer idDespesa) {
        this.eventosService.excluirDespesaInEvento(idEvento, idDespesa);
        return ResponseEntity.status(200).body("Deleta com sucesso");
    }

    @GetMapping("/{idEvento}/despesa/listAll")
    public ResponseEntity<List<DespesaEvento>> listarTodasDespesasNoEvento(@PathVariable(value = "idEvento") String idEvento) {
        return ResponseEntity.status(200).body(this.eventosService.listarDespesasInEvento(idEvento));
    }


}
