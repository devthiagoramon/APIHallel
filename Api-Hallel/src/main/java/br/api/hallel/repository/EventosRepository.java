package br.api.hallel.repository;

import br.api.hallel.model.Eventos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventosRepository extends MongoRepository<Eventos, String> {
    Optional<Eventos> findByNome(String nome);
}
