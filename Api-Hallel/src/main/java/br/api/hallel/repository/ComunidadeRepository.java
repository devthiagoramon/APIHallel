package br.api.hallel.repository;

import br.api.hallel.model.Comunidade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunidadeRepository extends MongoRepository<Comunidade, String> {

}
