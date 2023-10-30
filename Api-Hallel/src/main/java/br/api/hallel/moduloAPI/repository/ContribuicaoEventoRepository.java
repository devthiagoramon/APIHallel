package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.ContribuicaoEvento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContribuicaoEventoRepository extends MongoRepository<ContribuicaoEvento, String> {
}
