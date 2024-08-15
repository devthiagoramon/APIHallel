package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Ministerio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinisterioRepository extends MongoRepository<Ministerio, String> {

}
