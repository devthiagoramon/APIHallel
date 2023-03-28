package br.api.hallel.repository;

import br.api.hallel.model.DoacaoObjeto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoObjetoRepository extends MongoRepository<DoacaoObjeto, String> {
}
