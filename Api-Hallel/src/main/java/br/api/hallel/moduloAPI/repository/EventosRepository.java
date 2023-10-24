package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Eventos;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventosRepository extends MongoRepository<Eventos, String> {
    Optional<Eventos> findByTitulo(String titulo);
    List<Eventos> findAllByDestaqueEquals(Boolean isTrue);
    List<Eventos> findAllByOrderByTituloAsc();
}
