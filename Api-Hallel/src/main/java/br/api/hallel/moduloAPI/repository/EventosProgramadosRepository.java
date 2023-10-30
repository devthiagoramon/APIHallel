package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.EventosProgramados;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosProgramadosRepository extends MongoRepository<EventosProgramados, String> {
}
