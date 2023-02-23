package br.api.hallel.repository;

import br.api.hallel.model.Financeiro;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FinanceiroRepository extends MongoRepository<Financeiro, String> {
}
