package br.api.hallel.moduloAPI.financeiroNovo.repository;

import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentoEntradaEvento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoEntradaEventoRepository extends MongoRepository<PagamentoEntradaEvento, String> {
}
