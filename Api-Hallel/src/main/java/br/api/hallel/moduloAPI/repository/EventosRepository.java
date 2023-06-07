package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Eventos;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventosRepository extends MongoRepository<Eventos, String> {
    Optional<Eventos> findByTitulo(String titulo);

    @Aggregation(pipeline = {
            "{$group: {_id: null, total: {$sum: \"$despesas\"}}}"
    })
    Double getDespesas();
}
