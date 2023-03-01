package br.api.hallel.controller;

import br.api.hallel.model.Eventos;
import br.api.hallel.payload.requerimento.CadEventoRequerimento;
import br.api.hallel.service.EventosService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @GetMapping("/listar")
    public ResponseEntity<List<Eventos>> listAllEventos(){
        return ResponseEntity.status(200).body(service.listarAllEventos());
    }

    @GetMapping("{id}")
    public ResponseEntity<Eventos> listEventoById(@PathVariable String id){
        return ResponseEntity.status(201).body(service.listarEventoById(id));
    }

    @GetMapping("{nome}")
    public ResponseEntity<Eventos> listEventoByNome(@PathVariable String nome){
        return ResponseEntity.status(201).body(service.listarEventosByNome(nome));
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<Eventos> updateEventos(@PathVariable String id){
        return ResponseEntity.status(200).body(service.updateEventoById(id));
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
