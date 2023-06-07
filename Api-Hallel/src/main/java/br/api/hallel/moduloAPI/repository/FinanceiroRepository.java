package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Financeiro;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FinanceiroRepository extends MongoRepository<Financeiro, String> {
}
