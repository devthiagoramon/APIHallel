package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Transacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransacaoRepository extends MongoRepository<Transacao, String> {

}
