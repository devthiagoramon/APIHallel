package br.api.hallel.moduloAPI.controller.membro;

import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.payload.requerimento.EventosRequest;
import br.api.hallel.moduloAPI.payload.requerimento.InscreverEventoRequest;
import br.api.hallel.moduloAPI.payload.resposta.EventosResponse;
import br.api.hallel.moduloAPI.payload.resposta.EventosVisualizacaoResponse;
import br.api.hallel.moduloAPI.service.eventos.EventosService;
import br.api.hallel.moduloAPI.service.main.MembroService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/eventos")
@CrossOrigin(origins = "*")
@Log4j2
public class EventosController {


    @Autowired
    private EventosService service;
    @Autowired
    private MembroService membroService;

    @GetMapping("")
    public ResponseEntity<List<EventosVisualizacaoResponse>> listAllEventos() {
        return ResponseEntity.status(200).body(service.listEventosSemDestaqueToVisualizar());
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

    public Boolean adicionarMembro(InscreverEventoRequest inscreverEventoRequest) {

        log.info(inscreverEventoRequest.toString());
        if (this.service.inscreverEvento(inscreverEventoRequest)) {
            return true;
        }
        return false;
    }

    @GetMapping("/verificarEmail/{email}")
    public ResponseEntity<Boolean> verificarEmailMembro(@PathVariable(value = "email") String email){
        Membro membroDb = this.membroService.findByEmail(email);
        if (membroDb!=null) {
            return ResponseEntity.accepted().body(true);
        }
        return ResponseEntity.accepted().body(false);
    }


//    @PostMapping("/verificarEmail/{email}/{idEvento}")
//    public ResponseEntity<Boolean> verificarEmailMembro(@PathVariable(value = "email") String email,
//                                                        @PathVariable(value = "idEvento") String idEvento) {
//
//        Membro membroBD = this.membroService.findByEmail(email);
//
//        if (membroBD != null) {
//            this.service.preencherFormularioInscricaoComBanco(membroBD, idEvento);
//            return ResponseEntity.accepted().body(true);
//        }
//
//        return ResponseEntity.accepted().body(false);
//    }
}
