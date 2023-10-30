package br.api.hallel.moduloAPI.financeiroNovo.repository;

import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentoEntradaEvento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoEntradaEventoRepository extends MongoRepository<PagamentoEntradaEvento, String> {
    public Optional<PagamentoEntradaEvento> findByIdEventos(String idEvento);
}
