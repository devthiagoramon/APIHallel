package br.api.hallel.moduloAPI.service;

import java.util.List;
import java.util.Optional;

import br.api.hallel.moduloAPI.repository.MembroRepository;
import br.api.hallel.moduloAPI.service.interfaces.MembroInterface;
import br.api.hallel.moduloAPI.payload.resposta.PerfilResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.model.StatusMembro;

@Service
@Log4j2
public class MembroService implements MembroInterface {

    @Autowired
    private MembroRepository repository;

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //CRUD DE MEMBRO

    @Override
    public Membro createMembro(Membro membro) {
        System.out.println("Criando membro");
        String encoder = this.passwordEncoder().encode(membro.getSenha()); //CRIPTOGRAFA A SENHA
        membro.setSenha(encoder);
        return this.repository.insert(membro);
    }

    @Override
    public List<Membro> listAllMembros() {
        return this.repository.findAll(Sort.by(Direction.ASC, "status"));
    }

    @Override
    public Membro listMembroId(String id) {
        Optional<Membro> membroOptional = this.repository.findById(id);
        if (membroOptional.isPresent()) {
            return membroOptional.get();
        } else {
            log.info("Membro com id: '" + id + "' não enctrado");
            return null;
        }
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

            return this.repository.save(membroModel);
        }

        return null;
    }

    @Override
    public void deleteMembroById(String id) {
        Optional<Membro> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            this.repository.deleteById(id);

        }

    }

    @Override
    public Membro findByEmailAndPassword(String email, String senha) {

        Optional<Membro> optional = this.repository.findByEmailAndSenha(email, senha);

        if (optional.isPresent()) {
            Membro membro = optional.get();
            return membro;
        } else {
            throw new IllegalArgumentException("Usuario com email " + email + " não foi encontrado");
        }
    }


    @Override
    public List<Membro> findByStatusAtivo() {
        return this.repository.findByStatusEquals(StatusMembro.ATIVO);
    }

    @Override
    public List<Membro> findByStatusPendente() {
        return this.repository.findByStatusEquals(StatusMembro.PENDENTE);
    }

    @Override
    public Membro findByEmail(String email) {
        return this.repository.findByEmail(email).isPresent() ? this.repository.findByEmail(email).get() : null;
    }

    //INFORMAÇÕES PARA PODER O USUÁRIO VISUALIZAR SEU PERFIL
    @Override
    public PerfilResponse visualizarPerfil(String id) throws IllegalAccessException {

        //FAZ A BUSCA DO MEMBRO PELO BD, UTILIZANDO SEU ID
        Optional<Membro> optional = this.repository.findById(id);
        if (optional.isPresent()) {

            //SE EXISTE, EXIBE AS INFORMAÇÕES DO PERFIL
            PerfilResponse perfil = new PerfilResponse();
            perfil.setNome(optional.get().getNome());
            perfil.setEmail(optional.get().getEmail());
            perfil.setStatus(optional.get().getStatus());
            perfil.setIdade(optional.get().getIdade());
            perfil.setDataAniversario(optional.get().getDataNascimento());
            return perfil;
        }
        throw new IllegalAccessException("Usuario não encontrado para carregar o perfil");
    }

    public List<Membro> findByStatusInativo() {
        return this.repository.findByStatusEquals(StatusMembro.INATIVO);
    }

}