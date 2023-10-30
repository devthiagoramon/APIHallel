package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.model.Usuario;

import java.util.List;

public interface UsuarioInterface {
    
    public String inserirUsuario(Usuario usuario);
    public List<Usuario> listarTodosUsuarios();
    public Boolean solicitarCadastro(Membro membro);
    public Long quantidadeUsuario();

}
