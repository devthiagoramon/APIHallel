package br.api.hallel.controller;

import br.api.hallel.model.Eventos;
import br.api.hallel.model.MembroMarketing;
import br.api.hallel.service.EventosService;
import br.api.hallel.service.MembroMarketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/marketing")
@CrossOrigin("*")
public class MembroMarketingController {

    @Autowired
    private MembroMarketingService service;
    @Autowired
    private EventosService eventosService;

    @PostMapping("/create")
    public ResponseEntity<MembroMarketing> createMembro(@RequestBody MembroMarketing membro) {
        return ResponseEntity.status(201).body(service.createMembroMarketing(membro));
    }

    @GetMapping("")
    public ResponseEntity<List<MembroMarketing>> listAllMembros() {
        return ResponseEntity.status(200).body(service.listAllMembrosMarketing());

    }

    @GetMapping("/{id}")
    public ResponseEntity<MembroMarketing> listMembroId(@PathVariable String id) {
        return ResponseEntity.status(201).body(service.findMembroId(id));
    }

    @GetMapping("/email")
    public ResponseEntity<MembroMarketing> listMembroEmail(@RequestParam("email") String email){
        return ResponseEntity.status(201).body(this.service.findByEmail(email));
    }


    @GetMapping("/edit/{id}")
    public ResponseEntity<MembroMarketing> updateMembrobyId(@PathVariable String id) {
        return ResponseEntity.status(200).body(service.updateById(id));

    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteMembroById(@PathVariable String id) {
        this.service.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<Eventos>> listAllEventos(){
        return ResponseEntity.status(200).body(eventosService.listarAllEventos());
    }

    @PostMapping("/{id}/edit")
    public Eventos updateEventos(@PathVariable(value = "id") String id){
        return this.eventosService.updateEventoById(id);
    }

}
