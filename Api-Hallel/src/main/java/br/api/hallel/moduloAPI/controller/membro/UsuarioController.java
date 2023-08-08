package br.api.hallel.moduloAPI.controller.membro;

import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
