package br.api.hallel.moduloAPI.service.interfaces;

import java.util.List;

import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.model.Usuario;

public interface UsuarioInterface {
    
    public String inserirUsuario(Usuario usuario);
    public List<Usuario> listarTodosUsuarios();
    public Boolean solicitarCadastro(Membro membro);
    public Long quantidadeUsuario();

}
