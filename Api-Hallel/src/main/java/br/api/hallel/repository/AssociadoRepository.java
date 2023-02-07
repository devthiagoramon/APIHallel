package br.api.hallel.repository;

import br.api.hallel.model.Associado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssociadoRepository extends MongoRepository<Associado,String> {

}
