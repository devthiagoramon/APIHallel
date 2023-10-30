package br.api.hallel.moduloAPI.financeiroNovo.repository;

import br.api.hallel.moduloAPI.financeiroNovo.model.DespesaRetiro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesasRetiroRepository extends MongoRepository<DespesaRetiro, String> {
}
