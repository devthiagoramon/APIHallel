package br.api.hallel.repository;

import br.api.hallel.model.Associado;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AssociadoRepository extends MongoRepository<Associado,String> {
}
