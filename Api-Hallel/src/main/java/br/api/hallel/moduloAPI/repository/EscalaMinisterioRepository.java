package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.EscalaMinisterio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscalaMinisterioRepository extends MongoRepository<EscalaMinisterio, String> {
}
