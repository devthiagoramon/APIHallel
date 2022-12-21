package br.api.hallel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.api.hallel.model.Membro;
import br.api.hallel.model.Usuario;
import br.api.hallel.repository.UsuarioRepository;
import br.api.hallel.service.interfaces.UsuarioInterface;

@Service
public class UsuarioService implements UsuarioInterface {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public String inserirUsuario(Usuario usuario) {
        this.repository.insert(usuario);
        return "Usuario adicionado ao sistema";
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        return this.repository.findAll(Sort.by(Sort.Direction.ASC, "dataAcesso"));
    }

    @Override
    public Usuario listarUsuarioPorId(String id) {

        Optional<Usuario> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Usuario usuario = optional.get();
            return usuario;
        } else {
            throw new IllegalArgumentException("Usuario não encontrado");
        }

    }

    @Override
    public String alterarUsuarioDataAcesso(String id, Usuario usuarioNovo) {
        Usuario usuario = listarUsuarioPorId(id);
        usuario.setDataAcesso(usuarioNovo.getDataAcesso());
        this.repository.save(usuario);

        return "Data do usuario atualizado com sucesso";
    }

    @Override
    public String deletarAluno(String id) {
        Usuario usuario = listarUsuarioPorId(id);
        this.repository.delete(usuario);
        return "Usuario deletado com sucesso";
    }

    @Override
    public Membro solicitarCadastro(Membro membro) {

        

        return null;
    }

    @Override
    public Usuario encontrarPorId(String ip) {
        return this.repository.findByIp(ip);
    }

    

}
