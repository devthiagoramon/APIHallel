package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.GastoFinanceiro;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GastoFinanceiroRepository extends MongoRepository<GastoFinanceiro, String> {
}
