package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.EventoArquivado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoArquivadoRepository extends MongoRepository<EventoArquivado, String> {
}
