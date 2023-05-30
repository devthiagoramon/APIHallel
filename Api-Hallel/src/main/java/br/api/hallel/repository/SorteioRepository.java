package br.api.hallel.repository;

import br.api.hallel.model.Sorteio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SorteioRepository extends MongoRepository<Sorteio, String> {
}
