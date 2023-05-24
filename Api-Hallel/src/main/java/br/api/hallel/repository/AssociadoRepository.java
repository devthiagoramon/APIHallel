package br.api.hallel.repository;

import br.api.hallel.model.Associado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssociadoRepository extends MongoRepository<Associado,String> {
    Optional<Associado> findByEmail(String email);
}
