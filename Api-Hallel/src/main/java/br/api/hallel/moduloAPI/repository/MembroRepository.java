package br.api.hallel.moduloAPI.repository;

import java.lang.Enum;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.model.StatusMembro;

public interface MembroRepository extends MongoRepository<Membro, String>{
    
    public List<Membro> findByStatusEquals(Enum<StatusMembro> status);
    public Optional<Membro> findByEmailAndSenha(String email, String senha);
    public Optional<Membro> findByEmail(String email);
    public Optional<Membro> findByNome(String username);
    public boolean existsByEmail(String email);

    public boolean existsByNome(String nome);

    public Optional<Membro> findByNomeAndEmail(String nome, String email);
}