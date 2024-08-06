package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Doacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoRepository extends MongoRepository<Doacao, String> {

}
