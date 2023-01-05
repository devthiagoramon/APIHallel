package br.api.hallel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.api.hallel.model.Membro;
import br.api.hallel.model.Usuario;
import br.api.hallel.service.UsuarioService;
import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    public UsuarioService usuarioService;

    @PostMapping("/solicitarCadastro")
    public String solicitarCadastro(@RequestBody Membro membro) {

        if (this.usuarioService.solicitarCadastro(membro)) {
            System.out.println("Solitação enviada");
            return "Solicitação enviada";
        } else {
            System.out.println("Solitação não enviada");
            return "Solicitação recusada, tente novamente";
        }
    }

    @GetMapping("/quantidade")
    public Long quantidadeUsuarios() {
        return this.usuarioService.quantidadeUsuario();
    }

}
