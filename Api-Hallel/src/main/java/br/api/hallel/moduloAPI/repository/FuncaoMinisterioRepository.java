package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.FuncaoMinisterio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncaoMinisterioRepository extends MongoRepository<FuncaoMinisterio, String> {
}
