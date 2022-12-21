package br.api.hallel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.api.hallel.model.Membro;
import br.api.hallel.model.StatusMembro;
import br.api.hallel.model.Usuario;
import br.api.hallel.repository.UsuarioRepository;
import br.api.hallel.service.interfaces.UsuarioInterface;

@Service
public class UsuarioService implements UsuarioInterface {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private MembroService membroService;

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
    public Boolean solicitarCadastro(Membro membro) {
        if (validarMembro(membro) == null) {
            return false;
        }
        if (verificarSeExiste(membro)) {
            return false;
        }
        membro.setStatus(StatusMembro.PENDENTE);
        this.membroService.createMembro(membro);
        return true;
    }

    private Membro validarMembro(Membro membro) {
        if (membro.getNome() == null) {
            throw new IllegalArgumentException("Nome não preenchido");
        }
        if (membro.getDataAniversario() == null) {
            throw new IllegalArgumentException("Data de aniversario não foi preenchida");
        }
        if (membro.getEmail() == null) {
            throw new IllegalArgumentException("Email não preenchido");
        }
        if (membro.getSenha() == null) {
            throw new IllegalArgumentException("Senha não preenchida");
        }
        return membro;
    }

    private boolean verificarSeExiste(Membro membro) {
        boolean existe = false;
        List<Membro> membros = this.membroService.listAllMembros();
        for (Membro membroList : membros) {
            if (membroList.getNome().equals(membro.getNome())) {
                existe = true;
            }
        }
        return existe;
    }

    @Override
    public Long quantidadeUsuario() {
        return this.repository.count();
    }

}
