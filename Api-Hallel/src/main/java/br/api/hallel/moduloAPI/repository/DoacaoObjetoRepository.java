package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.DoacaoObjeto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface DoacaoObjetoRepository extends MongoRepository<DoacaoObjeto, String> {
}
