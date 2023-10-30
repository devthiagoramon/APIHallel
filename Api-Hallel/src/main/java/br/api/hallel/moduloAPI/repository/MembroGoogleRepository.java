package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.MembroGoogle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MembroGoogleRepository extends MongoRepository<MembroGoogle, String> {

    Optional<MembroGoogle> findByEmail(String email);

    Optional<MembroGoogle> findByEmailAndNome(String email, String nome);
}
