package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.LocalEvento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalEventoRepository extends MongoRepository<LocalEvento, String> {
}
