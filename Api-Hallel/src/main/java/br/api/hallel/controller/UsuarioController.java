package br.api.hallel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.api.hallel.model.Usuario;
import br.api.hallel.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    public UsuarioService usuarioService;

    @PostMapping("/create")
    public String adicionarUsuario(@RequestBody Usuario usuario){
        return this.usuarioService.inserirUsuario(usuario);
    }

    public List<Usuario> listarUsuarios(){
        return this.usuarioService.listarTodosUsuarios();
    }

}
