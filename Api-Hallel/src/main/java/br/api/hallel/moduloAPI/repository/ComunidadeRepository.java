package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Comunidade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface ComunidadeRepository extends MongoRepository<Comunidade, String> {


}
