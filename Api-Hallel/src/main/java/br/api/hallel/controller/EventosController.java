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

import java.util.List;

@RestController
@RequestMapping(value = "/api/eventos")
@CrossOrigin("*")
@Log4j2
public class EventosController {

    Logger logger = LoggerFactory.getLogger(EventosController.class);

    @Autowired
    private EventosService service;

    @PostMapping("/create")
    public ResponseEntity<Eventos> createEventos(@RequestBody CadEventoRequerimento cadEvento){

        logger.info("Create eventos acessado infos:\n{\n titulo:"+cadEvento.getTitulo()+"\n" +
                "descricao:"+cadEvento.getDescricao()+"\n" +
                "local: "+cadEvento.getLocal()+
                "\nimagem: "+cadEvento.getImagem());

        return ResponseEntity.status(201).body(service.createEvento(cadEvento.toEventos()));
    }

    @GetMapping("")
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


}
