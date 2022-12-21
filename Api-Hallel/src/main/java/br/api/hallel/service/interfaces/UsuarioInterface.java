package br.api.hallel.service.interfaces;

import java.util.List;

import br.api.hallel.model.Membro;
import br.api.hallel.model.Usuario;

public interface UsuarioInterface {
    
    public String inserirUsuario(Usuario usuario);
    public List<Usuario> listarTodosUsuarios();
    public Usuario listarUsuarioPorId(String id);
    public String alterarUsuarioDataAcesso(String id, Usuario usuarioNovo);
    public String deletarAluno(String id);
    public Usuario encontrarPorId(String ip);
    public Membro solicitarCadastro(Membro membro);
}
