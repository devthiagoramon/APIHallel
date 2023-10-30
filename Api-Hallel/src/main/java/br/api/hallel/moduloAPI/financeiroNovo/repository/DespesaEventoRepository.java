package br.api.hallel.moduloAPI.financeiroNovo.repository;

import br.api.hallel.moduloAPI.financeiroNovo.model.DespesaEvento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaEventoRepository extends MongoRepository<DespesaEvento, String> {
}
