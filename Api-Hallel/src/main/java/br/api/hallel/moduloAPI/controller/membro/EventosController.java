package br.api.hallel.moduloAPI.controller.membro;

import br.api.hallel.moduloAPI.financeiroNovo.payload.request.PagamentoEntradaEventoReq;
import br.api.hallel.moduloAPI.model.CartaoAssociado;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.payload.requerimento.EventosRequest;
import br.api.hallel.moduloAPI.payload.requerimento.InscreverEventoRequest;
import br.api.hallel.moduloAPI.payload.resposta.EventosResponse;
import br.api.hallel.moduloAPI.payload.resposta.EventosVisualizacaoResponse;
import br.api.hallel.moduloAPI.service.eventos.EventosService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/eventos")
@CrossOrigin(origins = "*")
@Log4j2
public class EventosController {


    @Autowired
    private EventosService service;

    @GetMapping("")
    public ResponseEntity<List<EventosVisualizacaoResponse>> listAllEventos() {
        return ResponseEntity.status(200).body(service.listEventosToVisualizar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventosResponse> listEventoByIdController(@PathVariable(value = "id") String id) {
        return ResponseEntity.status(201).body(service.listarEventoById(id));
    }

    @GetMapping("/{nome}")
    public ResponseEntity<EventosResponse> listEventoByNomeController(@PathVariable(value = "nome") String nome) {
        return ResponseEntity.status(201).body(service.listarEventosByTitulo(nome));
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<EventosResponse> updateEventos(@PathVariable(value = "id") String id,
                                                         @RequestBody EventosRequest request) {
        return ResponseEntity.status(200).body(service.updateEventoById(id, request));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteEventoById(@PathVariable String id) {
        this.service.deleteEventoById(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/inscrever")
    public ResponseEntity<Boolean> adicionarMembro() {
        InscreverEventoRequest inscreverEventoRequest = new InscreverEventoRequest();
        inscreverEventoRequest.setIdEvento("64b030780dfb1a1620eecb57");
        inscreverEventoRequest.setEmailMembroPagador("miguel@gmail.com");
        inscreverEventoRequest.setNumMetodoPagamento(1);
        log.info(inscreverEventoRequest.toString());
        if (this.service.inscreverEvento(inscreverEventoRequest)) {
            return ResponseEntity.accepted().body(true);
        }
        return ResponseEntity.accepted().body(false);
    }


}
