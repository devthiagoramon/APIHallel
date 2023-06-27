package br.api.hallel.moduloAPI.controller;

import br.api.hallel.moduloAPI.model.Administrador;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.payload.requerimento.CadAdministradorRequerimento;
import br.api.hallel.moduloAPI.payload.requerimento.EventosRequest;
import br.api.hallel.moduloAPI.payload.resposta.EventosResponse;
import br.api.hallel.moduloAPI.service.AdministradorService;
import br.api.hallel.moduloAPI.service.EventosService;
import br.api.hallel.moduloAPI.service.MembroService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/administrador")
@Slf4j
public class AdministradorController {

    @Autowired
    private AdministradorService service;
    @Autowired
    private EventosService eventosService;
    @Autowired
    private MembroService membroService;

    @PostMapping("/create")
    public ResponseEntity<?> inserirAdministrador(@Valid @RequestBody CadAdministradorRequerimento administradorReq) {
        return this.service.inserirAdministrador(administradorReq);
    }

    @GetMapping("")
    public List<Administrador> listarAdministradores() {
        return this.service.listarTodosAdministradores();
    }

    @GetMapping("/{id}")
    public Administrador listarAdministradorPorId(@PathVariable String id) {
        return this.service.findAdministrador(id);
    }

    @PostMapping("/{id}/update")
    public String alterarAdministrador(@PathVariable(value = "id") String id, @RequestBody Administrador administradorNovo) {
        return this.service.alterarAdministrador(id, administradorNovo);
    }

    @PostMapping("/{id}/delete")
    public String deletarAdministrador(@PathVariable String id) {
        return this.service.deletarAdministrador(id);
    }

    @GetMapping("/membros/ativo")
    public ResponseEntity<List<Membro>> listMembroAtivo() {
        return ResponseEntity.status(200).body(membroService.findByStatusAtivo());
    }

    @GetMapping("/membros/pendente")
    public ResponseEntity<List<Membro>> listMembroPendente() {
        return ResponseEntity.status(200).body(membroService.findByStatusPendente());
    }

    @GetMapping("/membros/inativo")
    public ResponseEntity<List<Membro>> listMembroInativo() {
        return ResponseEntity.status(200).body(membroService.findByStatusInativo());
    }

    @GetMapping("/membros")
    public ResponseEntity<List<Membro>> listAllMembros() {
        return ResponseEntity.status(200).body(membroService.listAllMembros());
    }

    @GetMapping("/membros/{id}")
    public ResponseEntity<Membro> listMembroId(@PathVariable String id) {
        return ResponseEntity.status(201).body(membroService.listMembroId(id));
    }

    @PostMapping("/evento/create")
    public ResponseEntity<Eventos> createEventos(@RequestBody EventosRequest request) {

        log.info("Create eventos acessado infos:\n{\n titulo:" + request.getTitulo() + "\n" +
                "descricao:" + request.getDescricao() + "\n" +
                "local: " + request.getLocalEvento());

        return ResponseEntity.status(201).body(eventosService.createEvento(request));
    }

    @PostMapping("/evento/{id}/edit")
    public EventosResponse updateEventos(@PathVariable(value = "id") String id,
                                         @RequestBody EventosRequest request) {
        return this.eventosService.updateEventoById(id, request);
    }

    @PostMapping("/evento/{id}/delete")
    public void deleteEvento(@PathVariable(value = "id") String id) {
        this.eventosService.deleteEventoById(id);
    }

    @GetMapping("/eventos/asc")
    public List<EventosResponse> getEventsByOrderAsc() {
        return this.eventosService.listEventoOrdemAlfabetica();
    }

    @PostMapping("/evento/addDestaque/{id}")
    public EventosResponse addDestaqueToEvent(@PathVariable(value = "id") String id) {
        return this.eventosService.addDestaqueToEvento(id);
    }

    @PostMapping("/evento/removeDestaque/{id}")
    public EventosResponse removeDestaqueToEvent(@PathVariable(value = "id") String id) {
        return this.eventosService.removeDestaqueToEvento(id);
    }

    @GetMapping("/eventos/destaques")
    public List<EventosResponse> listAllEventsByDestaque() {
        return this.eventosService.listEventosDestaque();
    }

    @GetMapping("{id}/get/participantes")
    public List<Membro> listAllEventsByDestaque(@PathVariable(value = "id") String id) {
        return this.eventosService.listMembrosEventos(id);
    }
}