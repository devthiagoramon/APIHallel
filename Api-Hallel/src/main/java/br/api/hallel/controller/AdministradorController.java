package br.api.hallel.controller;

import java.util.List;

import br.api.hallel.dto.AdministradorDTO;
import br.api.hallel.model.Membro;
import br.api.hallel.payload.requerimento.CadAdministradorRequerimento;
import br.api.hallel.service.MembroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.api.hallel.model.Administrador;
import br.api.hallel.service.AdministradorService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500",allowCredentials = "true")
@RequestMapping("/api/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorService service;

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
    public String alterarAdministrador(@PathVariable(value = "id") String id, @RequestBody Administrador administradorNovo){
        return this.service.alterarAdministrador(id, administradorNovo);
    }

    @PostMapping("/{id}/delete")
    public String deletarAdministrador(@PathVariable String id){
        return this.service.deletarAdministrador(id);
    }

    @GetMapping("/membros/ativo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Membro>> listMembroAtivo(){
        return ResponseEntity.status(200).body(membroService.findByStatusAtivo());
    }

    @GetMapping("/membros/pendente")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Membro>> listMembroPendente(){
        return ResponseEntity.status(200).body(membroService.findByStatusPendente());
    }

    @GetMapping("/membros/inativo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Membro>> listMembroInativo(){
        return ResponseEntity.status(200).body(membroService.findByStatusInativo());
    }

    @GetMapping("/membros")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Membro>> listAllMembros() {
        return ResponseEntity.status(200).body(membroService.listAllMembros());
    }
}
