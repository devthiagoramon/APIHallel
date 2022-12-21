package br.api.hallel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.api.hallel.model.Membro;
import br.api.hallel.model.Usuario;
import br.api.hallel.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    public UsuarioService usuarioService;

    @GetMapping("/solicitarCadastro")
    public String solicitarCadastro(@RequestBody Membro membro){

        if(this.usuarioService.solicitarCadastro(membro)){
            return "Solicitação enviada";
        }else{
            return "Solicitação recusada, tente novamente";
        }
    }

    @GetMapping("/quantidade")
    public Long quantidadeUsuarios(){
        return this.usuarioService.quantidadeUsuario();
    }

}
