package br.api.hallel.repository;

import br.api.hallel.model.Doacao;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoRepository extends MongoRepository<Doacao, String> {

}
