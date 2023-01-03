package br.api.hallel.controller;

import java.util.List;

import br.api.hallel.dto.AdministradorDTO;
import br.api.hallel.security.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@CrossOrigin("*")
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorService service;

    @PostMapping("/create")
    public String inserirAdministrador(@RequestBody AdministradorDTO administrador) {
        return this.service.inserirAdministrador(administrador.toAdministrador());
    }

    @GetMapping("")
    public List<Administrador> listarAdministradores() {
        return this.service.listarTodosAdministradores();
    }

    @GetMapping("/{id}")
    public Administrador listarAdministradorPorId(@PathVariable String id) {
        return this.service.findAdministrador(id);
    }

    @PostMapping("/login")
    public ResponseEntity<Token> logarAdministrador(@RequestBody AdministradorDTO administradorDTO) {
        Token token = this.service.gerarToken(administradorDTO);
        if(token!=null){
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/{id}/update")
    public String alterarAdministrador(@PathVariable(value = "id") String id, @RequestBody Administrador administradorNovo){
        return this.service.alterarAdministrador(id, administradorNovo);
    }

    @PostMapping("/{id}/delete")
    public String deletarAdministrador(@PathVariable String id){
        return this.service.deletarAdministrador(id);
    }
}
