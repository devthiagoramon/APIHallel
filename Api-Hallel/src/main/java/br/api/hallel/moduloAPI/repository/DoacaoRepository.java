package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Doacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoacaoRepository extends MongoRepository<Doacao, String> {

}
