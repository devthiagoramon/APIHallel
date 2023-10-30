package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.AssociadoStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AssociadoRepository extends MongoRepository<Associado,String> {
    Optional<Associado> findByEmail(String email);
    List<Associado> findByIsAssociadoEquals(Enum<AssociadoStatus> status);
}
