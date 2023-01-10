package br.api.hallel.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.api.hallel.model.Membro;
import br.api.hallel.model.StatusMembro;
import br.api.hallel.repository.MembroRepository;
import br.api.hallel.service.interfaces.MembroInterface;

@Service
public class MembroService implements MembroInterface {

    @Autowired
    private MembroRepository repository;

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private Logger logger = LoggerFactory.getLogger(MembroService.class);
    @Override
    public Membro createMembro(Membro membro) {
        System.out.println("Criando membro");
        String encoder = this.passwordEncoder().encode(membro.getSenha());
        membro.setSenha(encoder);
        return this.repository.insert(membro);
    }

    @Override
    public List<Membro> listAllMembros() {
        logger.info("Listando todos os membros! -- Administrador: "+getLogado());
        return this.repository.findAll(Sort.by(Direction.ASC, "status"));
    }

    @Override
    public Membro listMembroId(String id) {
        logger.info("Membro: "+this.repository.findById(id).get().getNome()+" listado! -- Administrador: "+getLogado());

        return this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Membro não existe"));
    }

    @Override
    public Membro updatePerfilMembro(String id, Membro membroModel) {
        Optional<Membro> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Membro membro = optional.get();
            membro.setNome(membroModel.getNome());
            membro.setIdade(membroModel.getIdade());
            membro.setEmail(membroModel.getEmail());
            String encoder = this.passwordEncoder().encode(membro.getSenha());
            membro.setSenha(encoder);
            membro.setDataNascimento(membroModel.getDataNascimento());

            logger.info("Alterando o membro -- Administrador: "+getLogado());
            return this.repository.save(membroModel);
        }

        return null;
    }

    @Override
    public void deleteMembroById(String id) {
        Optional<Membro> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            logger.info("Deletando o membro:"+optional.get().getNome()+"  -- Administrador: "+getLogado());

            this.repository.deleteById(id);

        }

    }

    @Override
    public Membro findByEmailAndPassword(String email, String senha) {

        Optional<Membro> optional = this.repository.findByEmailAndSenha(email, senha);

        if (optional.isPresent()) {
            Membro membro = optional.get();
            logger.info("Membro: "+optional.get().getEmail()+" -- Administrador :"+getLogado());
            return membro;
        } else {
            throw new IllegalArgumentException("Usuario com email " + email + " não foi encontrado");
        }
    }


    @Override
    public List<Membro> findByStatusAtivo() {
        logger.info("Membros Ativos listados! -- Administrador: "+getLogado());
        return this.repository.findByStatusEquals(StatusMembro.ATIVO);
    }

    @Override
    public List<Membro> findByStatusPendente() {
        logger.info("Membros Pendentes listados! -- Administrador: "+getLogado());

        return this.repository.findByStatusEquals(StatusMembro.PENDENTE);
    }

    @Override
    public Membro findByEmail(String email) {
        logger.info("Membro listado por email -- Administrador: "+getLogado());

        return this.repository.findByEmail(email).isPresent() ? this.repository.findByEmail(email).get() : null;
    }

    public List<Membro> findByStatusInativo() {
        logger.info("Membros Inativos listados! -- Administrador: "+getLogado());

        return this.repository.findByStatusEquals(StatusMembro.INATIVO);
    }

    private String getLogado() {
        Authentication adminLogado = SecurityContextHolder.getContext().getAuthentication();
        if (!(adminLogado instanceof AnonymousAuthenticationToken)) {
            return adminLogado.getName();
        }
        return "null";

    }

}