package br.api.hallel.controller;

import br.api.hallel.model.Administrador;
import br.api.hallel.model.Eventos;
import br.api.hallel.model.Membro;
import br.api.hallel.payload.requerimento.CadAdministradorRequerimento;
import br.api.hallel.service.AdministradorService;
import br.api.hallel.service.EventosService;
import br.api.hallel.service.MembroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/administrador")
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

    @GetMapping("/eventos")
    public ResponseEntity<List<Eventos>> listAllEventos() {
        return ResponseEntity.status(200).body(eventosService.listarAllEventos());
    }

    @PostMapping("/evento/{id}/edit")
    public Eventos updateEventos(@PathVariable(value = "id") String id) {
        return this.eventosService.updateEventoById(id);
    }

    @PostMapping("/evento/{id}/delete")
    public void deleteEvento(@PathVariable(value = "id") String id) {
        this.eventosService.deleteEventoById(id);
    }
}