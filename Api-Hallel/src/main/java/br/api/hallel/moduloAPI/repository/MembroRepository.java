package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Membro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembroRepository extends MongoRepository<Membro, String> {

    Optional<Membro> findByEmailAndSenha(String email, String senha);

    Optional<Membro> findByEmail(String email);

    Optional<Membro> findByNome(String username);

    boolean existsByEmail(String email);

    boolean existsByNome(String nome);

    Optional<Membro> findByNomeAndEmail(String nome, String email);

    Optional<Membro> findByToken(String token);
}
