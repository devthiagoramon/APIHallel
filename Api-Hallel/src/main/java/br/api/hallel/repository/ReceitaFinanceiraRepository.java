package br.api.hallel.repository;

import br.api.hallel.model.ReceitaFinanceira;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReceitaFinanceiraRepository extends MongoRepository<ReceitaFinanceira,String> {
}
