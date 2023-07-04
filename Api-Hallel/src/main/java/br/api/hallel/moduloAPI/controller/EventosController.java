package br.api.hallel.moduloAPI.controller;

import br.api.hallel.moduloAPI.model.DespesaEvento;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.payload.requerimento.DespesaEventoRequest;
import br.api.hallel.moduloAPI.payload.requerimento.EventosRequest;
import br.api.hallel.moduloAPI.payload.resposta.EventosResponse;
import br.api.hallel.moduloAPI.service.EventosService;
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

    @GetMapping("")
    public ResponseEntity<List<EventosResponse>> listAllEventos(){
        return ResponseEntity.status(200).body(service.listarAllEventos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventosResponse> listEventoByIdController(@PathVariable(value = "id") String id){
        return ResponseEntity.status(201).body(service.listarEventoById(id));
    }

    @GetMapping("/{nome}")
    public ResponseEntity<EventosResponse> listEventoByNomeController(@PathVariable(value = "nome") String nome){
        return ResponseEntity.status(201).body(service.listarEventosByTitulo(nome));
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<EventosResponse> updateEventos(@PathVariable(value = "id") String id,
                                                         @RequestBody EventosRequest request){
        return ResponseEntity.status(200).body(service.updateEventoById(id, request));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteEventoById(@PathVariable String id){
        this.service.deleteEventoById(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/adicionarMembro")
    public ResponseEntity<String> adicionarMembro(@RequestParam(value = "titulo") String titulo,
                                             @RequestParam(value = "emailUser") String emailUser){
        return ResponseEntity.ok().body(this.service.adicionarMembro(titulo,emailUser));
    }


}
