package br.api.hallel.moduloAPI.financeiroNovo.repository;

import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoSaidaFinanceiro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodigoSaidaFinanceiroRepository extends MongoRepository<CodigoSaidaFinanceiro, String> {
}
