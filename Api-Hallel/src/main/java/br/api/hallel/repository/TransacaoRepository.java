package br.api.hallel.repository;

import br.api.hallel.model.MetodoPagamento;
import br.api.hallel.model.Transacao;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransacaoRepository extends MongoRepository<Transacao, String> {

}
