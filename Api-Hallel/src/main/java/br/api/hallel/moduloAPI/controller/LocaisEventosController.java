package br.api.hallel.moduloAPI.controller;

import br.api.hallel.moduloAPI.model.LocalEvento;
import br.api.hallel.moduloAPI.payload.requerimento.LocalEventoReq;
import br.api.hallel.moduloAPI.payload.resposta.LocalEventoLocalizacaoResponse;
import br.api.hallel.moduloAPI.payload.resposta.LocalEventoResponse;
import br.api.hallel.moduloAPI.service.LocalEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/locais")
public class LocaisEventosController {

    @Autowired
    private LocalEventoService service;

    @GetMapping("")
    public ResponseEntity<List<LocalEvento>> listTodosLocaisEventos(){
        return ResponseEntity.status(200).body(this.service.listarTodosLocalEvento());
    }

    @GetMapping("/listLocalizacao")
    public ResponseEntity<List<LocalEventoLocalizacaoResponse>> listTodosLocaisEventosLocalizacao(){
        return ResponseEntity.status(200).body(this.service.listarTodasLocalizacaoLocalEvento());
    }

    @GetMapping("/{id}/list")
    public ResponseEntity<LocalEventoResponse> listLocalEventoById(@PathVariable String id){
        return ResponseEntity.status(200).body(this.service.listarLocalEventoPorId(id));
    }

    @PostMapping("/create")
    public ResponseEntity<LocalEvento> createLocalEvento(@RequestBody LocalEventoReq localEventoReq){
        return ResponseEntity.status(201).body(this.service.adicionarLocalEvento(localEventoReq));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<LocalEvento> editLocalEvento(@PathVariable String id, @RequestBody LocalEventoReq localEventoReq) {
        return ResponseEntity.status(201).body(this.service.editarLocalEvento(id, localEventoReq));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteLocalEvento(@PathVariable String id){
        return ResponseEntity.status(200).body("Local evento deletado");
    }




}
