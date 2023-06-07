package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Sorteio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SorteioRepository extends MongoRepository<Sorteio, String> {
}
