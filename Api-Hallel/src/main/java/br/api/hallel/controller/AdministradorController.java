package br.api.hallel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String inserirAdministrador(@RequestBody Administrador administrador) {
        return this.service.inserirAdministrador(administrador);
    }

    @GetMapping("")
    public List<Administrador> listarAdministradores() {
        return this.service.listarTodosAdministradores();
    }

    @GetMapping("/{id}")
    public Administrador listarAdministradorPorId(@PathVariable String id) {
        return this.service.findAdministrador(id);
    }

    @GetMapping("/{id}/{senhaAcesso}")
    public Administrador acessarAdministrador(@PathVariable(value = "id") String id,
            @PathVariable(value = "senhaAcesso") String senhaAcesso) {
        return this.service.acessarAdministrador(id, senhaAcesso);
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
