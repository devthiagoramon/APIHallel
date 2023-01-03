package br.api.hallel.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.api.hallel.dto.MembroDTO;
import br.api.hallel.model.Membro;
import br.api.hallel.model.StatusMembro;
import br.api.hallel.repository.MembroRepository;
import br.api.hallel.security.Token;
import br.api.hallel.security.TokenUtil;
import br.api.hallel.service.interfaces.MembroInterface;

@Service
public class MembroService implements MembroInterface {

    @Autowired
    private MembroRepository repository;

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Membro createMembro(Membro membro) {
        System.out.println("Criando membro");
        String encoder = this.passwordEncoder().encode(membro.getSenha());
        membro.setSenha(encoder);
        return this.repository.insert(membro);
    }

    @Override
    public List<Membro> listAllMembros() {
        return this.repository.findAll(Sort.by(Direction.ASC, "status"));
    }

    @Override
    public Membro listMembroId(String id) {
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

    public Token gerarToken(MembroDTO membroDTO) {
        Optional<Membro> optional = this.repository
                    .findByEmail(membroDTO.getEmail());

        if(optional.isPresent()){

            Boolean valid = passwordEncoder().matches(membroDTO.getSenha(), optional.get().getSenha());
            if(valid){
                return new Token(TokenUtil.createToken(membroDTO.toMembro()));
            }
        }

        return null;
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

    public List<Membro> findByStatusInativo() {
        return this.repository.findByStatusEquals(StatusMembro.INATIVO);
    }

}