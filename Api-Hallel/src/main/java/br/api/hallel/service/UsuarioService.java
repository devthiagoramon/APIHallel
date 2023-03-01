package br.api.hallel.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    //ADICIONA UM USUÁRIO AO BANCO DE DADOS
    @Override
    public String inserirUsuario(Usuario usuario) {
        this.repository.insert(usuario);
        return "Usuario adicionado ao sistema";
    }

    //FAZ UMA LISTAGEM DE TODOS OS USUÁRIOS
    @Override
    public List<Usuario> listarTodosUsuarios() {
        return this.repository.findAll(Sort.by(Sort.Direction.ASC, "dataAcesso"));
    }

    //FAZ O SOLICITAMENTO DE CADASTRO
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

    //MÉTODO PARA O PREENCHIMENTO CORRETO DOS CAMPOS
    private Membro validarMembro(Membro membro) {
        if (membro.getNome() == null) {
            throw new IllegalArgumentException("Nome não preenchido");
        }
        if (membro.getEmail() == null) {
            throw new IllegalArgumentException("Email não preenchido");
        }
        if (membro.getSenha() == null) {
            throw new IllegalArgumentException("Senha não preenchida");
        }
        return membro;
    }

    //MÉTODO PARA VERIFICAR SE EXISTE UM USUÁRIO NO SISTEMA
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

    //QUANTIDADE DE USUÁRIOS
    @Override
    public Long quantidadeUsuario() {
        return this.repository.count();
    }

}
