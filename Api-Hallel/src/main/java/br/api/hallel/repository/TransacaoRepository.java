package br.api.hallel.repository;

import br.api.hallel.model.Transacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransacaoRepository extends MongoRepository<Transacao, String> {

}
