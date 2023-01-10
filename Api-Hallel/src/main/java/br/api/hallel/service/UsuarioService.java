package br.api.hallel.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Override
    public String inserirUsuario(Usuario usuario) {
        logger.info("Usuários inserido! -- Administrador Logado: "+getLogado());

        this.repository.insert(usuario);
        return "Usuario adicionado ao sistema";
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        logger.info("Usuários listados! -- Administrador Logado: "+getLogado());
        return this.repository.findAll(Sort.by(Sort.Direction.ASC, "dataAcesso"));
    }

    @Override
    public Boolean solicitarCadastro(Membro membro) {
        if (validarMembro(membro) == null) {
            logger.error("Não foi possível validar o membro! -- Administrador: "+getLogado());
            return false;
        }
        if (verificarSeExiste(membro)) {
            logger.error("Membro já existente! -- Administrador: "+getLogado());

            return false;
        }
        membro.setStatus(StatusMembro.PENDENTE);
        this.membroService.createMembro(membro);
        logger.info("Cadastro solicitado! -- Adiministrador "+getLogado());
        return true;
    }

    private Membro validarMembro(Membro membro) {
        if (membro.getNome() == null) {
            logger.error("Credencial 'nome' não preenchida! -- Administrador Logado: "+getLogado());

            throw new IllegalArgumentException("Nome não preenchido");
        }
        if (membro.getEmail() == null) {
            logger.error("Credencial 'email' não preenchida! -- Administrador Logado: "+getLogado());

            throw new IllegalArgumentException("Email não preenchido");
        }
        if (membro.getSenha() == null) {
            logger.error("Credencial 'senha' não preenchida! -- Administrador Logado: "+getLogado());

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
                logger.error("Usuário já existente -- Administrador Logado: "+getLogado());
                break;
            }
        }
        return existe;
    }

    @Override
    public Long quantidadeUsuario() {
        logger.info("Listando quantidade de usuários! "+getLogado());
        return this.repository.count();
    }

    private String getLogado() {
        Authentication adminLogado = SecurityContextHolder.getContext().getAuthentication();
        if (!(adminLogado instanceof AnonymousAuthenticationToken)) {
            return adminLogado.getName();
        }
        return "null";

    }

}
