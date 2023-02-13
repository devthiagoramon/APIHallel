package br.api.hallel.repository;

import br.api.hallel.model.MembroGoogle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembroGoogleRepository extends MongoRepository<MembroGoogle, String> {

    Optional<MembroGoogle> findByEmail(String email);

    Optional<MembroGoogle> findByEmailAndNome(String email, String nome);
}
