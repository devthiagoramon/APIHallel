package br.api.hallel.repository;

import br.api.hallel.model.Eventos;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventosRepository extends MongoRepository<Eventos, String> {
    Optional<Eventos> findByTitulo(String titulo);

    @Aggregation(pipeline = {
            "{$group: {_id: null, total: {$sum: \"$despesas\"}}}"
    })
    Double getDespesas();
}
