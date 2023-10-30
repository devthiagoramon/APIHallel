package br.api.hallel.moduloAPI.financeiroNovo.repository;

import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentosAssociado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoAssociadoRepository extends MongoRepository<PagamentosAssociado, String> {
}
