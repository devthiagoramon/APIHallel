package br.api.hallel.repository;

import br.api.hallel.model.GastoFinanceiro;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GastoFinanceiroRepository extends MongoRepository<GastoFinanceiro, String> {
}
