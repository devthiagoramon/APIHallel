package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.ReceitaFinanceira;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReceitaFinanceiraRepository extends MongoRepository<ReceitaFinanceira,String> {
}
